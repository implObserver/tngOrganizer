package com.example.tngorganizer.services.dao.etalon

import androidx.room.*
import com.example.tngorganizer.services.models.etalon.ProgramEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgramDao {
    @Insert
    suspend fun insert(program: ProgramEntity): Long

    @Update
    suspend fun update(program: ProgramEntity)

    @Delete
    suspend fun delete(program: ProgramEntity)

    @Query("SELECT * FROM programs WHERE id = :id")
    fun getProgramById(id: Long): Flow<ProgramEntity?>  // Изменено с Int на Long

    @Query("SELECT * FROM programs ORDER BY name ASC")
    fun getAllPrograms(): Flow<List<ProgramEntity>>
}
