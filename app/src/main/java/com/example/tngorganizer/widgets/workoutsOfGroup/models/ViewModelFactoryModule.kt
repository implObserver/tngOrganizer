package com.example.tngorganizer.widgets.workoutsOfGroup.models

import com.example.tngorganizer.services.repositories.etalon.WorkoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelFactoryModule {

    @Provides
    @Singleton
    fun provideWorkoutListViewModelFactory(
        repository: WorkoutRepository
    ): WorkoutListViewModelFactory = WorkoutListViewModelFactory(repository)
}