package com.example.tngorganizer.services.repositories.exemplar

import com.example.tngorganizer.services.dao.exemplar.ExerciseExemplarDao
import com.example.tngorganizer.services.models.exemplar.ExerciseExemplar
import com.example.tngorganizer.services.dao.exemplar.ExerciseExemplarWithTemplate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseExemplarRepository @Inject constructor(
    private val exemplarDao: ExerciseExemplarDao
) {
    // Получить все экземляры по ID тренировки
    fun getAllByWorkoutInstance(workoutInstanceId: Long): Flow<List<ExerciseExemplar>> =
        exemplarDao.getByWorkoutInstanceId(workoutInstanceId)

    // Получить по шаблону
    fun getAllByTemplateId(templateId: Long?): Flow<List<ExerciseExemplar>> =
        exemplarDao.getByTemplateId(templateId)

    // Вставка
    suspend fun insert(exemplar: ExerciseExemplar): Long = exemplarDao.insert(exemplar)

    // Обновление
    suspend fun update(exemplar: ExerciseExemplar) = exemplarDao.update(exemplar)

    // Удаление
    suspend fun delete(exemplar: ExerciseExemplar) = exemplarDao.delete(exemplar)

    // Получить по ID
    suspend fun getById(id: Long): ExerciseExemplar? = exemplarDao.getById(id)

    // Получить с шаблоном по ID
    suspend fun getWithTemplate(id: Long): ExerciseExemplarWithTemplate? =
        exemplarDao.getWithTemplate(id)

    // Получить все с шаблонами по экземпляру тренировки
    fun getWithTemplatesByWorkoutInstance(workoutInstanceId: Long): Flow<List<ExerciseExemplarWithTemplate>> =
        exemplarDao.getWithTemplatesByWorkoutInstance(workoutInstanceId)
}
