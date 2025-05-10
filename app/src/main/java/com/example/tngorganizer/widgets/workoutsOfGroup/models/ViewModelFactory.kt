package com.example.tngorganizer.widgets.workoutsOfGroup.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tngorganizer.services.repositories.etalon.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class WorkoutListViewModelFactory @Inject constructor(
    private val repository: WorkoutRepository
) {
    fun create(groupId: Long): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(WorkoutListViewModel::class.java)) {
                    return WorkoutListViewModel(groupId, repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}

@HiltViewModel
class FactoryHolderViewModel @Inject constructor(
    val workoutListFactory: WorkoutListViewModelFactory
) : ViewModel()