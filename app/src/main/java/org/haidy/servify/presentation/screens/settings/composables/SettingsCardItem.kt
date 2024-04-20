package org.haidy.servify.presentation.screens.settings.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.modifier.noRippleEffect

@Composable
fun SettingsCardItem(
    onClick: () -> Unit,
    leadingIconPainter: Painter,
    text: String,
    trailingText: String? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    Row(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 4.dp)
            .noRippleEffect { onClick() }) {
        Icon(
            painter = leadingIconPainter,
            contentDescription = null,
            tint = Theme.colors.primary100
        )

        Text(
            text = text,
            style = Theme.typography.body.copy(color = Theme.colors.dark200),
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        if (trailingText != null) {
            Text(
                text = trailingText,
                style = Theme.typography.body.copy(color = Theme.colors.primary100),
            )
        }
    }
}