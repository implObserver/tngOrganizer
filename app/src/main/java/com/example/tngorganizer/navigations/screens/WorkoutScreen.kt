package com.example.tngorganizer.navigations.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.tngorganizer.features.addEtalonGroupOfWorkouts.ui.AddEtalonGroupOfWorkoutsInput
import com.example.tngorganizer.features.addEtalonWorkout.ui.AddEtalonWorkoutInput
import com.example.tngorganizer.gadgets.etalonGroupOfWorkoutsList.ui.EtalonGroupOfWorkoutsList
import com.example.tngorganizer.gadgets.etalonWorkoutList.ui.EtalonWorkoutList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(onMainClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Тренировки") }
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