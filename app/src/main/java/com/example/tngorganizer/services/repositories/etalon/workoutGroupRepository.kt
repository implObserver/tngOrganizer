package com.example.tngorganizer.services.repositories.etalon

import com.example.tngorganizer.services.dao.etalon.WorkoutGroupDao
import com.example.tngorganizer.services.models.etalon.WorkoutGroupEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutGroupRepository @Inject constructor(
    private val workoutGroupDao: WorkoutGroupDao
) {
    fun getAllGroupsByProgram(programId: Long): Flow<List<WorkoutGroupEntity>> =
        workoutGroupDao.getAllGroupsByProgramId(programId)

    fun getGroupById(id: Long): Flow<WorkoutGroupEntity?> =
        workoutGroupDao.getGroupById(id)

    suspend fun insertGroup(group: WorkoutGroupEntity): Long =
        workoutGroupDao.insert(group)

    suspend fun updateGroup(group: WorkoutGroupEntity) =
        workoutGroupDao.update(group)

    suspend fun deleteGroup(group: WorkoutGroupEntity) =
        workoutGroupDao.delete(group)
}