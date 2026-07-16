package com.example.moodmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moodmusic.model.MoodRepository
import com.example.moodmusic.navigation.AppNavHost
import com.example.moodmusic.ui.mood.ViewModel.MoodViewModel
import com.example.moodmusic.ui.theme.MoodMusicTheme

class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        MoodViewModel(MoodRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoodMusicTheme {
                AppNavHost(viewModel = viewModel)
            }
        }
    }
}