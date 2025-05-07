package com.example.tngorganizer.widgets.etalonGroupOfWorkoutsItem.models

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import com.example.tngorganizer.services.models.etalon.WorkoutGroupEntity
import com.example.tngorganizer.services.repositories.etalon.WorkoutGroupRepository
import com.example.tngorganizer.services.repositories.etalon.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsOfGroupViewModel @Inject constructor(
    private val workoutsRepository: WorkoutRepository
) : ViewModel() {

    private val _workouts = MutableStateFlow<List<WorkoutEntity>>(emptyList())
    val workouts: StateFlow<List<WorkoutEntity>> = _workouts.asStateFlow()

    fun loadWorkoutsOfGroup(groupId: Long) {
        viewModelScope.launch {
            workoutsRepository.getAllWorkoutsByGroup(groupId).collect {
                _workouts.value = it
            }
        }
    }
}


