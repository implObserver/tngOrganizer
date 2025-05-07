package com.example.tngorganizer.widgets.etalonGroupOfWorkoutsItem.components.item.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.widgets.etalonGroupOfWorkoutsItem.models.WorkoutsOfGroupViewModel

@Composable
fun WorkoutsOfGroupItem(groupId: Long) {
    val viewModel: WorkoutsOfGroupViewModel = hiltViewModel()

    // Загружаем только один раз
    LaunchedEffect(groupId) {
        viewModel.loadWorkoutsOfGroup(groupId)
    }

    val workoutsState = viewModel.workouts.collectAsState()
    val workouts = workoutsState.value

    // Здесь можно отображать список
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (workouts.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("В этой группе еще нет тренировок")
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(workouts) { workout ->
                    Text(workout.name)
                }
            }
        }
    }
}