package com.example.tngorganizer.features.deleteGroup.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.WorkoutGroupEntity
import com.example.tngorganizer.services.repositories.etalon.WorkoutGroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteGroupViewModel @Inject constructor(
    private val repository: WorkoutGroupRepository
) : ViewModel() {

    fun deleteWorkout(group: WorkoutGroupEntity) {
        viewModelScope.launch {
            repository.deleteGroup(group)
        }
    }
}