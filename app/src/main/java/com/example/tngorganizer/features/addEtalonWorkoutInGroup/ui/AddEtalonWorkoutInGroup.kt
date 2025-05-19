package com.example.tngorganizer.features.addEtalonWorkoutInGroup.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.features.addEtalonWorkoutInGroup.models.AddEtalonWorkoutInGroupViewModel
import com.example.tngorganizer.services.models.etalon.WorkoutEntity


@Composable
fun AddEtalonWorkoutInGroupInput(
    groupId: Long,
    viewModel: AddEtalonWorkoutInGroupViewModel = hiltViewModel()
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
                val normalizedName = name.trim().replace(Regex("\\s+"), " ")
                if (normalizedName.isNotBlank() && programId != null && groupId != null) {
                    viewModel.addWorkout(
                        WorkoutEntity(
                            programId = programId,
                            groupId = groupId,
                            name = normalizedName
                        )
                    )
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
