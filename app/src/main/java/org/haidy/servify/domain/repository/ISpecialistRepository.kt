package org.haidy.servify.domain.repository

import org.haidy.servify.domain.model.Specialist

interface ISpecialistRepository {
    suspend fun getBestSpecialists(): List<Specialist>
    suspend fun getFilteredSpecialists(
        serviceName: String? = null,
        name: String? = null,
        rating: String? = null
    ): List<Specialist>

    suspend fun getNearestSpecialists(): List<Specialist>
    suspend fun getSpecialist(id: String): Specialist
    suspend fun getServiceSpecialists(serviceName: String): List<Specialist>
}
