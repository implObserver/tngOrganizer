package com.example.tngorganizer.services.repositories.exemplar

import com.example.tngorganizer.services.dao.exemplar.ScheduledWorkoutDao
import com.example.tngorganizer.services.models.exemplar.ScheduledWorkoutEntity
import com.example.tngorganizer.services.dao.exemplar.ScheduledWorkoutWithWorkout
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduledWorkoutRepository @Inject constructor(
    private val scheduledDao: ScheduledWorkoutDao
) {
    // ✅ Базовые операции
    suspend fun insertScheduledWorkout(scheduled: ScheduledWorkoutEntity): Long =
        scheduledDao.insert(scheduled)

    suspend fun deleteScheduledWorkout(scheduled: ScheduledWorkoutEntity) =
        scheduledDao.delete(scheduled)

    suspend fun updateScheduledWorkout(scheduled: ScheduledWorkoutEntity) =
        scheduledDao.update(scheduled)

    // ✅ Получить все по разным параметрам
    fun getAllScheduledWorkouts(): Flow<List<ScheduledWorkoutEntity>> =
        scheduledDao.getAll()

    fun getScheduledWorkoutsByDate(date: String): Flow<List<ScheduledWorkoutEntity>> =
        scheduledDao.getByDate(date)

    fun getScheduledWorkoutsByWorkoutId(workoutId: Int): Flow<List<ScheduledWorkoutEntity>> =
        scheduledDao.getByWorkoutId(workoutId)

    // ✅ Получить с вложенными Workout
    fun getScheduledWithWorkoutsByDate(date: String): Flow<List<ScheduledWorkoutWithWorkout>> =
        scheduledDao.getWithWorkoutsByDate(date)

    suspend fun getScheduledWorkoutById(id: Int): ScheduledWorkoutEntity? =
        scheduledDao.getById(id)

    suspend fun getScheduledWorkoutWithWorkout(id: Int): ScheduledWorkoutWithWorkout? =
        scheduledDao.getWithWorkout(id)
}
