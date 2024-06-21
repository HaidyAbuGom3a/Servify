package org.haidy.servify.data.repository

import org.haidy.servify.data.mapper.toSpecialist
import org.haidy.servify.data.remote.network.ServifyApiService
import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.domain.repository.ISpecialistRepository
import javax.inject.Inject

class SpecialistRepositoryImp @Inject constructor(private val apiService: ServifyApiService) :
    ISpecialistRepository, BaseRepository() {
    override suspend fun getBestSpecialists(): List<Specialist> {
        return wrapResponse { apiService.getBestSpecialists() }?.data?.map { it.toSpecialist() }
            ?: emptyList()
    }

    override suspend fun getFilteredSpecialists(
        serviceName: String?,
        name: String?,
        rating: String?
    ): List<Specialist> {

        val response = wrapResponse {
            apiService.getFilteredSpecialists(
                serviceName,
                name,
                rating
            )
        }?.data
        return response?.map { it.toSpecialist() } ?: emptyList()
    }

    override suspend fun getNearestSpecialists(): List<Specialist> {
        TODO("Not yet implemented")
    }

    override suspend fun getSpecialist(id: String): Specialist {
        TODO("Not yet implemented")
    }

    override suspend fun getServiceSpecialists(serviceName: String): List<Specialist> {
        return wrapResponse { apiService.getServiceSpecialists(serviceName) }?.data?.map { it.toSpecialist() }
            ?: emptyList()
    }
}