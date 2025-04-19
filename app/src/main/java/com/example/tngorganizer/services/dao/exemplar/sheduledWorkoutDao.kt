package com.example.tngorganizer.services.dao.exemplar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import com.example.tngorganizer.services.models.exemplar.ScheduledWorkoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduledWorkoutDao {
    // Основные CRUD операции
    @Insert
    suspend fun insert(scheduledWorkout: ScheduledWorkoutEntity): Long

    @Update
    suspend fun update(scheduledWorkout: ScheduledWorkoutEntity)

    @Delete
    suspend fun delete(scheduledWorkout: ScheduledWorkoutEntity)

    // Запросы
    @Query("SELECT * FROM scheduled_workouts WHERE id = :id")
    suspend fun getById(id: Int): ScheduledWorkoutEntity?

    @Query("SELECT * FROM scheduled_workouts WHERE date = :date")
    fun getByDate(date: String): Flow<List<ScheduledWorkoutEntity>>

    @Query("SELECT * FROM scheduled_workouts WHERE workoutId = :workoutId")
    fun getByWorkoutId(workoutId: Int): Flow<List<ScheduledWorkoutEntity>>

    @Query("SELECT * FROM scheduled_workouts ORDER BY date ASC")
    fun getAll(): Flow<List<ScheduledWorkoutEntity>>

    // Сложные запросы с отношениями
    @Transaction
    @Query("SELECT * FROM scheduled_workouts WHERE id = :id")
    suspend fun getWithWorkout(id: Int): ScheduledWorkoutWithWorkout?

    @Transaction
    @Query("SELECT * FROM scheduled_workouts WHERE date = :date")
    fun getWithWorkoutsByDate(date: String): Flow<List<ScheduledWorkoutWithWorkout>>
}

data class ScheduledWorkoutWithWorkout(
    @Embedded val scheduledWorkout: ScheduledWorkoutEntity,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "id"
    )
    val workout: WorkoutEntity
)