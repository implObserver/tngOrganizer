package com.example.tngorganizer.services.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tngorganizer.services.models.etalon.*
import com.example.tngorganizer.services.dao.etalon.*
import com.example.tngorganizer.services.dao.exemplar.*
import com.example.tngorganizer.services.models.exemplar.*

@Database(
    entities = [
        ProgramEntity::class,
        WorkoutEntity::class,
        ExerciseEntity::class,
        ScheduledWorkoutEntity::class,
        WorkoutExemplar::class,
        ExerciseExemplar::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun programDao(): ProgramDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun scheduledWorkoutDao(): ScheduledWorkoutDao
    abstract fun workoutExemplarDao():WorkoutExemplarDao
    abstract fun exerciseExemplarDao():ExerciseExemplarDao
}