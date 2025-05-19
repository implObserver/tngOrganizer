package com.example.tngorganizer.shared.ui.longPressUnlocker.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

/**
 * Компонент, который полностью и навсегда блокирует все виды взаимодействия
 * с его содержимым (тапы, клики, скроллы, свайпы и любые другие жесты)
 */
@Composable
fun LongPressUnlocker(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                // Бесконечный цикл, который поглощает все pointer события
                while (true) {
                    awaitPointerEventScope {
                        val event = awaitPointerEvent()
                        // Явно поглощаем все изменения pointer событий
                        event.changes.forEach { change ->
                            change.consume()
                        }
                    }
                }
            }
    ) {
        content()
    }
}