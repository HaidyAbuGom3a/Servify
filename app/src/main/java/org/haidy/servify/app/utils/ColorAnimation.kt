package org.haidy.servify.app.utils

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.haidy.servify.app.theme.Colors

@Composable
private fun animateColor(targetValue: Color) =
    animateColorAsState(
        targetValue = targetValue,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    ).value

@Composable
fun getAnimatedColorScheme(newColorScheme: Colors): Colors {
    return Colors(
        primary100 = animateColor(targetValue = newColorScheme.primary100),
        primary200 = animateColor(targetValue = newColorScheme.primary200),
        primary300 = animateColor(targetValue = newColorScheme.primary300),
        accent100 = animateColor(targetValue = newColorScheme.accent100),
        accent200 = animateColor(targetValue = newColorScheme.accent200),
        accent300 = animateColor(targetValue = newColorScheme.accent300),
        success100 = animateColor(targetValue = newColorScheme.success100),
        success200 = animateColor(targetValue = newColorScheme.success200),
        success300 = animateColor(targetValue = newColorScheme.success300),
        error100 = animateColor(targetValue = newColorScheme.error100),
        error200 = animateColor(targetValue = newColorScheme.error200),
        error300 = animateColor(targetValue = newColorScheme.error300),
        dark100 = animateColor(targetValue = newColorScheme.dark100),
        dark200 = animateColor(targetValue = newColorScheme.dark200),
        dark300 = animateColor(targetValue = newColorScheme.dark300),
        grey50 = animateColor(targetValue = newColorScheme.grey50),
        grey100 = animateColor(targetValue = newColorScheme.grey100),
        grey200 = animateColor(targetValue = newColorScheme.grey200),
        grey300 = animateColor(targetValue = newColorScheme.grey300),
        background = animateColor(targetValue = newColorScheme.background),
        backgroundGrey = animateColor(targetValue = newColorScheme.backgroundGrey),
        blue = animateColor(targetValue = newColorScheme.blue),
        card = animateColor(targetValue = newColorScheme.card),
        contrast = animateColor(targetValue = newColorScheme.contrast),
        black = animateColor(targetValue = newColorScheme.black),
        drawer = animateColor(targetValue = newColorScheme.drawer),
        cardGrey = animateColor(targetValue = newColorScheme.cardGrey),
        white = animateColor(targetValue = newColorScheme.white),
        cardChat = animateColor(targetValue = newColorScheme.cardChat)
    )
}