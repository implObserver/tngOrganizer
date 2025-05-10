package com.example.tngorganizer.widgets.etalonGroupOfWorkoutsItem.components.item.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tngorganizer.features.addEtalonWorkoutInGroup.ui.AddEtalonWorkoutInGroupInput
import com.example.tngorganizer.widgets.etalonGroupOfWorkoutsItem.components.item.models.FactoryHolderViewModel
import com.example.tngorganizer.widgets.etalonGroupOfWorkoutsItem.components.item.models.WorkoutListViewModel

@Composable
fun WorkoutsOfGroupItem(groupId: Long) {
    val factoryProvider = hiltViewModel<FactoryHolderViewModel>().workoutListFactory
    val viewModel: WorkoutListViewModel = viewModel(
        key = "group_$groupId",
        factory = factoryProvider.create(groupId)
    )

    val workouts by viewModel.workouts.collectAsState()

    Column {
        workouts.forEach { workout ->
            Text(text = workout.name)
        }
        AddEtalonWorkoutInGroupInput(groupId)
    }
}