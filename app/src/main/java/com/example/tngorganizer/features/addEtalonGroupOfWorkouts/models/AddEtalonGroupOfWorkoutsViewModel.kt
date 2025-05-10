package com.example.tngorganizer.features.addEtalonProgram.models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.WorkoutGroupEntity
import com.example.tngorganizer.services.repositories.etalon.WorkoutGroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEtalonGroupOfWorkoutsViewModel @Inject constructor(
    private val repository: WorkoutGroupRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val programId: Long? = savedStateHandle["programId"]
    fun addGroup(group: WorkoutGroupEntity) {
        viewModelScope.launch {
            repository.insertGroup(group)
        }
    }
}