package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.MessageDto
import org.haidy.servify.domain.model.Message
import org.haidy.servify.domain.utils.toTimeStamp

fun MessageDto.toMessage(): Message{
    return Message(
        createdAt = createdAt?.toTimeStamp() ?: 0,
        senderId = senderId ?: "",
        receiverId = receiverId ?: "",
        text = text ?: ""
    )
}

