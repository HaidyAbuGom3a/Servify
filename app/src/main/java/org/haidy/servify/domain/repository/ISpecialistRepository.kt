package org.haidy.servify.domain.repository

import org.haidy.servify.domain.model.Specialist

interface ISpecialistRepository {
    suspend fun getBestSpecialists(): List<Specialist>
    suspend fun getNearestSpecialists(): List<Specialist>
}
