package com.example.tngorganizer.features.addEtalonProgram.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.ProgramEntity
import com.example.tngorganizer.services.repositories.etalon.ProgramRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEtalonProgramViewModel @Inject constructor(
    private val repository: ProgramRepository
) : ViewModel() {

    fun addProgram(program: ProgramEntity) {
        viewModelScope.launch {
            repository.insertProgram(program)
        }
    }
}