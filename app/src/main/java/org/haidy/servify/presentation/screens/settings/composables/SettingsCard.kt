package org.haidy.servify.presentation.screens.settings.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.theme.localThemeMode
import org.haidy.servify.app.utils.getIsDarkTheme

@Composable
fun SettingsCard(
    items: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier
) {
    var isDark by remember{ mutableStateOf(false) }
    isDark = getIsDarkTheme(themeMode = localThemeMode.current)
    val elevation = if(isDark) 0.dp else 4.dp
    Surface(elevation = elevation, shape = RoundedCornerShape(Theme.radius.medium)) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(Theme.radius.medium))
                .background(Theme.colors.card)
                .padding(vertical = 16.dp)

        ) {
            repeat(items.size) { index ->
                items[index]()
            }
        }
    }
}
