package com.example.tngorganizer.widgets.workoutsOfGroup.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import com.example.tngorganizer.services.repositories.etalon.WorkoutRepository
import kotlinx.coroutines.flow.*

class WorkoutListViewModel(
    private val groupId: Long,
    private val repository: WorkoutRepository
) : ViewModel() {

    val workouts: StateFlow<List<WorkoutEntity>> =
        repository.getAllWorkoutsByGroup(groupId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
