package org.haidy.servify.presentation.screens.onBoarding

import org.haidy.servify.presentation.base.BaseUiEffect

sealed class OnBoardingUiEffect: BaseUiEffect{
    object NavigateToLogin: OnBoardingUiEffect()
}
