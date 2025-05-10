package com.example.tngorganizer.gadgets.etalonGroupOfWorkoutsList.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.gadgets.etalonGroupOfWorkoutsList.models.GroupsOfWorkoutsViewModel
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import com.example.tngorganizer.shared.lib.provides.LocalNavController
import com.example.tngorganizer.widgets.etalonWorkoutItem.ui.EtalonWorkoutItem
import com.example.tngorganizer.widgets.workoutsOfGroup.ui.WorkoutsOfGroup

@Composable
fun EtalonGroupOfWorkoutsList(
    viewModel: GroupsOfWorkoutsViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val groups by viewModel.groups.collectAsState()
    val programId = viewModel.programId
    Text(
        "Группы тренировок:",
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (groups.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), // Только горизонтальные отступы
                contentAlignment = Alignment.Center
            ) {
                Text("В вашу программу еще не добавлены группы тренировок")
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                groups.forEach { group ->
                    key(group.id) {
                        WorkoutsOfGroup<WorkoutEntity>(
                            group,
                            content = { workout ->
                                EtalonWorkoutItem(workout, programId, navController)
                            }
                        )
                    }
                }
            }
        }
    }
}