package com.example.tngorganizer.services.repositories.etalon

import com.example.tngorganizer.services.dao.etalon.ProgramDao
import com.example.tngorganizer.services.models.etalon.ProgramEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgramRepository @Inject constructor(
    private val programDao: ProgramDao
) {
    fun getAllPrograms(): Flow<List<ProgramEntity>> = programDao.getAllPrograms()

    suspend fun insertProgram(program: ProgramEntity) = programDao.insert(program)

    suspend fun deleteProgram(program: ProgramEntity) = programDao.delete(program)

    suspend fun updateProgram(program: ProgramEntity) = programDao.update(program)

    fun getProgramById(id: Long): Flow<ProgramEntity?> = programDao.getProgramById(id)
}