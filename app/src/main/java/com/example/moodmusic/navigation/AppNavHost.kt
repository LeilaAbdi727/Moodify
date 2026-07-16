package com.example.moodmusic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.moodmusic.ui.Screen.MoodDetailScreen
import com.example.moodmusic.ui.Screen.MoodListScreen
import com.example.moodmusic.ui.mood.ViewModel.MoodViewModel

@Composable
fun AppNavHost(
    viewModel: MoodViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val appNavController = AppNavController(navController)

    NavHost(
        navController = navController,
        startDestination = MoodListRoute,
        modifier = modifier
    ) {
        composable<MoodListRoute> {
            MoodListScreen(
                viewModel = viewModel,
                onNavigateToDetail = { moodId ->
                    appNavController.navigateToMoodDetail(moodId)
                }
            )
        }

        composable<MoodDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<MoodDetailRoute>()

            LaunchedEffect(route.moodId) {
                viewModel.setSelectedMood(route.moodId)
            }

            MoodDetailScreen(
                viewModel = viewModel,
                onBack = {
                    appNavController.navigateBack()
                }
            )
        }
    }
}