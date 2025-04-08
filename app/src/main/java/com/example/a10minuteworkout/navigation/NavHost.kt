package com.example.a10minuteworkout.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.a10minuteworkout.data.UserPreferences
import com.example.a10minuteworkout.screens.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(userPreferences: UserPreferences) {
    val navController = rememberNavController()
    val completedWorkouts by userPreferences.completedWorkouts.collectAsState(initial = 0)
    val completedCardioWorkouts by userPreferences.completedCardioWorkouts.collectAsState(initial = 0)
    val completedCoreWorkouts by userPreferences.completedCoreWorkouts.collectAsState(initial = 0)
    val completedIntenseWorkouts by userPreferences.completedIntenseWorkouts.collectAsState(initial = 0)

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController, userPreferences)
        }

        composable("profileSetup/step1") {
            ProfileSetupStep1(navController)
        }

        composable("profileSetup/step2") {
            ProfileSetupStep2(navController)
        }

        composable("profileSetup/step3") {
            ProfileSetupStep3(navController)
        }

        composable("profileSetup/step4") {
            ProfileSetupStep4(navController)
        }

        composable("profileSetup/step5") {
            ProfileSetupStep5(navController, userPreferences)
        }

        composable("main") {
            MainScreen(navController)
        }

        composable("workout") {
            WorkoutScreen(navController)
        }

        composable("exercises") {
            ExercisesScreen(navController)
        }

        composable(
            "exerciseDetail/{exerciseName}",
            arguments = listOf(navArgument("exerciseName") { type = NavType.StringType })
        ) { backStackEntry ->
            ExerciseDetailScreen(
                navController = navController,
                exerciseName = backStackEntry.arguments?.getString("exerciseName") ?: ""
            )
        }

        composable("achievements") {
            AchievementsScreen(
                completedWorkouts = completedWorkouts,
                completedCardioWorkouts = completedCardioWorkouts,
                completedCoreWorkouts = completedCoreWorkouts,
                completedIntenseWorkouts = completedIntenseWorkouts
            )
        }

        composable(
            "individualWorkout/{workoutName}",
            arguments = listOf(navArgument("workoutName") { type = NavType.StringType })
        ) { backStackEntry ->
            val workoutName = backStackEntry.arguments?.getString("workoutName") ?: ""
            val workoutType = when {
                workoutName.contains("Cardio", ignoreCase = true) -> "Cardio"
                workoutName.contains("Core", ignoreCase = true) -> "Core"
                workoutName.contains("Intense", ignoreCase = true) -> "Intense"
                else -> null
            }

            IndividualWorkoutScreen(
                navController = navController,
                workoutName = workoutName,
                onWorkoutCompleted = {
                    CoroutineScope(Dispatchers.IO).launch {
                        userPreferences.incrementCompletedWorkouts(workoutType)
                    }
                }
            )
        }
    }
}