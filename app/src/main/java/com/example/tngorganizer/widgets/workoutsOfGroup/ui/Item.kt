package com.example.tngorganizer.widgets.workoutsOfGroup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tngorganizer.features.addEtalonWorkoutInGroup.ui.AddEtalonWorkoutInGroupInput
import com.example.tngorganizer.features.deleteEtalonWorkout.models.DeleteEtalonWorkoutViewModel
import com.example.tngorganizer.features.deleteGroup.models.DeleteGroupViewModel
import com.example.tngorganizer.features.swipeToDeleteItem.ui.SwipeToDeleteItem
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import com.example.tngorganizer.services.models.etalon.WorkoutGroupEntity
import com.example.tngorganizer.shared.ui.expandableGroup.ui.ExpandableGroup
import com.example.tngorganizer.widgets.workoutsOfGroup.models.FactoryHolderViewModel
import com.example.tngorganizer.widgets.workoutsOfGroup.models.WorkoutListViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun <C> WorkoutsOfGroup(
    group: WorkoutGroupEntity,
    deleteViewModel: DeleteGroupViewModel = hiltViewModel(),
    content: @Composable (child: WorkoutEntity) -> Unit
) {
    val factoryProvider = hiltViewModel<FactoryHolderViewModel>().workoutListFactory
    val viewModel: WorkoutListViewModel = viewModel(
        key = "group_$group.id",
        factory = factoryProvider.create(group.id)
    )

    val workouts by viewModel.workouts.collectAsState()

    Column {
            ExpandableGroup(
                item = group,
                idProvider = { it.id },
                nameProvider = { it.name },
                children = workouts, // List<ExerciseEntity>
                content = { workout ->
                    content(workout) // Ваш компонент для элемента списка
                },
                trailingContent = {
                    AddEtalonWorkoutInGroupInput(group.id) // Кнопка добавления
                }
            )
    }
}