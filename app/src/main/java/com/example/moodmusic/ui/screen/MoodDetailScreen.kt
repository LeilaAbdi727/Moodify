package com.example.moodmusic.ui.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.moodmusic.audio.MoodSoundPlayer
import com.example.moodmusic.ui.mood.MoodSideEffect
import com.example.moodmusic.ui.mood.MoodUiAction
import com.example.moodmusic.ui.mood.ViewModel.MoodViewModel

@Composable
fun MoodDetailScreen(
    viewModel: MoodViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val soundPlayer = remember { MoodSoundPlayer(context) }

    DisposableEffect(Unit) {
        onDispose {
            soundPlayer.release()
        }
    }

    // تغییر کلید لایف‌سایکل به viewModel
    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is MoodSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is MoodSideEffect.PlaySound -> {
                    soundPlayer.play(effect.soundResId)
                }

                MoodSideEffect.NavigateBack -> {
                    onBack()
                }

                else -> Unit
            }
        }
    }

    val mood = uiState.selectedMood

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = mood?.emoji ?: "❓",
            style = MaterialTheme.typography.displayLarge
        )

        Text(
            text = mood?.name ?: "Unknown Mood",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = {
                viewModel.onAction(MoodUiAction.PlaySoundClicked)
            },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Play Sound")
        }

        Button(
            onClick = {
                viewModel.onAction(MoodUiAction.BackClicked)
            },
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text("Back")
        }
    }
}