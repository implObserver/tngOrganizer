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
    fun getExerciseById(id: Long): Flow<ExerciseEntity?>

    @Query("SELECT * FROM exercises WHERE workoutId = :workoutId ORDER BY name ASC")
    fun getAllExercisesByWorkout(workoutId: Long): Flow<List<ExerciseEntity>>
}