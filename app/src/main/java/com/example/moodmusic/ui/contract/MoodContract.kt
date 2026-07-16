package com.example.moodmusic.ui.mood

import com.example.moodmusic.model.Mood

data class MoodUiState(
    val moods: List<Mood> = emptyList(),
    val selectedMood: Mood? = null
)

sealed interface MoodUiAction {
    data class MoodClicked(val moodId: String) : MoodUiAction
    data object PlaySoundClicked : MoodUiAction
    data object BackClicked : MoodUiAction
}

sealed interface MoodSideEffect {
    data class NavigateToDetail(val moodId: String) : MoodSideEffect
    data class ShowToast(val message: String) : MoodSideEffect
    data class PlaySound(val soundResId: Int) : MoodSideEffect
    data object NavigateBack : MoodSideEffect
}