package org.haidy.servify.presentation.screens.chatHistory

sealed class ChatHistoryUiEffect {
    object NavigateUp: ChatHistoryUiEffect()
    data class NavigateToChat(val otherUseId: String): ChatHistoryUiEffect()
}