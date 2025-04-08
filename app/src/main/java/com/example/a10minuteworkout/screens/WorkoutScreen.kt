package com.example.a10minuteworkout.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a10minuteworkout.R
import com.example.a10minuteworkout.utils.GradientBackground

@Composable
fun WorkoutScreen(navController: NavController) {
    val workoutList = listOf(
        "10 Minute Cardio",
        "10 Minute Core",
        "10 Minute Intense"
    )

    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(240.dp)
                        .padding(bottom = 32.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                workoutList.forEach { workoutName ->
                    Button(
                        onClick = { navController.navigate("individualWorkout/$workoutName") },
                        modifier = Modifier.widthIn(min = 200.dp, max = 300.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFFFF9800)
                        )
                    ) {
                        Text(
                            text = workoutName,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}