package com.example.tngorganizer.features.swipeToDeleteItem.ui

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

@Composable
fun <T> SwipeToDeleteItem(
    item: T,
    onDelete: (T) -> Unit,
    modifier: Modifier = Modifier,
    swipeThreshold: Dp = 100.dp,
    backgroundFillSpeed: Float = 1.5f,
    backgroundColorStart: Color = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
    backgroundColorEnd: Color = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
    deleteIcon: ImageVector,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val swipeThresholdPx = with(density) { swipeThreshold.toPx() }
    val offsetX = remember { Animatable(0f) }
    var isDeleted by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val vibrator = remember {
        context.getSystemService(android.os.Vibrator::class.java)
    }
    var hasVibrated by remember { mutableStateOf(false) }
    if (isDeleted) return

    val cornerRadius = 12.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        // progress и цвет фона
        val progress = (offsetX.value.absoluteValue / swipeThresholdPx * backgroundFillSpeed).coerceIn(0f, 1f)
        val backgroundColor = lerp(backgroundColorStart, backgroundColorEnd, progress)

        // Общая обёртка и background
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(cornerRadius))
                .background(backgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = deleteIcon,
                    contentDescription = "Удалить",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }

        // content
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(cornerRadius))
                .graphicsLayer {
                    translationX = offsetX.value
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()
                            val newOffset = offsetX.value + dragAmount.x
                            if (newOffset <= 0f) {
                                scope.launch {
                                    offsetX.snapTo(newOffset)
                                }
                                if (newOffset <= -swipeThresholdPx) {
                                    if (!hasVibrated) {
                                        hasVibrated = true
                                        vibrator?.let {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                it.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
                                            } else {
                                                @Suppress("DEPRECATION")
                                                it.vibrate(50)
                                            }
                                        }
                                    }
                                } else {
                                    // Если свайп "откатился" выше порога — сбрасываем флаг,
                                    // чтобы вибрация могла сработать снова при следующем достижении порога
                                    hasVibrated = false
                                }
                            }
                        },
                        onDragEnd = {
                            if (offsetX.value <= -swipeThresholdPx) {
                                scope.launch {
                                    offsetX.animateTo(-1000f, tween(300))
                                    isDeleted = true
                                    onDelete(item)
                                }
                            } else {
                                scope.launch {
                                    offsetX.animateTo(0f, tween(300))
                                }
                            }
                        },
                        onDragCancel = {
                            scope.launch {
                                offsetX.animateTo(0f, tween(300))
                            }
                        }
                    )
                }
        ) {
            // Измеряем размеры content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                content()
            }
        }
    }
}
/*
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> SwipeToDeleteItem(
    item: T,
    onDelete: (T) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val dismissState = rememberDismissState()

    // Следим за прогрессом свайпа и всегда сбрасываем состояние обратно в Default
    LaunchedEffect(dismissState.progress.fraction) {
        if (dismissState.progress.fraction < 1f) {
            // Принудительно сбрасываем в Default, если не достигнута полная отмена свайпа
            dismissState.snapTo(DismissValue.Default)
        }
    }

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart), // Свайп только в одну сторону
        background = {
            val progress = dismissState.progress.fraction.coerceIn(0f, 1f)
            val startColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
            val endColor = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
            val backgroundColor = lerp(startColor, endColor, progress)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.error.copy(alpha = 0.1f)) // постоянный бледный фон
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress + 0.5f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(backgroundColor) // плавное изменение фона
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Удалить",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        dismissContent = {
            content() // Контент остается без изменений
        },
        modifier = modifier
    )
}
*/
