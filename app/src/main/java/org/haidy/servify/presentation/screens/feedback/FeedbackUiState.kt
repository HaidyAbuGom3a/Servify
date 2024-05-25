package org.haidy.servify.presentation.screens.feedback

data class FeedbackUiState(
    val serviceName: String = "",
    val specialistName: String = "",
    val specialistUrl: String = "",
    val rating: Double = 1.0,
    val feedback: String = ""
)
