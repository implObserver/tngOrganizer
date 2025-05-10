package com.example.tngorganizer.services.dao.exemplar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.example.tngorganizer.services.models.exemplar.ScheduledWorkoutEntity
import com.example.tngorganizer.services.models.exemplar.WorkoutExemplar
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutExemplarDao {
    // Основные CRUD операции
    @Insert
    suspend fun insert(workoutExemplar: WorkoutExemplar): Long

    @Update
    suspend fun update(workoutExemplar: WorkoutExemplar)

    @Delete
    suspend fun delete(workoutExemplar: WorkoutExemplar)

    // Запросы
    @Query("SELECT * FROM workout_exemplars WHERE id = :id")
    suspend fun getById(id: Long): WorkoutExemplar?  // Changed to Long

    @Query("SELECT * FROM workout_exemplars WHERE scheduledWorkoutId = :scheduledWorkoutId")
    fun getByScheduledWorkoutId(scheduledWorkoutId: Long): Flow<List<WorkoutExemplar>>  // Changed to Long

    // Сложные запросы с отношениями
    @Transaction
    @Query("SELECT * FROM workout_exemplars WHERE id = :id")
    suspend fun getWithScheduledWorkout(id: Long): WorkoutExemplarWithScheduledWorkout?  // Changed to Long

    @Transaction
    @Query("SELECT * FROM workout_exemplars WHERE scheduledWorkoutId = :scheduledWorkoutId")
    fun getWithScheduledWorkouts(scheduledWorkoutId: Long): Flow<List<WorkoutExemplarWithScheduledWorkout>>  // Changed to Long
}

data class WorkoutExemplarWithScheduledWorkout(
    @Embedded val exemplar: WorkoutExemplar,
    @Relation(
        parentColumn = "scheduledWorkoutId",
        entityColumn = "id"
    )
    val scheduledWorkout: ScheduledWorkoutEntity
)