package com.example.tngorganizer.services.repositories.etalon

import com.example.tngorganizer.services.dao.etalon.ExerciseDao
import com.example.tngorganizer.services.dao.etalon.WorkoutDao
import com.example.tngorganizer.services.models.etalon.ExerciseEntity
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepository @Inject constructor(
    private val workoutDao: ExerciseDao
) {
    fun getAllExerciseByWorkout(exerciseId:Long): Flow<List<ExerciseEntity>> = workoutDao.getAllExercisesByWorkout(exerciseId)

    suspend fun insertWorkout(exercise: ExerciseEntity) = workoutDao.insert(exercise)

    suspend fun deleteWorkout(exercise: ExerciseEntity) = workoutDao.delete(exercise)

    suspend fun updateWorkout(exercise: ExerciseEntity) = workoutDao.update(exercise)

    fun getExerciseById(id: Long): Flow<ExerciseEntity?> = workoutDao.getExerciseById(id)
}