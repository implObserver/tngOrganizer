package com.example.tngorganizer.services.repositories.exemplar

import com.example.tngorganizer.services.dao.exemplar.WorkoutExemplarDao
import com.example.tngorganizer.services.models.exemplar.WorkoutExemplar
import com.example.tngorganizer.services.dao.exemplar.WorkoutExemplarWithScheduledWorkout
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutExemplarRepository @Inject constructor(
    private val dao: WorkoutExemplarDao
) {

    // CRUD
    suspend fun insert(workoutExemplar: WorkoutExemplar): Long {
        return dao.insert(workoutExemplar)
    }

    suspend fun update(workoutExemplar: WorkoutExemplar) {
        dao.update(workoutExemplar)
    }

    suspend fun delete(workoutExemplar: WorkoutExemplar) {
        dao.delete(workoutExemplar)
    }

    // Получение одного экземпляра по id
    suspend fun getById(id: Int): WorkoutExemplar? {
        return dao.getById(id)
    }

    // Получение всех по scheduledWorkoutId
    fun getByScheduledWorkoutId(scheduledWorkoutId: Int): Flow<List<WorkoutExemplar>> {
        return dao.getByScheduledWorkoutId(scheduledWorkoutId)
    }

    // Получение с отношениями (одного)
    suspend fun getWithScheduledWorkout(id: Int): WorkoutExemplarWithScheduledWorkout? {
        return dao.getWithScheduledWorkout(id)
    }

    // Получение с отношениями (списка)
    fun getWithScheduledWorkouts(scheduledWorkoutId: Int): Flow<List<WorkoutExemplarWithScheduledWorkout>> {
        return dao.getWithScheduledWorkouts(scheduledWorkoutId)
    }
}
