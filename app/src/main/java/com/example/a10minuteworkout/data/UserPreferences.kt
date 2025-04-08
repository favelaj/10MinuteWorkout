package com.example.a10minuteworkout.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val context: Context) {
    companion object {
        val IS_PROFILE_SETUP_COMPLETE = booleanPreferencesKey("is_profile_setup_complete")
        val COMPLETED_WORKOUTS = intPreferencesKey("completed_workouts")
        val COMPLETED_CARDIO_WORKOUTS = intPreferencesKey("completed_cardio_workouts")
        val COMPLETED_CORE_WORKOUTS = intPreferencesKey("completed_core_workouts")
        val COMPLETED_INTENSE_WORKOUTS = intPreferencesKey("completed_intense_workouts")
    }

    val isProfileSetupComplete: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_PROFILE_SETUP_COMPLETE] ?: false
        }

    val completedWorkouts: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[COMPLETED_WORKOUTS] ?: 0
        }

    val completedCardioWorkouts: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[COMPLETED_CARDIO_WORKOUTS] ?: 0 }

    val completedCoreWorkouts: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[COMPLETED_CORE_WORKOUTS] ?: 0 }

    val completedIntenseWorkouts: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[COMPLETED_INTENSE_WORKOUTS] ?: 0 }

    suspend fun completeProfileSetup() {
        context.dataStore.edit { preferences ->
            preferences[IS_PROFILE_SETUP_COMPLETE] = true
        }
    }

    suspend fun incrementCompletedWorkouts(workoutType: String?) {
        context.dataStore.edit { preferences ->
            preferences[COMPLETED_WORKOUTS] = (preferences[COMPLETED_WORKOUTS] ?: 0) + 1

            when (workoutType) {
                "Cardio" -> preferences[COMPLETED_CARDIO_WORKOUTS] = (preferences[COMPLETED_CARDIO_WORKOUTS] ?: 0) + 1
                "Core" -> preferences[COMPLETED_CORE_WORKOUTS] = (preferences[COMPLETED_CORE_WORKOUTS] ?: 0) + 1
                "Intense" -> preferences[COMPLETED_INTENSE_WORKOUTS] = (preferences[COMPLETED_INTENSE_WORKOUTS] ?: 0) + 1
            }

        }
    }
}