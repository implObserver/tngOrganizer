package com.example.tngorganizer.services.dao.etalon

import androidx.room.*
import com.example.tngorganizer.services.models.etalon.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(exercise: ExerciseEntity): Long

    @Update
    suspend fun update(exercise: ExerciseEntity)

    @Delete
    suspend fun delete(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun getById(id: Int): ExerciseEntity?

    @Query("SELECT * FROM exercises WHERE workoutId = :workoutId ORDER BY name ASC")
    fun getByWorkoutId(workoutId: Int): Flow<List<ExerciseEntity>>
}