package org.haidy.servify.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.theme.Theme

@Composable
fun ServifyOutlinedButton(
    onClick: () -> Unit,
    text: String,
    hasIcon: Boolean = false,
    iconPainter: Painter? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        { onClick() },
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Theme.colors.accent100,
                shape = RoundedCornerShape(Theme.radius.large)
            ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Theme.colors.accent100,
        ),
        shape = RoundedCornerShape(Theme.radius.large),
        enabled = enabled
    ) {
        Text(text, style = Theme.typography.titleLarge, fontWeight = FontWeight.Bold)
        AnimatedVisibility(visible = hasIcon) {
            Icon(painter = iconPainter!!, contentDescription = null)
        }
    }
}

@Composable
@Preview(showSystemUi = false)
private fun PreviewServifyOutlinedButton() {
    ServifyOutlinedButton(onClick = { }, "Get Started")
}