package org.haidy.servify.domain.repository

import org.haidy.servify.domain.model.Country
import org.haidy.servify.domain.model.Governorate

interface ILocationRepository {
    suspend fun getGovernorates(countryId: String): List<Governorate>
    suspend fun getCountries(): List<Country>
}