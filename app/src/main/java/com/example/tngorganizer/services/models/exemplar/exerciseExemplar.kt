package com.example.tngorganizer.services.models.exemplar

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.tngorganizer.services.models.etalon.ExerciseEntity

@Entity(
    tableName = "exercise_exemplars",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutExemplar::class,
            parentColumns = ["id"],
            childColumns = ["workoutInstanceId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = ["id"],
            childColumns = ["templateId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("workoutInstanceId"), Index("templateId")]
)
data class ExerciseExemplar(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workoutInstanceId: Long,
    val templateId: Long?,
    val sets: Long,
    val reps: Long,
    val weight: Float,
    val comment: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
