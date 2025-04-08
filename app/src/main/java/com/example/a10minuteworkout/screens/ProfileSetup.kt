@file:OptIn(ExperimentalLayoutApi::class)

package com.example.a10minuteworkout.screens

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a10minuteworkout.data.UserPreferences
import com.example.a10minuteworkout.utils.GradientBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

@Composable
fun ProfileSetupStep1(navController: NavController) {
    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                LinearProgressIndicator(
                    progress = { 0.2f },
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "How do you identify?",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step2") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Male", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step2") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Female", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step2") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Other", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ProfileSetupStep2(navController: NavController) {
    val ageRange = (18..100).toList()
    var selectedAge by remember { mutableStateOf(25) }

    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                LinearProgressIndicator(
                    progress = {  0.4f  },
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "How old are you?",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                LazyColumn(
                    modifier = Modifier.height(250.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(ageRange) { _, age ->
                        Text(
                            text = "$age",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { selectedAge = age },
                            color = if (selectedAge == age) Color.White else Color.LightGray,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Button(
                    onClick = { navController.navigate("profileSetup/step3") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Next", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ProfileSetupStep3(navController: NavController) {
    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                LinearProgressIndicator(
                    progress = { 0.6f },
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "What is your workout experience?",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step4") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Beginner", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step4") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Intermediate", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step4") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Advanced", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ProfileSetupStep4(navController: NavController) {
    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                LinearProgressIndicator(
                    progress = { 0.8f },
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "What is your main workout goal?",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step5") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Improve Fitness", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step5") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Build Strength", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step5") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Lose Fat", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("profileSetup/step5") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Other", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ProfileSetupStep5(navController: NavController, userPreferences: UserPreferences) {
    val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    var selectedDays by remember { mutableStateOf(setOf<String>()) }
    val context = LocalContext.current

    var timePickerDialog by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()
    var selectedHour by remember { mutableStateOf(calendar.get(Calendar.HOUR_OF_DAY)) }
    var selectedMinute by remember { mutableStateOf(calendar.get(Calendar.MINUTE)) }

    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                LinearProgressIndicator(
                    progress = { 1.0f },
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "When would you like to work out?",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    daysOfWeek.forEach { day ->
                        Button(
                            onClick = {
                                selectedDays = if (selectedDays.contains(day)) {
                                    selectedDays - day
                                } else {
                                    selectedDays + day
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedDays.contains(day)) Color(0xFFFF9800) else Color.White,
                                contentColor = if (selectedDays.contains(day)) Color.White else Color(0xFFFF9800)
                            ),
                            modifier = Modifier
                                .wrapContentWidth()
                                .height(40.dp)
                        ) {
                            Text(text = day, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Button(
                    onClick = { timePickerDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    )
                ) {
                    Text(
                        "Select Time: ${String.format("%02d:%02d", selectedHour, selectedMinute)}",
                        fontWeight = FontWeight.Bold
                    )
                }
                if (timePickerDialog) {
                    TimePickerDialog(
                        context,
                        { _, hour, minute ->
                            selectedHour = hour
                            selectedMinute = minute
                            timePickerDialog = false
                        },
                        selectedHour,
                        selectedMinute,
                        false
                    ).show()
                }

                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            userPreferences.completeProfileSetup()
                            withContext(Dispatchers.Main) {
                                navController.navigate("main") {
                                    popUpTo("profileSetup/step1") { inclusive = true }
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFFF9800)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Finish Setup", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}