package com.example.tngorganizer.gadgets.etalonWorkoutList.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.gadgets.etalonWorkoutList.models.WorkoutsViewModel
import com.example.tngorganizer.shared.lib.provides.LocalNavController
import com.example.tngorganizer.widgets.etalonWorkoutItem.ui.EtalonWorkoutItem

@Composable
fun EtalonWorkoutList(
    viewModel: WorkoutsViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val workouts by viewModel.workouts.collectAsState()
    val programId = viewModel.programId

    Text(
        "Тренировки:",
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (workouts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), // Только горизонтальные отступы
                contentAlignment = Alignment.Center
            ) {
                Text("В вашу программу еще не добавлены тренировки без группы")
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                workouts.forEach { workout ->
                    key(workout.id) {
                        EtalonWorkoutItem(workout, programId, navController)
                    }
                }
            }
        }
    }
}