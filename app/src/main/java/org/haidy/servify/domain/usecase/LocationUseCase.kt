package org.haidy.servify.domain.usecase

import org.haidy.servify.domain.model.Country
import org.haidy.servify.domain.model.Governorate
import org.haidy.servify.domain.repository.ILocationRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationRepo: ILocationRepository,
) {
    suspend fun getCountryGovernorates(countryId: String): List<Governorate> {
        return locationRepo.getGovernorates(countryId)
    }

    suspend fun getCountries(): List<Country> {
        return locationRepo.getCountries()
    }
}