package com.example.tngorganizer.entities.programItem.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tngorganizer.services.models.etalon.ProgramEntity

@Composable
fun ProgramItem(program: ProgramEntity) {
    Text(
        text = program.name,
        modifier = Modifier
            .padding(8.dp)
    )
}
