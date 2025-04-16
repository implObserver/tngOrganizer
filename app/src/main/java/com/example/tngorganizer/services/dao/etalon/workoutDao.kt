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
    suspend fun getById(id: Int): WorkoutEntity?

    @Query("SELECT * FROM workouts WHERE programId = :programId ORDER BY name ASC")
    fun getByProgramId(programId: Int): Flow<List<WorkoutEntity>>
}
