package com.example.tngorganizer.features.deleteEtalonProgram.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.ProgramEntity
import com.example.tngorganizer.services.repositories.etalon.ProgramRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteEtalonProgramViewModel @Inject constructor(
    private val repository: ProgramRepository
) : ViewModel() {

    fun deleteProgram(program: ProgramEntity) {
        viewModelScope.launch {
            repository.deleteProgram(program)
        }
    }
}