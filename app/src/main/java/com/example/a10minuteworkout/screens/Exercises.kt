package com.example.a10minuteworkout.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a10minuteworkout.R
import com.example.a10minuteworkout.utils.GradientBackground

data class Exercise(
    val name: String,
    val category: String,
    val imageResId: Int
)

@Composable
fun ExercisesScreen(navController: NavController) {
    val exercises = listOf(
        Exercise("Jumping Jacks", "Cardio", R.drawable.jumping_jacks),
        Exercise("High Knees", "Cardio", R.drawable.high_knees),
        Exercise("Mountain Climbers", "Cardio", R.drawable.mountain_climbers),
        Exercise("Burpees", "Cardio", R.drawable.burpees),
        Exercise("Jump Rope", "Cardio", R.drawable.jump_rope),
        Exercise("Running in Place", "Cardio", R.drawable.running_in_place),
        Exercise("Jump Squats", "Cardio", R.drawable.jump_squats),
        Exercise("Butt Kicks", "Cardio", R.drawable.butt_kicks),
        Exercise("Star Jumps", "Cardio", R.drawable.star_jumps),
        Exercise("Speed Skaters", "Cardio", R.drawable.speed_skaters),

        Exercise("Crunches", "Core", R.drawable.crunches),
        Exercise("Plank", "Core", R.drawable.plank),
        Exercise("Russian Twists", "Core", R.drawable.russian_twists),
        Exercise("Leg Raises", "Core", R.drawable.leg_raises),
        Exercise("Bicycle Crunches", "Core", R.drawable.bicycle_crunches),
        Exercise("V-Sits", "Core", R.drawable.v_sits),
        Exercise("Side Planks", "Core", R.drawable.side_planks),
        Exercise("Flutter Kicks", "Core", R.drawable.flutter_kicks),
        Exercise("Plank to Downward Dog", "Core", R.drawable.plank_to_downward_dog),

        Exercise("Push-Ups", "Intense", R.drawable.push_ups),
        Exercise("Diamond Push-Ups", "Intense", R.drawable.diamond_push_ups),
        Exercise("Jump Lunges", "Intense", R.drawable.jump_lunges),
        Exercise("Plank Shoulder Taps", "Intense", R.drawable.plank_shoulder_taps)
    )

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
                    text = "Exercises",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val groupedExercises = exercises.groupBy { it.category }

                    groupedExercises.forEach { (category, exercisesInCategory) ->
                        item {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }

                        items(exercisesInCategory) { exercise ->
                            ExerciseCard(exercise = exercise, navController = navController)
                        }

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate("exerciseDetail/${exercise.name}")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = exercise.imageResId),
                contentDescription = exercise.name,
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 16.dp)
            )

            Column {
                Text(
                    text = exercise.name,
                    color = Color(0xFFFF9800),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(

                    text = exercise.category,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}