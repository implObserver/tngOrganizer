package com.example.tngorganizer.services.dao.etalon

import androidx.room.*
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insert(workout: WorkoutEntity): Long

    @Update
    suspend fun update(workout: WorkoutEntity)

    @Delete
    suspend fun delete(workout: WorkoutEntity)

    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkoutById(id: Long): Flow<WorkoutEntity?>  // Изменено на Long

    @Query("SELECT * FROM workouts WHERE programId = :programId ORDER BY name ASC")
    fun getAllWorkoutsByProgramId(programId: Long): Flow<List<WorkoutEntity>>  // Изменено на Long

    @Query("SELECT * FROM workouts WHERE groupId = :groupId ORDER BY name ASC")
    fun getAllWorkoutsByGroupId(groupId: Long): Flow<List<WorkoutEntity>>  // Изменено на Long
}
