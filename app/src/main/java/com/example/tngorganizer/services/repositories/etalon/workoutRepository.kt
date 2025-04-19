package com.example.tngorganizer.services.repositories.etalon

import com.example.tngorganizer.services.dao.etalon.WorkoutDao
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepository @Inject constructor(
    private val workoutDao: WorkoutDao
) {
    fun getAllWorkoutsByProgram(programId:Long): Flow<List<WorkoutEntity>> = workoutDao.getAllWorkoutsByProgramId(programId)

    suspend fun insertWorkout(workout: WorkoutEntity) = workoutDao.insert(workout)

    suspend fun deleteWorkout(workout: WorkoutEntity) = workoutDao.delete(workout)

    suspend fun updateWorkout(workout: WorkoutEntity) = workoutDao.update(workout)

    fun getWorkoutById(id: Long): Flow<WorkoutEntity?> = workoutDao.getWorkoutById(id)
}