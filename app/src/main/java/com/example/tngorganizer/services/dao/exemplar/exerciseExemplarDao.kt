package com.example.tngorganizer.services.dao.exemplar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.example.tngorganizer.services.models.etalon.ExerciseEntity
import com.example.tngorganizer.services.models.exemplar.ExerciseExemplar
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseExemplarDao {
    // Основные CRUD операции
    @Insert
    suspend fun insert(exerciseExemplar: ExerciseExemplar): Long

    @Update
    suspend fun update(exerciseExemplar: ExerciseExemplar)

    @Delete
    suspend fun delete(exerciseExemplar: ExerciseExemplar)

    // Запросы
    @Query("SELECT * FROM exercise_exemplars WHERE id = :id")
    suspend fun getById(id: Long): ExerciseExemplar?  // Изменено на Long

    @Query("SELECT * FROM exercise_exemplars WHERE workoutInstanceId = :workoutInstanceId")
    fun getByWorkoutInstanceId(workoutInstanceId: Long): Flow<List<ExerciseExemplar>>  // Изменено на Long

    @Query("SELECT * FROM exercise_exemplars WHERE templateId = :templateId")
    fun getByTemplateId(templateId: Long?): Flow<List<ExerciseExemplar>>  // Изменено на Long

    // Сложные запросы с отношениями
    @Transaction
    @Query("SELECT * FROM exercise_exemplars WHERE id = :id")
    suspend fun getWithTemplate(id: Long): ExerciseExemplarWithTemplate?  // Изменено на Long

    @Transaction
    @Query("SELECT * FROM exercise_exemplars WHERE workoutInstanceId = :workoutInstanceId")
    fun getWithTemplatesByWorkoutInstance(workoutInstanceId: Long): Flow<List<ExerciseExemplarWithTemplate>>  // Изменено на Long
}

data class ExerciseExemplarWithTemplate(
    @Embedded val exemplar: ExerciseExemplar,
    @Relation(
        parentColumn = "templateId",
        entityColumn = "id"
    )
    val template: ExerciseEntity?
)