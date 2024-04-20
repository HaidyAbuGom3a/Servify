package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.UserLocationDto
import org.haidy.servify.domain.model.Location

fun UserLocationDto.toLocation(): Location {
    return Location(
        country = country ?: "",
        governorate = governorate ?: "",
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0
    )
}