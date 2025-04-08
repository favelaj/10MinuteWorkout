package com.example.a10minuteworkout.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.a10minuteworkout.R
import com.example.a10minuteworkout.utils.GradientBackground
import java.time.DayOfWeek
import java.time.LocalDateTime

data class Achievement(
    val title: String,
    var currentValue: Int = 0,
    val targetValue: Int = 1,
    val isCompleted: Boolean = false,
    val workoutType: String? = null
) {
    val progress: Float get() = (currentValue.toFloat() / targetValue).coerceIn(0f, 1f)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AchievementsScreen(
    completedWorkouts: Int,
    completedCardioWorkouts: Int,
    completedCoreWorkouts: Int,
    completedIntenseWorkouts: Int
) {
    var achievements = listOf(
        Achievement("First Step: Complete your first workout", workoutType = null),
        Achievement("Early Bird: Complete a workout before 8 AM", workoutType = null),
        Achievement("Night Owl: Complete a workout after 8 PM", workoutType = null),
        Achievement("Streak Master: Complete 3 workouts in 3 consecutive days", 0, 3, workoutType = null),
        Achievement("Week Warrior: Complete 7 workouts in a week", 0, 7, workoutType = null),
        Achievement("Monthly Champion: Complete 20 workouts in a month", 0, 20, workoutType = null),

        Achievement("Cardio King: Complete 5 cardio workouts", 0, 5, workoutType = "Cardio"),
        Achievement("Core Commander: Complete 5 core workouts", 0, 5, workoutType = "Core"),
        Achievement("Intensity Master: Complete 5 intense workouts", 0, 5, workoutType = "Intense"),

        Achievement("All-Rounder: Complete all workout types at least once", 0, 3, workoutType = null),
        Achievement("30-Day Commitment: Work out for 30 days straight", 0, 30, workoutType = null),
        Achievement("90-Day Transformation: Complete 60 workouts in 90 days", 0, 60, workoutType = null),
        Achievement("Weekend Warrior: Complete workouts on both Saturday and Sunday", 0, 2, workoutType = null),
        Achievement("Perfect Week: Complete all planned workouts in a week", 0, 7, workoutType = null),
        Achievement("Fitness Enthusiast: Total workout time reaches 10 hours", 0, 600, workoutType = null)
    )

    val updatedAchievements = achievements.map { achievement ->
        when (achievement.workoutType) {
            null -> when (achievement.title) {
                "First Step: Complete your first workout" ->
                    achievement.copy(currentValue = if (completedWorkouts > 0) 1 else 0)
                "All-Rounder: Complete all workout types at least once" ->
                    achievement.copy(currentValue = listOf(
                        completedCardioWorkouts > 0,
                        completedCoreWorkouts > 0,
                        completedIntenseWorkouts > 0
                    ).count { it })
                "Streak Master: Complete 3 workouts in 3 consecutive days",
                "Week Warrior: Complete 7 workouts in a week",
                "Monthly Champion: Complete 20 workouts in a month",
                "30-Day Commitment: Work out for 30 days straight",
                "90-Day Transformation: Complete 60 workouts in 90 days",
                "Fitness Enthusiast: Total workout time reaches 10 hours" ->
                    achievement.copy(currentValue = completedWorkouts)
                else -> achievement
            }
            "Cardio" -> achievement.copy(currentValue = completedCardioWorkouts)
            "Core" -> achievement.copy(currentValue = completedCoreWorkouts)
            "Intense" -> achievement.copy(currentValue = completedIntenseWorkouts)
            else -> achievement
        }
    }

    val currentDateTime = LocalDateTime.now()
    val currentDayOfWeek = currentDateTime.dayOfWeek

    val timeBasedAchievements = updatedAchievements.map { achievement ->
        when (achievement.title) {
            "Early Bird: Complete a workout before 8 AM" ->
                if (currentDateTime.hour < 8 && completedWorkouts > 0)
                    achievement.copy(currentValue = 1)
                else achievement
            "Night Owl: Complete a workout after 8 PM" ->
                if (currentDateTime.hour >= 20 && completedWorkouts > 0)
                    achievement.copy(currentValue = 1)
                else achievement
            "Weekend Warrior: Complete workouts on both Saturday and Sunday" ->
                if ((currentDayOfWeek == DayOfWeek.SATURDAY || currentDayOfWeek == DayOfWeek.SUNDAY)
                    && completedWorkouts > 0)
                    achievement.copy(currentValue = 1)
                else achievement
            "Perfect Week: Complete all planned workouts in a week" ->
                if (currentDayOfWeek == DayOfWeek.SUNDAY && completedWorkouts >= 7)
                    achievement.copy(currentValue = 7)
                else achievement

            else -> achievement
        }
    }

    achievements = timeBasedAchievements.map { achievement ->
        achievement.copy(isCompleted = achievement.currentValue >= achievement.targetValue)
    }

    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = "Achievements",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(achievements) { achievement ->
                        AchievementCard(achievement = achievement)
                    }
                }
            }
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(start = 8.dp, top = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.achievement_badge),
            contentDescription = "Achievement Badge",
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopStart)
                .zIndex(2f)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (achievement.isCompleted) {
                    Color(0xFFFFD700)
                } else {
                    Color.White
                }
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = if (achievement.isCompleted) {
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFFFFD700), Color(0xFFFFE680))
                            )
                        } else {
                            Brush.verticalGradient(
                                colors = listOf(Color.White, Color.White)
                            )
                        }
                    )
                    .padding(16.dp)
                    .padding(start = 24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = achievement.title,
                        color = if (achievement.isCompleted) Color.White else Color(0xFFFF9800),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = { achievement.progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = if (achievement.isCompleted) Color.White else Color(0xFFFF9800),
                        trackColor = if (achievement.isCompleted) Color(0x80FFFFFF) else Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (achievement.isCompleted)
                            "Achievement Complete!"
                        else "${achievement.currentValue}/${achievement.targetValue} (${(achievement.progress * 100).toInt()}% Complete)",
                        color = if (achievement.isCompleted) Color.White else Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}