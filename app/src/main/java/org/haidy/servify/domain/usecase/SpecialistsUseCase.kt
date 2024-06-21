package org.haidy.servify.domain.usecase

import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.domain.repository.ISpecialistRepository
import javax.inject.Inject
import javax.inject.Named

class SpecialistsUseCase @Inject constructor(
    @Named("fakeSpecialists") private val fakeSpecialistsRepo: ISpecialistRepository,
    @Named("specialists") private val specialistsRepo: ISpecialistRepository
) {
    suspend fun getBestSpecialists(): List<Specialist> {
        return fakeSpecialistsRepo.getBestSpecialists()
    }

    suspend fun getServiceSpecialists(serviceName: String): List<Specialist> {
        return specialistsRepo.getServiceSpecialists(serviceName)
    }

    suspend fun getNearestSpecialists(): List<Specialist> {
        return fakeSpecialistsRepo.getNearestSpecialists()
    }

    suspend fun getSpecialist(id: String): Specialist {
        return fakeSpecialistsRepo.getSpecialist(id)
    }

    suspend fun getFilteredSpecialists(
        serviceName: String? = null,
        name: String? = null,
        rating: String? = null
    ) = specialistsRepo.getFilteredSpecialists(serviceName, name, rating)
}