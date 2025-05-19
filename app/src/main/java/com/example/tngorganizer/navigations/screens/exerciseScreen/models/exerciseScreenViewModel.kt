package com.example.tngorganizer.navigations.screens.exerciseScreen.models

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.ProgramEntity
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import com.example.tngorganizer.services.repositories.etalon.ProgramRepository
import com.example.tngorganizer.services.repositories.etalon.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseScreenViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val workoutId: Long? = savedStateHandle["workoutId"]
    private val _workout = MutableStateFlow<WorkoutEntity?>(null)
    val workout: StateFlow<WorkoutEntity?> = _workout.asStateFlow()

    init {


        if (workoutId != null) {
            loadWorkout(workoutId)
        } else {

        }
    }

    private fun loadWorkout(workoutId: Long) {
        viewModelScope.launch {
            workoutRepository.getWorkoutById(workoutId)
                .collect { _workout.value = it }
        }
    }
}


