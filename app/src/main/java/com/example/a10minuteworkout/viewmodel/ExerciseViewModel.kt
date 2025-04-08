package com.example.a10minuteworkout.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a10minuteworkout.model.Exercise

class ExerciseViewModel : ViewModel() {
    private val exercises: List<Exercise> = listOf(
        Exercise(
            id = 1,
            name = "Push-ups",
            description = "",
            image = ""
        ),
        Exercise(
            id = 2,
            name = "Jumping Jacks",
            description = "",
            image = ""
        ),
        Exercise(
            id = 3,
            name = "Plank",
            description = "",
            image = ""
        )
    )

    fun getExercises(): List<Exercise> = exercises
}
