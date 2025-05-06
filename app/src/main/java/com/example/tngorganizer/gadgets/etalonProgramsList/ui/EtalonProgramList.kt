package com.example.tngorganizer.gadgets.etalonProgramsList.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tngorganizer.features.addEtalonProgram.ui.AddEtalonProgramInput
import com.example.tngorganizer.gadgets.etalonProgramsList.models.ProgramViewModel
import com.example.tngorganizer.shared.lib.provides.LocalNavController
import com.example.tngorganizer.widgets.etalonProgramItem.ui.EtalonProgramItem

@Composable
fun EtalonProgramsList(
    viewModel: ProgramViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val programs by viewModel.programs.collectAsState()

    if (programs.isEmpty()) {
        // Показываем сообщение, если программ нет
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "У вас ещё нет программ")
        }
    } else {
        // Показываем список, если есть хотя бы одна программа
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(programs) { program ->
                EtalonProgramItem(program = program, navController = navController)
            }
        }
    }
    AddEtalonProgramInput()
}
