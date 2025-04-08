package com.example.a10minuteworkout.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a10minuteworkout.R
import com.example.a10minuteworkout.data.UserPreferences
import com.example.a10minuteworkout.utils.GradientBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, userPreferences: UserPreferences) {
    val isProfileSetupComplete by userPreferences.isProfileSetupComplete.collectAsState(initial = false)
    var showWelcomeText by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        delay(500)
        showWelcomeText = true
        delay(500)
        showButton = true

        if (isProfileSetupComplete) {
            delay(1000)
            navController.navigate("main") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 32.dp)
                )

                AnimatedVisibility(
                    visible = showWelcomeText,
                    enter = fadeIn() + slideInVertically(
                        initialOffsetY = { 100 }
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 32.dp)
                    ) {
                        Text(
                            text = "Welcome to",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "10 Minute Workout",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                AnimatedVisibility(
                    visible = showButton && !isProfileSetupComplete,
                    enter = fadeIn() + expandVertically()
                ) {
                    Button(
                        onClick = {
                            navController.navigate("profileSetup/step1") {
                                popUpTo("splash") { inclusive = true }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFFFF9800)
                        ),
                        modifier = Modifier.widthIn(min = 200.dp, max = 300.dp)
                    ) {
                        Text(
                            text = "Setup Profile",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}