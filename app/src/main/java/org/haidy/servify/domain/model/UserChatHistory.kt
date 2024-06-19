package org.haidy.servify.domain.model

data class UserChatHistory(
    val chatId: String,
    val lastMessage: String,
    val otherUserImageUrl: String,
    val otherUserName: String,
    val updatedAt: Long,
    val otherUserId: String
)
