package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.MessageDto
import org.haidy.servify.domain.model.Message

fun MessageDto.toMessage(): Message{
    return Message(
        createdAt = createdAt?.time ?: 0,
        senderId = senderId ?: "",
        receiverId = user2 ?: "",
        text = text ?: ""
    )
}

