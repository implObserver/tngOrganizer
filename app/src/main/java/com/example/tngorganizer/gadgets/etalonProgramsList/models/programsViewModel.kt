package com.example.tngorganizer.gadgets.etalonProgramsList.models
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.models.etalon.ProgramEntity
import com.example.tngorganizer.services.repositories.etalon.ProgramRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramViewModel @Inject constructor(
    private val repository: ProgramRepository
) : ViewModel() {

    private val _programs = MutableStateFlow<List<ProgramEntity>>(emptyList())
    val programs: StateFlow<List<ProgramEntity>> = _programs.asStateFlow()

    init {
        loadPrograms()
    }

    private fun loadPrograms() {
        viewModelScope.launch {
            repository.getAllPrograms()
                .collect { _programs.value = it }
        }
    }
}
