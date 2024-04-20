package org.haidy.servify.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.screens.language.navigateToLanguage
import org.haidy.servify.presentation.screens.login.navigateToLogin
import org.haidy.servify.presentation.screens.settings.composables.SettingsCard
import org.haidy.servify.presentation.screens.settings.composables.ThemeAlertDialog
import org.haidy.servify.presentation.screens.settings.composables.getFirstSectionItems
import org.haidy.servify.presentation.screens.settings.composables.getSecondSectionItems
import org.haidy.servify.presentation.screens.settings.composables.getThirdSectionItems
import org.haidy.servify.presentation.screens.updatePassword.navigateToUpdatePassword
import org.haidy.servify.presentation.util.EffectHandler

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            SettingsUiEffect.NavigateToContactUs -> TODO()
            SettingsUiEffect.NavigateToEditProfile -> TODO()
            SettingsUiEffect.NavigateToHelpAndSupport -> TODO()
            SettingsUiEffect.NavigateToLanguage -> navController.navigateToLanguage()
            SettingsUiEffect.NavigateToNotifications -> TODO()
            SettingsUiEffect.NavigateToPrivacyPolicy -> TODO()
            SettingsUiEffect.NavigateToUpdatePassword -> navController.navigateToUpdatePassword()
            SettingsUiEffect.NavigateUp -> navController.popBackStack()
            is SettingsUiEffect.UpdateTheme -> Theme.switchTheme(effect.themeMode)
            SettingsUiEffect.NavigateToLogin -> navController.navigateToLogin(true)
        }
    }
    SettingsContent(listener = viewModel, state = state)
}

@Composable
fun SettingsContent(listener: SettingsInteractionListener, state: SettingsUiState) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.settings
            )
        }
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(it)
                .padding(top = 120.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            val firstSectionItems = getFirstSectionItems(
                listener = listener,
                isNotificationOn = state.isNotificationOn,
                languageCode = Resources.languageCode.value
            )
            val secondSectionItems = getSecondSectionItems(listener = listener, themeMode = state.themeMode)
            val thirdSectionItems = getThirdSectionItems(listener = listener)
            SettingsCard(items = firstSectionItems)
            SettingsCard(items = secondSectionItems)
            SettingsCard(items = thirdSectionItems)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (state.showAlertDialog) {
                    ThemeAlertDialog(listener = listener, state.themeMode)
                }
            }
        }
    }
}