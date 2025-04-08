package com.example.a10minuteworkout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.a10minuteworkout.data.UserPreferences
import com.example.a10minuteworkout.navigation.AppNavHost
import com.example.a10minuteworkout.ui.theme._10MinuteWorkoutTheme

class MainActivity : ComponentActivity() {
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPreferences = UserPreferences(applicationContext)

        setContent {
            _10MinuteWorkoutTheme {
                AppNavHost(userPreferences = userPreferences)
            }
        }
    }
}