package com.example.moodmusic.audio

import android.content.Context
import android.media.MediaPlayer

class MoodSoundPlayer(
    private val context: Context
) {
    private var mediaPlayer: MediaPlayer? = null

    fun play(soundResId: Int) {
        release()

        mediaPlayer = MediaPlayer.create(context, soundResId).apply {
            setOnCompletionListener {
                release()
            }
            start()
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}