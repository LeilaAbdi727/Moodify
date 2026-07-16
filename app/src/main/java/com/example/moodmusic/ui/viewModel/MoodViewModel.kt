package com.example.moodmusic.ui.mood.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmusic.model.MoodRepository
import com.example.moodmusic.ui.mood.MoodSideEffect
import com.example.moodmusic.ui.mood.MoodUiAction
import com.example.moodmusic.ui.mood.MoodUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MoodViewModel(
    private val repository: MoodRepository
) : ViewModel() { // ارث‌بری از لایف‌سایکل ویومدل اندروید

    private val _uiState = MutableStateFlow(
        MoodUiState(
            moods = repository.getMoods()
        )
    )
    val uiState: StateFlow<MoodUiState> = _uiState

    private val _sideEffect = Channel<MoodSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(action: MoodUiAction) {
        when (action) {
            is MoodUiAction.MoodClicked -> {
                val mood = repository.getMoodById(action.moodId)
                _uiState.value = _uiState.value.copy(
                    selectedMood = mood
                )
                viewModelScope.launch {
                    _sideEffect.send(
                        MoodSideEffect.NavigateToDetail(action.moodId)
                    )
                }
            }

            MoodUiAction.PlaySoundClicked -> {
                val mood = _uiState.value.selectedMood
                viewModelScope.launch {
                    if (mood?.soundResId != null) {
                        _sideEffect.send(MoodSideEffect.PlaySound(mood.soundResId))
                        _sideEffect.send(MoodSideEffect.ShowToast("Playing ${mood.name} sound"))
                    } else {
                        _sideEffect.send(MoodSideEffect.ShowToast("Sound not available"))
                    }
                }
            }

            MoodUiAction.BackClicked -> {
                viewModelScope.launch {
                    _sideEffect.send(MoodSideEffect.NavigateBack)
                }
            }
        }
    }

    fun setSelectedMood(moodId: String) {
        val mood = repository.getMoodById(moodId)
        _uiState.value = _uiState.value.copy(
            selectedMood = mood
        )
    }
}