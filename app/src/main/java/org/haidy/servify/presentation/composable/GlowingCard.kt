package org.haidy.servify.presentation.composable

import android.graphics.Paint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.modifier.noRippleEffect

@Composable
fun GlowingCard(
    glowingColor: Color,
    modifier: Modifier = Modifier,
    containerColor: Color = Theme.colors.accent100,
    cornersRadius: Dp = Int.MAX_VALUE.dp,
    glowingRadius: Dp = 15.dp,
    xShifting: Dp = 0.dp,
    yShifting: Dp = glowingRadius / 2,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .drawBehind {
                val canvasSize = size
                drawContext.canvas.nativeCanvas.apply {
                    drawRoundRect(
                        0f, // Left
                        0f, // Top
                        canvasSize.width, // Right
                        canvasSize.height, // Bottom
                        cornersRadius.toPx(), // Radius X
                        cornersRadius.toPx(), // Radius Y
                        Paint().apply {
                            color = containerColor.toArgb()
                            isAntiAlias = true
                            setShadowLayer(
                                glowingRadius.toPx(),
                                xShifting.toPx(), yShifting.toPx(),
                                glowingColor.copy(alpha = 0.6f).toArgb()
                            )
                        }
                    )
                }
            }
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(cornersRadius))
                .noRippleEffect { onClick() },
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}