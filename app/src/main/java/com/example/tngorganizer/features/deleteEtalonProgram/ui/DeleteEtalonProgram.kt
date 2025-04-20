package com.example.tngorganizer.features.deleteEtalonProgram.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.services.models.etalon.ProgramEntity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.Color
import com.example.tngorganizer.features.deleteEtalonProgram.models.DeleteEtalonProgramViewModel

@Composable
fun DeleteEtalonProgram(
    program: ProgramEntity,
    viewModel: DeleteEtalonProgramViewModel = hiltViewModel(),
) {
    var showDialog by remember { mutableStateOf(false) }

    IconButton(onClick = {
        showDialog = true
    }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Удалить программу",
            tint = Color.Red
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Удалить программу?") },
            text = {
                Text("Вы уверены, что хотите удалить эту программу? Вся история занятий по ней тоже будет удалена.")
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteProgram(program)
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

