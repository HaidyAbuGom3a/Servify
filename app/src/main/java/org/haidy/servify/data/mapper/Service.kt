package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.ServiceDto
import org.haidy.servify.domain.model.Service

fun ServiceDto.toService(): Service {
    return Service(
        id = id.toString(),
        name = name ?: "",
        description = description ?: "",
        imageUrl = image ?: "",
        status = status?.lowercase() == "true"
    )
}