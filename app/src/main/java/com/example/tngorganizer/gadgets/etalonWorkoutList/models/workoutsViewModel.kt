package com.example.tngorganizer.gadgets.etalonWorkoutList.models

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import com.example.tngorganizer.services.repositories.etalon.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsViewModel @Inject constructor(
    private val repository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val programId: Long? = savedStateHandle["programId"]
    private val _workouts = MutableStateFlow<List<WorkoutEntity>>(emptyList())
    val workouts: StateFlow<List<WorkoutEntity>> = _workouts.asStateFlow()
    init {
        Log.e("WorkoutViewModel", "SavedStateHandle keys: ${savedStateHandle.keys()}")

        if (programId != null) {
            loadWorkouts(programId)
        } else {
            Log.e("WorkoutViewModel", "❌ programId is NULL in SavedStateHandle")
        }
    }

    private fun loadWorkouts(programId: Long) {
        viewModelScope.launch {
            repository.getAllWorkoutsByProgram(programId)
                .collect { _workouts.value = it }
        }
    }
}


