package com.example.tngorganizer.services.repositories.etalon

import com.example.tngorganizer.services.dao.etalon.ExerciseDao
import com.example.tngorganizer.services.models.etalon.ExerciseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) {
    fun getAllExercisesByWorkout(exerciseId:Long): Flow<List<ExerciseEntity>> = exerciseDao.getAllExercisesByWorkout(exerciseId)

    suspend fun insertExercise(exercise: ExerciseEntity) = exerciseDao.insert(exercise)

    suspend fun deleteExercise(exercise: ExerciseEntity) = exerciseDao.delete(exercise)

    suspend fun updateExercise(exercise: ExerciseEntity) = exerciseDao.update(exercise)

    fun getExerciseById(id: Long): Flow<ExerciseEntity?> = exerciseDao.getExerciseById(id)
}