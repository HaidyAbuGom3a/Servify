package org.haidy.servify.presentation.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.modifier.noRippleEffect

@Composable
fun ItemService(
    onClick: () -> Unit,
    painter: Painter,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.noRippleEffect { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Theme.colors.accent300),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = title,
            style = Theme.typography.bodyLarge.copy(color = Theme.colors.contrast),
            modifier = Modifier.padding(top = 8.dp),

            )
    }
}