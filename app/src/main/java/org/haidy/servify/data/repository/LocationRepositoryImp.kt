package org.haidy.servify.data.repository

import org.haidy.servify.data.mapper.toCountry
import org.haidy.servify.data.mapper.toGovernorate
import org.haidy.servify.data.remote.network.ServifyApiService
import org.haidy.servify.domain.model.Country
import org.haidy.servify.domain.model.Governorate
import org.haidy.servify.domain.repository.ILocationRepository
import javax.inject.Inject

class LocationRepositoryImp @Inject constructor(private val apiService: ServifyApiService) :
    ILocationRepository,
    BaseRepository() {

    override suspend fun getGovernorates(countryId: String): List<Governorate> {
        return wrapResponse { apiService.getGovernorates(countryId.toInt()) }?.data?.governorate?.map { it.toGovernorate() }
            ?: emptyList()
    }

    override suspend fun getCountries(): List<Country> {
        return wrapResponse { apiService.getCountries() }?.data?.map { it.toCountry() }
            ?: emptyList()
    }
}