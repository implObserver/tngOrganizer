package com.example.tngorganizer.widgets.etalonWorkoutItem.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tngorganizer.services.models.etalon.WorkoutEntity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.features.deleteEtalonWorkout.models.DeleteEtalonWorkoutViewModel
import com.example.tngorganizer.features.swipeToDeleteItem.ui.SwipeToDeleteItem
import com.example.tngorganizer.shared.ui.longPressUnlocker.ui.LongPressUnlocker

@Composable
fun EtalonWorkoutItem(
    workout: WorkoutEntity,
    programId: Long?,
    navController: NavController,
    viewModel: DeleteEtalonWorkoutViewModel = hiltViewModel()
) {
        SwipeToDeleteItem(
            item = workout,
            onDelete = { viewModel.deleteWorkout(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),

            swipeThreshold = 100.dp, // Чувствительность свайпа
            backgroundFillSpeed = 0.5f, // Чем больше, тем быстрее заполняется фон (экспериментируй)
            backgroundColorStart = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
            backgroundColorEnd = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
            deleteIcon = Icons.Default.Delete
        ) {
            Card(
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth(),

                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .clickable {
                            navController.navigate("program/${programId}/workouts/${workout.id}")
                        }

                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = workout.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "Перейти",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }

}