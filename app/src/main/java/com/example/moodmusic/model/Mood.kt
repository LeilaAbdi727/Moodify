package com.example.moodmusic.model

data class Mood(
    val id: String,
    val name: String,
    val emoji: String,
    val soundResId: Int?
)