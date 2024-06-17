package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.SpecialistResponse
import org.haidy.servify.domain.model.Location
import org.haidy.servify.domain.model.Service
import org.haidy.servify.domain.model.Specialist

fun SpecialistResponse.toSpecialist(): Specialist {
    return Specialist(
        id = specialist?.id ?: "",
        name = specialist?.name ?: "",
        service = getService(serviceName ?: ""),
        imageUrl = specialist?.image ?: "",
        location = specialist?.location?.toLocation() ?: Location("", "", 0.0, 0.0),
        rating = specialist?.rating?.toDouble() ?: 0.0,
    )
}

private fun getService(serviceName: String) = Service(
    id = "",
    name = serviceName,
    description = "",
    imageUrl = "",
    status = false,
    discount = 0.0
)