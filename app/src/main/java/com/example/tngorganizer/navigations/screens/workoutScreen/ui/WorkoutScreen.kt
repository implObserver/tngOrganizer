package com.example.tngorganizer.navigations.screens.workoutScreen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.features.addEtalonGroupOfWorkouts.ui.AddEtalonGroupOfWorkoutsInput
import com.example.tngorganizer.features.addEtalonWorkout.ui.AddEtalonWorkoutInput
import com.example.tngorganizer.gadgets.etalonGroupOfWorkoutsList.ui.EtalonGroupOfWorkoutsList
import com.example.tngorganizer.gadgets.etalonWorkoutList.ui.EtalonWorkoutList
import com.example.tngorganizer.navigations.screens.workoutScreen.models.WorkoutScreenViewModel
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
    viewModel: WorkoutScreenViewModel = hiltViewModel(),
    onMainClick: () -> Unit
) {
    val program by viewModel.program.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { program?.let { Text(it.name) } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                AddEtalonGroupOfWorkoutsInput()
            }

            item {
                EtalonGroupOfWorkoutsList()
            }

            item {
                AddEtalonWorkoutInput()
            }

            item {
                EtalonWorkoutList()
            }
        }
    }
}