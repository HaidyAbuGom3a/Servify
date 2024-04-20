package org.haidy.servify.presentation.screens.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.utils.LanguageCode
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.util.EffectHandler

@Composable
fun LanguageScreen(viewModel: LanguageViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            LanguageUiEffect.NavigateUp -> navController.popBackStack()
            is LanguageUiEffect.UpdateLanguage -> Resources.switchLanguage(effect.language)
        }
    }
    LanguageContent(viewModel, state)
}

@Composable
fun LanguageContent(listener: LanguageInteractionListener, state: LanguageUiState) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.language
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(paddingValues)
        ) {
            Text(
                text = Resources.strings.suggested,
                style = Theme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Theme.colors.dark100
                ),
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, bottom = 16.dp)
            )

            val radioOptions = listOf(LanguageCode.AR, LanguageCode.EG, LanguageCode.EN)
            radioOptions.forEach { language ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .noRippleEffect {
                            when (language) {
                                LanguageCode.AR -> listener.onClickArabic()
                                LanguageCode.EG -> listener.onClickEgyptian()
                                LanguageCode.EN -> listener.onClickEnglish()
                            }
                        }
                ) {
                    val text = when (language) {
                        LanguageCode.AR -> Resources.strings.arabic
                        LanguageCode.EG -> Resources.strings.egyptian
                        LanguageCode.EN -> Resources.strings.english
                    }
                    Text(
                        text = text,
                        style = Theme.typography.bodyLarge.copy(Theme.colors.dark100),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    RadioButton(
                        selected = (language == state.language),
                        onClick = {
                            when (language) {
                                LanguageCode.AR -> listener.onClickArabic()
                                LanguageCode.EG -> listener.onClickEgyptian()
                                LanguageCode.EN -> listener.onClickEnglish()
                            }
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Theme.colors.blue,
                            unselectedColor = Theme.colors.blue
                        )
                    )
                }

            }
        }
    }
}