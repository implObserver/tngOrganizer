package com.example.tngorganizer.features.addEtalonProgram.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.features.addEtalonProgram.models.AddEtalonProgramViewModel
import com.example.tngorganizer.services.models.etalon.ProgramEntity

@Composable
fun AddEtalonProgramInput(
    viewModel: AddEtalonProgramViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Название") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        Button(
            onClick = {
                if (name.isNotBlank()) {
                    viewModel.addProgram(ProgramEntity(name = name))
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
