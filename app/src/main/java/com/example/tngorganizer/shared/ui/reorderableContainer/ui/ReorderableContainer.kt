package com.example.tngorganizer.shared.ui.reorderableContainer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun <C> ReorderableContainer(
    items: List<C>,
    onReorder: (fromIndex: Int, toIndex: Int) -> Unit,
    currentlyDragged: C?,
    onDragStart: (C) -> Unit,
    onDragEnd: () -> Unit,
    content: @Composable (child: C) -> Unit
) {
    val dragOffsetY = remember { mutableStateOf(0f) }
    val currentPosition = remember { mutableStateOf(-1) }

    Column {
        items.forEachIndexed { index, child ->
            val isDragged = child == currentlyDragged

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isDragged)
                            Modifier
                                .offset { IntOffset(0, dragOffsetY.value.toInt()) }
                                .zIndex(1f)
                        else Modifier.zIndex(0f)
                    )
                    .pointerInput(child) {
                        detectDragGestures(
                            onDragStart = {
                                currentPosition.value = index
                                onDragStart(child)
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                dragOffsetY.value += dragAmount.y

                                val newIndex = (index + (dragOffsetY.value / 60f)).toInt().coerceIn(0, items.size - 1)
                                if (newIndex != currentPosition.value) {
                                    onReorder(index, newIndex)
                                    currentPosition.value = newIndex
                                }
                            },
                            onDragEnd = {
                                dragOffsetY.value = 0f
                                currentPosition.value = -1
                                onDragEnd()
                            }
                        )
                    }
            ) {
                content(child)
            }

            // Место-заполнитель для визуализации позиции вставки
            if (!isDragged && currentlyDragged != null && currentPosition.value == index) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                )
            }
        }
    }
}
