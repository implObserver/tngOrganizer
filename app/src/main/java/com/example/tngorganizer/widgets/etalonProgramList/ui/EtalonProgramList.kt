package com.example.tngorganizer.widgets.etalonProgramList.ui
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.tngorganizer.entities.programItem.ui.ProgramItem
import com.example.tngorganizer.services.models.etalon.ProgramEntity

@Composable
fun ProgramListWidget(programs: List<ProgramEntity>) {
    LazyColumn{
        items(programs) { program ->
            ProgramItem(program)
        }
    }
}