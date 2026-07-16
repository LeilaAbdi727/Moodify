package com.example.moodmusic.navigation

import kotlinx.serialization.Serializable

@Serializable
object MoodListRoute

@Serializable
data class MoodDetailRoute(
    val moodId: String
)