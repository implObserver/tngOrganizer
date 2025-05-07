package com.example.tngorganizer.services.dao.etalon

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tngorganizer.services.models.etalon.WorkoutGroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutGroupDao {
    @Insert
    suspend fun insert(group: WorkoutGroupEntity): Long

    @Update
    suspend fun update(group: WorkoutGroupEntity)

    @Delete
    suspend fun delete(group: WorkoutGroupEntity)

    @Query("SELECT * FROM workout_groups WHERE id = :id")
    fun getGroupById(id: Long): Flow<WorkoutGroupEntity?>

    @Query("SELECT * FROM workout_groups WHERE programId = :programId ORDER BY name ASC")
    fun getAllGroupsByProgramId(programId: Long): Flow<List<WorkoutGroupEntity>>
}