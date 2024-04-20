package org.haidy.servify.presentation.screens.language

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.app.utils.LanguageCode
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val userManagement: UserUseCase
) : BaseViewModel<LanguageUiState, LanguageUiEffect>(LanguageUiState()),
    LanguageInteractionListener {

    init {
        getLanguage()
    }

    override fun onClickBackIcon() {
        sendNewEffect(LanguageUiEffect.NavigateUp)
    }

    override fun onClickArabic() {
        _state.update { it.copy(language = LanguageCode.AR) }
        saveLanguage()
    }

    override fun onClickEnglish() {
        _state.update { it.copy(language = LanguageCode.EN) }
        saveLanguage()
    }

    override fun onClickEgyptian() {
        _state.update { it.copy(language = LanguageCode.EG) }
        saveLanguage()
    }

    private fun getLanguage() {
        tryToExecute(
            { userManagement.getLanguageCode() },
            { languageCode -> _state.update { it.copy(language = languageCode) } },
            {}
        )
    }

    private fun saveLanguage() {
        tryToExecute(
            { userManagement.saveLanguageCode(state.value.language.code) },
            { sendNewEffect(LanguageUiEffect.UpdateLanguage(state.value.language)) },
            {}
        )
    }
}