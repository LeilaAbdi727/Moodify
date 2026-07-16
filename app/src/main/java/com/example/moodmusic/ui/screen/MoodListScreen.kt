package com.example.moodmusic.ui.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodmusic.ui.mood.MoodSideEffect
import com.example.moodmusic.ui.mood.MoodUiAction
import com.example.moodmusic.ui.mood.ViewModel.MoodViewModel

@Composable
fun MoodListScreen(
    viewModel: MoodViewModel,
    onNavigateToDetail: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // تغییر کلید لایف‌سایکل برای جلوگیری از تداخل دریافت کانال
    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { effect ->
            if (effect is MoodSideEffect.NavigateToDetail) {
                onNavigateToDetail(effect.moodId)
            }
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(uiState.moods) { mood ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clickable {
                        viewModel.onAction(MoodUiAction.MoodClicked(mood.id))
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = mood.emoji,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = mood.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}