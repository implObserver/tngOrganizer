package com.example.tngorganizer.navigations.screens.exerciseScreen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.gadgets.etalonExerciseList.ui.EtalonExerciseList
import com.example.tngorganizer.navigations.screens.exerciseScreen.models.ExerciseScreenViewModel
import com.example.tngorganizer.navigations.screens.workoutScreen.models.WorkoutScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen(
    viewModel: ExerciseScreenViewModel = hiltViewModel(),
    onMainClick: () -> Unit
) {
    val workout by viewModel.workout.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { workout?.let { Text(it.name) } }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top, // изменил на Top для корректного скролла
            horizontalAlignment = Alignment.Start
        ) {
            Text("Мои упражнения:")
            Spacer(modifier = Modifier.height(8.dp))

            EtalonExerciseList();

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onMainClick) {
                Text("Перейти на главную")
            }
        }
    }
}