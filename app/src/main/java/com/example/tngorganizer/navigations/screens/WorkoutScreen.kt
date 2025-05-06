package com.example.tngorganizer.navigations.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("Мои тренировки:")
            Spacer(modifier = Modifier.height(8.dp))

            EtalonWorkoutList();

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onMainClick) {
                Text("Перейти на главную")
            }
        }
    }
}