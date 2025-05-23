package com.example.tngorganizer.widgets.etalonProgramItem.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tngorganizer.features.deleteEtalonProgram.ui.DeleteEtalonProgram
import com.example.tngorganizer.services.models.etalon.ProgramEntity

@Composable
fun EtalonProgramItem(
    program: ProgramEntity,
    navController: NavController
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable {
                navController.navigate("program/${program.id}/workouts")
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = program.name,
            modifier = Modifier.weight(1f)
        )
        DeleteEtalonProgram(program = program)
    }
}
