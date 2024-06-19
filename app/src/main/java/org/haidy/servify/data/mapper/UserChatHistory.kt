package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.UserChatHistoryDto
import org.haidy.servify.domain.model.UserChatHistory

fun UserChatHistoryDto.toUserChatHistory(
    otherUserName: String,
    otherUserImageUrl: String
): UserChatHistory {
    return UserChatHistory(
        chatId = chatId ?: "",
        lastMessage = lastMessage ?: "",
        updatedAt = updatedAt ?: 0,
        otherUserName = otherUserName,
        otherUserImageUrl = otherUserImageUrl,
        otherUserId = receiverId ?: ""
    )
}