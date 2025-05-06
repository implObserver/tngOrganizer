package com.example.tngorganizer.features.deleteEtalonWorkout.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.Color
import com.example.tngorganizer.features.deleteEtalonWorkout.models.DeleteEtalonWorkoutViewModel
import com.example.tngorganizer.services.models.etalon.WorkoutEntity

@Composable
fun DeleteEtalonWorkout(
    workout: WorkoutEntity,
    viewModel: DeleteEtalonWorkoutViewModel = hiltViewModel(),
) {
    var showDialog by remember { mutableStateOf(false) }

    IconButton(onClick = {
        showDialog = true
    }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Удалить тренировку",
            tint = Color.Red
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Удалить тренировку?") },
            text = {
                Text("Вы уверены, что хотите удалить эту тренировку? Вся история занятий по ней тоже будет удалена.")
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteWorkout(workout)
                    showDialog = false
                }) {
                    Text("Да", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text("Нет")
                }
            }
        )
    }
}

