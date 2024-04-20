package org.haidy.servify.presentation.screens.settings.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.utils.ThemeMode
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.settings.SettingsInteractionListener

@Composable
fun ThemeAlertDialog(
    listener: SettingsInteractionListener,
    themeMode: ThemeMode
) {
    val radioOptions = listOf(ThemeMode.SYSTEM_THEME, ThemeMode.LIGHT_THEME, ThemeMode.DARK_THEME)
    Dialog(onDismissRequest = { listener.onClickCancel() }) {
        Card(
            shape = RoundedCornerShape(Theme.radius.large),
            colors = CardDefaults.cardColors(Theme.colors.card),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Text(
                text = Resources.strings.chooseTheme,
                style = Theme.typography.headline.copy(Theme.colors.dark100),
                modifier = Modifier.padding(top = 24.dp, start = 24.dp, bottom = 16.dp)
            )

            radioOptions.forEach { theme ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .noRippleEffect {
                            when (theme) {
                                ThemeMode.SYSTEM_THEME -> listener.onClickSystemMode()
                                ThemeMode.LIGHT_THEME -> listener.onClickLightMode()
                                ThemeMode.DARK_THEME -> listener.onClickDarkMode()
                            }
                        }
                ) {
                    RadioButton(
                        selected = (theme == themeMode),
                        onClick = {
                            when (theme) {
                                ThemeMode.SYSTEM_THEME -> listener.onClickSystemMode()
                                ThemeMode.LIGHT_THEME -> listener.onClickLightMode()
                                ThemeMode.DARK_THEME -> listener.onClickDarkMode()
                            }
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Theme.colors.blue,
                            unselectedColor = Theme.colors.blue
                        )
                    )
                    val selectedText = when (theme) {
                        ThemeMode.SYSTEM_THEME -> Resources.strings.systemMode
                        ThemeMode.LIGHT_THEME -> Resources.strings.lightMode
                        ThemeMode.DARK_THEME -> Resources.strings.darkMode
                    }

                    Text(
                        text = selectedText,
                        style = Theme.typography.bodyLarge.copy(Theme.colors.dark100),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = {
                        listener.onClickCancel()
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(
                        Resources.strings.cancel,
                        style = Theme.typography.title.copy(color = Theme.colors.blue)
                    )
                }

                TextButton(
                    onClick = {
                        listener.onClickConfirm()
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        Resources.strings.confirm,
                        style = Theme.typography.title.copy(color = Theme.colors.blue)
                    )
                }
            }

        }
    }

}