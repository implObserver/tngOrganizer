package com.example.tngorganizer.features.addEtalonWorkout.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.features.addEtalonWorkout.models.AddEtalonWorkoutViewModel
import com.example.tngorganizer.services.models.etalon.WorkoutEntity

@Composable
fun AddEtalonWorkoutInput(
    viewModel: AddEtalonWorkoutViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    val programId = viewModel.programId

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Тренировка") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        Button(
            onClick = {
                if (name.isNotBlank() && programId != null ) {
                    viewModel.addWorkout(WorkoutEntity(
                        programId = programId,
                        name = name
                    ))
                    name = "" // очищаем поле
                }
            },
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(48.dp) // Квадратная кнопка
        ) {
            Text("+")
        }
    }
}
