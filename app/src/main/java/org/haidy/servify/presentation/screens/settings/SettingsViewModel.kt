package org.haidy.servify.presentation.screens.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.utils.ThemeMode
import org.haidy.servify.domain.usecase.AuthUseCase
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userManagement: UserUseCase,
    private val authManagement: AuthUseCase
) : BaseViewModel<SettingsUiState, SettingsUiEffect>(SettingsUiState()),
    SettingsInteractionListener {


    override fun onClickBackIcon() {
        sendNewEffect(SettingsUiEffect.NavigateUp)
    }

    override fun onClickEditProfile() {
        sendNewEffect(SettingsUiEffect.NavigateToEditProfile)
    }

    override fun onClickNotifications() {
        sendNewEffect(SettingsUiEffect.NavigateToNotifications)
    }

    override fun onClickLanguage() {
        sendNewEffect(SettingsUiEffect.NavigateToLanguage)
    }

    override fun onClickUpdatePassword() {
        sendNewEffect(SettingsUiEffect.NavigateToUpdatePassword)
    }

    override fun onClickTheme() {
        _state.update { it.copy(showAlertDialog = true) }
    }

    override fun onClickLogout() {
        tryToExecute(
            { authManagement.logout() },
            ::onClickLogoutSuccess,
            {}
        )
    }

    override fun onClickPrivacyPolicy() {
        sendNewEffect(SettingsUiEffect.NavigateToPrivacyPolicy)
    }

    override fun onClickHelpAndSupport() {
        sendNewEffect(SettingsUiEffect.NavigateToHelpAndSupport)
    }

    override fun onClickContactUs() {
        sendNewEffect(SettingsUiEffect.NavigateToContactUs)
    }

    override fun onClickConfirm() {
        tryToExecute(
            { userManagement.saveThemeMode(state.value.themeMode.code) },
            {
                _state.update { it.copy(showAlertDialog = false) }
                sendNewEffect(SettingsUiEffect.UpdateTheme(state.value.themeMode))
            },
            {}
        )
    }

    override fun onClickCancel() {
        _state.update { it.copy(showAlertDialog = false, themeMode = Theme.themeMode.value) }
    }

    override fun onClickSystemMode() {
        _state.update { it.copy(themeMode = ThemeMode.SYSTEM_THEME) }
    }

    override fun onClickLightMode() {
        _state.update { it.copy(themeMode = ThemeMode.LIGHT_THEME) }
    }

    override fun onClickDarkMode() {
        _state.update { it.copy(themeMode = ThemeMode.DARK_THEME) }
    }

    private fun onClickLogoutSuccess(unit: Unit) {
        sendNewEffect(SettingsUiEffect.NavigateToLogin)
    }
}