package org.haidy.servify.presentation.screens.feedback

sealed class FeedbackUiEffect{
    object NavigateUp: FeedbackUiEffect()
    object ShowSuccessMessage: FeedbackUiEffect()
    object NavigateToHome: FeedbackUiEffect()
}
