package org.haidy.servify.app

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val manageUser: UserUseCase) :
    BaseViewModel<MainUiState, String>(MainUiState()) {

    init {
        getIfUserIsLoggedIn()
        getIsFirstTimeUserApp()
        getLanguageCode()
        getThemeMode()
    }

    private fun getIfUserIsLoggedIn() {
        viewModelScope.launch {
            val isLoggedIn = viewModelScope.async { manageUser.getIfUserIsLoggedIn() }
            _state.update { it.copy(isLoggedIn = isLoggedIn.await()) }
        }
    }

    private fun getIsFirstTimeUserApp() {
        viewModelScope.launch {
            val isFirstTimeUseApp = viewModelScope.async { manageUser.getIfFirstTimeUseApp() }
            _state.update { it.copy(isFirstTimeUseApp = isFirstTimeUseApp.await()) }
        }
    }

    private fun getLanguageCode() {
        val languageCode = manageUser.getLanguageCode()
        Resources.switchLanguage(languageCode)
    }

    private fun getThemeMode() {
        viewModelScope.launch {
            val themeMode = async { manageUser.getThemeMode() }
            Theme.switchTheme(themeMode.await())
        }
    }
}

data class MainUiState(
    val isLoggedIn: Boolean = false,
    val isFirstTimeUseApp: Boolean = false,
)