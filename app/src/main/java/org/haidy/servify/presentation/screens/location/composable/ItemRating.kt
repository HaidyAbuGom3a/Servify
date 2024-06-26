package org.haidy.servify.presentation.screens.location.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.modifier.noRippleEffect

@Composable
fun ItemRating(
    rating: Double,
    modifier: Modifier = Modifier,
    showTextRating: Boolean = true,
    startSize: Int = 10,
    onClick: (Double) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (showTextRating) {
            Text(
                text = rating.toString(),
                style = Theme.typography.body,
                color = Theme.colors.contrast,
                modifier = Modifier.padding(end = 4.dp)
            )
        }
        repeat(rating.toInt()) {
            Icon(
                painter = painterResource(id = Resources.images.filledStartIcon),
                contentDescription = "",
                tint = Theme.colors.accent200,
                modifier = Modifier.noRippleEffect {
                    onClick(it.toDouble())
                }.size(startSize.dp)
            )
        }
        repeat(5 - rating.toInt()) {
            Icon(
                painter = painterResource(id = Resources.images.startIcon),
                contentDescription = "",
                tint = Theme.colors.grey100,
                modifier = Modifier.noRippleEffect {
                    onClick((rating.toInt() + it).toDouble())
                }.size(startSize.dp)
            )
        }

    }
}