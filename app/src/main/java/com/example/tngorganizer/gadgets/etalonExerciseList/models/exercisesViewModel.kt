package com.example.tngorganizer.gadgets.etalonExerciseList.models

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.ExerciseEntity
import com.example.tngorganizer.services.repositories.etalon.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val repository: ExerciseRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val programId: Long? = savedStateHandle["programId"]
    val workoutId: Long? = savedStateHandle["workoutId"]

    private val _exercises = MutableStateFlow<List<ExerciseEntity>>(emptyList())
    val exercises: StateFlow<List<ExerciseEntity>> = _exercises.asStateFlow()
    init {
        Log.e("ExerciseViewModel", "SavedStateHandle keys: ${savedStateHandle.keys()}")

        if (programId != null) {
            loadExercises(programId)
        } else {
            Log.e("ExerciseViewModel", "❌ programId is NULL in SavedStateHandle")
        }
    }

    private fun loadExercises(workoutId: Long) {
        viewModelScope.launch {
            repository.getAllExercisesByWorkout(workoutId)
                .collect { _exercises.value = it }
        }
    }
}


