package org.haidy.servify.presentation.composable

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import org.haidy.servify.app.navigation.Screen
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.theme.localThemeMode
import org.haidy.servify.app.utils.getIsDarkTheme

@Composable
fun ServifyNavItem(
    item: Screen,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val icon = if (isSelected) item.selectedIcon else item.unselectedIcon
    val isLightTheme = !getIsDarkTheme(localThemeMode.current)
    val tint =   if (isSelected) Theme.colors.accent100 else{
        if (isLightTheme) Theme.colors.accent200.copy(alpha = 0.7f) else Theme.colors.accent300
    }
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = tint
        )
    }
}