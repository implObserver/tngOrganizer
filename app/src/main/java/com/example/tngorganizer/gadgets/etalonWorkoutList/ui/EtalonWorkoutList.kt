package com.example.tngorganizer.gadgets.etalonWorkoutList.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.features.addEtalonProgram.ui.AddEtalonProgramInput
import com.example.tngorganizer.features.addEtalonWorkout.ui.AddEtalonWorkoutInput
import com.example.tngorganizer.gadgets.etalonWorkoutList.models.WorkoutsViewModel
import com.example.tngorganizer.shared.lib.provides.LocalNavController
import com.example.tngorganizer.widgets.etalonWorkoutItem.ui.EtalonWorkoutItem

@Composable
fun EtalonWorkoutList(
    viewModel:WorkoutsViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val workouts by viewModel.workouts.collectAsState()
    val programId = viewModel.programId

    if (workouts.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "В вашу тренировку еще не добавлены тренировки")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(workouts) { workout ->
                EtalonWorkoutItem(workout, programId, navController = navController)
            }
        }
    }
    AddEtalonWorkoutInput()
}