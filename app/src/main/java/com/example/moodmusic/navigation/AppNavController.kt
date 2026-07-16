package com.example.moodmusic.navigation

import androidx.navigation.NavHostController

class AppNavController(
    private val navController: NavHostController
) {
    fun navigateToMoodDetail(moodId: String) {
        navController.navigate(MoodDetailRoute(moodId))
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}