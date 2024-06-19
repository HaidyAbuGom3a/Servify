package org.haidy.servify.presentation.screens.chatHistory

import org.haidy.servify.domain.model.UserChatHistory

data class ChatHistoryUiState(
    val chats: List<UserChatHistory> = emptyList()
)
