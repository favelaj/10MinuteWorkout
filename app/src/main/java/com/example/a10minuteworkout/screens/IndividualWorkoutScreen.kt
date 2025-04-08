package com.example.a10minuteworkout.screens

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a10minuteworkout.R
import com.example.a10minuteworkout.utils.GradientBackground
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Composable
fun IndividualWorkoutScreen(
    navController: NavController,
    workoutName: String,
    onWorkoutCompleted: () -> Unit
) {
    var isWorkoutStarted by remember { mutableStateOf(false) }
    var timeRemaining by remember { mutableStateOf(600) }
    var currentWorkoutIndex by remember { mutableStateOf(0) }
    val workouts = remember { getWorkoutsList(workoutName) }
    val workoutImages = remember { getWorkoutImages(workoutName) }
    var isWorkoutComplete by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val whistleSound = remember { MediaPlayer.create(context, R.raw.whistle) }
    var hasRecordedCompletion by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            whistleSound.release()
        }
    }

    LaunchedEffect(isWorkoutStarted) {
        while (isWorkoutStarted && timeRemaining > 0) {
            delay(1000L)
            timeRemaining--

            if (timeRemaining % 60 == 0) {
                currentWorkoutIndex = (currentWorkoutIndex + 1).coerceIn(0, workouts.size - 1)
                try {
                    whistleSound.seekTo(0)
                    whistleSound.start()
                } catch (e: Exception) {
                }
            }

            if (timeRemaining == 0 && !hasRecordedCompletion) {
                isWorkoutComplete = true
                isWorkoutStarted = false
                onWorkoutCompleted()
                hasRecordedCompletion = true
            }
        }
    }

    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(32.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 32.dp)
                    ) {
                        Text(
                            text = workouts[currentWorkoutIndex],
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Image(
                            painter = painterResource(id = workoutImages[currentWorkoutIndex]),
                            contentDescription = workouts[currentWorkoutIndex],
                            modifier = Modifier.size(200.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = formatTime(timeRemaining),
                        style = MaterialTheme.typography.displayLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    if (isWorkoutComplete) {
                        Text(
                            text = "Workout Complete!",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    Button(
                        onClick = {
                            if (isWorkoutComplete) {
                                navController.navigate("main") {
                                    popUpTo("main") { inclusive = true }
                                }
                            } else {
                                isWorkoutStarted = !isWorkoutStarted
                            }
                        },

                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .widthIn(min = 200.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFFFF9800)
                        )
                    ) {
                        Text(
                            text = if (isWorkoutComplete) "Continue"
                            else if (isWorkoutStarted) "Pause"
                            else "Start",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun formatTime(seconds: Int): String {
    return String.format("%02d:%02d",
        TimeUnit.SECONDS.toMinutes(seconds.toLong()),
        seconds % 60
    )
}

private fun getWorkoutsList(workoutType: String): List<String> {
    return when (workoutType) {
        "10 Minute Cardio" -> listOf(
            "Jumping Jacks",
            "High Knees",
            "Mountain Climbers",
            "Burpees",
            "Jump Rope",
            "Running in Place",
            "Jump Squats",
            "Butt Kicks",
            "Star Jumps",
            "Speed Skaters"
        )
        "10 Minute Core" -> listOf(
            "Crunches",
            "Plank",
            "Russian Twists",
            "Leg Raises",
            "Mountain Climbers",
            "Bicycle Crunches",
            "V-Sits",
            "Side Planks",
            "Flutter Kicks",
            "Plank to Downward Dog"
        )
        else -> listOf(
            "Burpees",
            "Push-Ups",
            "Mountain Climbers",
            "Jump Squats",
            "Plank",
            "High Knees",
            "Diamond Push-Ups",
            "Jump Lunges",
            "Flutter Kicks",
            "Plank Shoulder Taps"
        )
    }
}

private fun getWorkoutImages(workoutType: String): List<Int> {
    return when (workoutType) {
        "10 Minute Cardio" -> listOf(
            R.drawable.jumping_jacks,
            R.drawable.high_knees,
            R.drawable.mountain_climbers,
            R.drawable.burpees,
            R.drawable.jump_rope,
            R.drawable.running_in_place,
            R.drawable.jump_squats,
            R.drawable.butt_kicks,
            R.drawable.star_jumps,
            R.drawable.speed_skaters
        )
        "10 Minute Core" -> listOf(
            R.drawable.crunches,
            R.drawable.plank,
            R.drawable.russian_twists,
            R.drawable.leg_raises,
            R.drawable.mountain_climbers,
            R.drawable.bicycle_crunches,
            R.drawable.v_sits,
            R.drawable.side_planks,
            R.drawable.flutter_kicks,
            R.drawable.plank_to_downward_dog
        )
        else -> listOf(
            R.drawable.burpees,
            R.drawable.push_ups,
            R.drawable.mountain_climbers,
            R.drawable.jump_squats,
            R.drawable.plank,
            R.drawable.high_knees,
            R.drawable.diamond_push_ups,
            R.drawable.jump_lunges,
            R.drawable.flutter_kicks,
            R.drawable.plank_shoulder_taps
        )
    }
}