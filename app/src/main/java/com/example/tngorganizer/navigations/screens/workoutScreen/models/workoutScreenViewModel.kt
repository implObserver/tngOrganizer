package com.example.tngorganizer.navigations.screens.workoutScreen.models

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.ProgramEntity
import com.example.tngorganizer.services.models.etalon.WorkoutGroupEntity
import com.example.tngorganizer.services.repositories.etalon.ProgramRepository
import com.example.tngorganizer.services.repositories.etalon.WorkoutGroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutScreenViewModel @Inject constructor(
    private val programRepository: ProgramRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val programId: Long? = savedStateHandle["programId"]
    private val _program = MutableStateFlow<ProgramEntity?>(null)
    val program: StateFlow<ProgramEntity?> = _program.asStateFlow()

    init {
        Log.e("WorkoutViewModel", "SavedStateHandle keys: ${savedStateHandle.keys()}")

        if (programId != null) {
            loadProgram(programId)
        } else {
            Log.e("WorkoutViewModel", "❌ programId is NULL in SavedStateHandle")
        }
    }

    private fun loadProgram(programId: Long) {
        viewModelScope.launch {
            programRepository.getProgramById(programId)
                .collect { _program.value = it }
        }
    }
}


