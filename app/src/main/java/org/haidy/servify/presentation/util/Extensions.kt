package org.haidy.servify.presentation.util

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import java.text.SimpleDateFormat
import java.util.Date

fun DrawScope.drawIndicator(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radius: CornerRadius,
    color: Color
) {
    val rect = RoundRect(
        x,
        y - height / 2,
        x + width,
        y + height / 2,
        radius
    )
    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = color)
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.getPageOffset() =
    currentPage + currentPageOffsetFraction

@Composable
fun PaddingValues.sum(otherPaddingValues: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = this.calculateStartPadding(layoutDirection) + otherPaddingValues.calculateStartPadding(
            layoutDirection
        ),
        end = this.calculateEndPadding(layoutDirection) + otherPaddingValues.calculateEndPadding(
            layoutDirection
        ),
        top = this.calculateTopPadding() + otherPaddingValues.calculateTopPadding(),
        bottom = this.calculateBottomPadding() + otherPaddingValues.calculateBottomPadding()
    )
}

fun Modifier.bottomBorder(strokeWidth: Dp, color: Color, isVisible: Boolean = true) = composed(
    factory = {
        if (isVisible) {
            val density = LocalDensity.current
            val strokeWidthPx = density.run { strokeWidth.toPx() }

            Modifier.drawBehind {
                val width = size.width
                val height = size.height - strokeWidthPx / 2

                drawLine(
                    color = color,
                    start = Offset(x = 0f, y = height),
                    end = Offset(x = width, y = height),
                    strokeWidth = strokeWidthPx
                )
            }
        } else this
    }
)

@SuppressLint("SimpleDateFormat")
fun Long.toTimeString(): String {
    val formatter = SimpleDateFormat("hh:mm aa")
    formatter.timeZone = java.util.TimeZone.getDefault()
    val date = Date(this * 1000)
    return formatter.format(date)
}

@SuppressLint("SimpleDateFormat")
fun Long.toDateString(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    formatter.timeZone = java.util.TimeZone.getDefault()
    val date = Date(this * 1000)
    return formatter.format(date)
}
