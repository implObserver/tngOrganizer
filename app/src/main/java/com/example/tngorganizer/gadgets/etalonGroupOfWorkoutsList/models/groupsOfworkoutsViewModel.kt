package com.example.tngorganizer.gadgets.etalonGroupOfWorkoutsList.models

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
class GroupsOfWorkoutsViewModel @Inject constructor(
    private val groupsRepository: WorkoutGroupRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val programId: Long? = savedStateHandle["programId"]

    private val _groups = MutableStateFlow<List<WorkoutGroupEntity>>(emptyList())
    val groups: StateFlow<List<WorkoutGroupEntity>> = _groups.asStateFlow()
    init {
        Log.e("WorkoutViewModel", "SavedStateHandle keys: ${savedStateHandle.keys()}")

        if (programId != null) {
            loadGroupsOfWorkouts(programId)
        } else {
            Log.e("WorkoutViewModel", "❌ programId is NULL in SavedStateHandle")
        }
    }

    private fun loadGroupsOfWorkouts(programId: Long) {
        viewModelScope.launch {
            groupsRepository.getAllGroupsByProgram(programId)
                .collect { _groups.value = it }
        }
    }
}


