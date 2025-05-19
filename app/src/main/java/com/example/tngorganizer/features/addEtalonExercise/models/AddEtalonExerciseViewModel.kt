package com.example.tngorganizer.features.addEtalonExercise.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.ExerciseEntity
import com.example.tngorganizer.services.repositories.etalon.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEtalonExerciseViewModel @Inject constructor(
    private val repository: ExerciseRepository
) : ViewModel() {

    fun addExercise(exercise: ExerciseEntity) {
        viewModelScope.launch {
            repository.insertExercise(exercise)
        }
    }
}