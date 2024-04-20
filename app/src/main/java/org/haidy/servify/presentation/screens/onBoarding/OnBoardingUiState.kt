package org.haidy.servify.presentation.screens.onBoarding

import androidx.annotation.DrawableRes

data class OnBoardingUiState (
    val pages: List<OnBoardingPage> = emptyList(),
    val isFirstTimeUseApp: Boolean = false
)

data class OnBoardingPage(
    val title: String,
    val description: String,
    @DrawableRes val imageDrawable: Int
)