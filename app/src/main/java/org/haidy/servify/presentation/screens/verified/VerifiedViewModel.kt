package org.haidy.servify.presentation.screens.verified

import dagger.hilt.android.lifecycle.HiltViewModel
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class VerifiedViewModel @Inject constructor() : BaseViewModel<Unit, VerifiedUiEffect>(Unit),
    VerifiedInteractionListener {
    override fun onClickExplore() {
        sendNewEffect(VerifiedUiEffect.NavigateToHome)
    }
}