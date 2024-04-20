package org.haidy.servify.data.repository

import org.haidy.servify.data.mapper.toService
import org.haidy.servify.data.remote.network.ServifyApiService
import org.haidy.servify.domain.model.Service
import org.haidy.servify.domain.repository.IServiceRepository
import javax.inject.Inject


class ServiceRepositoryImp @Inject constructor(private val apiService: ServifyApiService) :
    IServiceRepository, BaseRepository() {
    override suspend fun getAllServices(): List<Service> {
        val response = wrapResponse { apiService.getAllServices() }
        return response?.data?.map { it.toService() } ?: emptyList()
    }

    override suspend fun getAllOffers(): List<String> {
        TODO("Not Implemented yet")
    }

}