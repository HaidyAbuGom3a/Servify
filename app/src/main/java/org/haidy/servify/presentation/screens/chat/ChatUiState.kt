package org.haidy.servify.presentation.screens.chat

import org.haidy.servify.domain.model.Message

data class ChatUiState(
    val text: String = "",
    val messages: List<Message> = emptyList(),
    val chatId: String = "",
    val userId: String = "",
    val userImageUrl: String = "",
    val otherUserImageUrl: String = "",
    val otherUserName: String = ""
)
