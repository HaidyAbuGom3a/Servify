package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.CountryDto
import org.haidy.servify.domain.model.Country

fun CountryDto.toCountry(): Country{
    return Country(
        id = id ?: 0,
        name = name ?: ""
    )
}