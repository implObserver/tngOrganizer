package com.example.tngorganizer.shared.lib.modules

import android.content.Context
import androidx.room.Room
import com.example.tngorganizer.services.dao.etalon.*
import com.example.tngorganizer.services.dao.exemplar.*
import com.example.tngorganizer.services.database.AppDatabase
import com.example.tngorganizer.services.repositories.etalon.*
import com.example.tngorganizer.services.repositories.exemplar.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    // --- Database ---
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tng_database"
        ).build()
    }

    // --- DAOs ---
    @Provides fun provideProgramDao(db: AppDatabase): ProgramDao = db.programDao()
    @Provides fun provideWorkoutDao(db: AppDatabase): WorkoutDao = db.workoutDao()
    @Provides fun provideWorkoutGroupDao(db: AppDatabase): WorkoutGroupDao = db.groupDao()
    @Provides fun provideExerciseDao(db: AppDatabase): ExerciseDao = db.exerciseDao()
    @Provides fun provideScheduledWorkoutDao(db: AppDatabase): ScheduledWorkoutDao = db.scheduledWorkoutDao()
    @Provides fun provideWorkoutExemplarDao(db: AppDatabase): WorkoutExemplarDao = db.workoutExemplarDao()
    @Provides fun provideExerciseExemplarDao(db: AppDatabase): ExerciseExemplarDao = db.exerciseExemplarDao()

    // --- Repositories ---
    @Provides
    @Singleton
    fun provideProgramRepository(programDao: ProgramDao): ProgramRepository =
        ProgramRepository(programDao)

    @Provides
    @Singleton
    fun provideWorkoutRepository(workoutDao: WorkoutDao): WorkoutRepository =
        WorkoutRepository(workoutDao)

    @Provides
    @Singleton
    fun provideWorkoutGroupRepository(workoutGroupDao: WorkoutGroupDao): WorkoutGroupRepository =
        WorkoutGroupRepository(workoutGroupDao)

    @Provides
    @Singleton
    fun provideExerciseRepository(exerciseDao: ExerciseDao): ExerciseRepository =
        ExerciseRepository(exerciseDao)

    @Provides
    @Singleton
    fun provideScheduledWorkoutRepository(scheduledWorkoutDao: ScheduledWorkoutDao): ScheduledWorkoutRepository =
        ScheduledWorkoutRepository(scheduledWorkoutDao)

    @Provides
    @Singleton
    fun provideWorkoutExemplarRepository(workoutExemplarDao: WorkoutExemplarDao): WorkoutExemplarRepository =
        WorkoutExemplarRepository(workoutExemplarDao)

    @Provides
    @Singleton
    fun provideExerciseExemplarRepository(exerciseExemplarDao: ExerciseExemplarDao): ExerciseExemplarRepository =
        ExerciseExemplarRepository(exerciseExemplarDao)
}

