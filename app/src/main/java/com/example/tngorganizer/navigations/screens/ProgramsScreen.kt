package com.example.tngorganizer.navigations.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tngorganizer.gadgets.etalonProgramsOrganizer.ui.EtalonProgramsOrganizer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramsScreen(onMainClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Главный экран") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top, // изменил на Top для корректного скролла
            horizontalAlignment = Alignment.Start
        ) {
            Text("Мои программы:")
            Spacer(modifier = Modifier.height(8.dp))

            EtalonProgramsOrganizer() // 👈 Добавили виджет программ сюда

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onMainClick) {
                Text("Перейти на главную")
            }
        }
    }
}
