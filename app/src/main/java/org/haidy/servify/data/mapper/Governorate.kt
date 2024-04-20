package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.GovernorateDto
import org.haidy.servify.domain.model.Governorate

fun GovernorateDto.toGovernorate() = Governorate(
    id = id ?: 0,
    name = name ?: ""
)