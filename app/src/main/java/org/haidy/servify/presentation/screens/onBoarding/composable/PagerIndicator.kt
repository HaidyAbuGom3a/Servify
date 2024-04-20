package org.haidy.servify.presentation.screens.onBoarding.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.util.drawIndicator
import org.haidy.servify.presentation.util.getPageOffset

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    pagerState: PagerState,
    numOfPages: Int,
    width: Dp = 6.dp,
    height: Dp = 6.dp,
    circleSpacing: Dp = 8.dp,
    activeLineWidth: Dp = 24.dp,
    radius: CornerRadius = CornerRadius(10f,10f),
    selectedColor: Color = Theme.colors.primary100,
    unselectedColor: Color = Theme.colors.primary300,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    val rotationDegree = when (LocalLayoutDirection.current) {
        LayoutDirection.Ltr -> 0f
        LayoutDirection.Rtl -> 180f
    }
    Canvas(modifier = modifier.rotate(rotationDegree)) {
        val spacing = circleSpacing.toPx()
        val dotWidth = width.toPx()
        val dotHeight = height.toPx()

        val activeDotWidth = activeLineWidth.toPx()
        var x = 0f
        val y = center.y
        repeat(numOfPages) { i ->
            val posOffset = pagerState.getPageOffset()
            val dotOffset = posOffset % 1
            val current = posOffset.toInt()

            val factor = (dotOffset * (activeDotWidth - dotWidth))
            val color = if (i == current) selectedColor else unselectedColor
            val calculatedWidth = when {
                i == current -> activeDotWidth - factor
                i - 1 == current || (i == 0 && posOffset > numOfPages - 1) -> dotWidth + factor
                else -> dotWidth
            }

            drawIndicator(x, y, calculatedWidth, dotHeight, radius, color)
            x += calculatedWidth + spacing
        }
    }
}