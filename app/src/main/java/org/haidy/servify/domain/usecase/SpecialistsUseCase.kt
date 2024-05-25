package org.haidy.servify.domain.usecase

import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.domain.repository.ISpecialistRepository
import javax.inject.Inject
import javax.inject.Named

class SpecialistsUseCase @Inject constructor(
    @Named("fakeSpecialists") private val fakeSpecialistsRepo: ISpecialistRepository
) {
    suspend fun getBestSpecialists(): List<Specialist> {
        return fakeSpecialistsRepo.getBestSpecialists()
    }

    suspend fun getNearestSpecialists(): List<Specialist> {
        return fakeSpecialistsRepo.getNearestSpecialists()
    }

    suspend fun getSpecialist(id: String): Specialist{
        return fakeSpecialistsRepo.getSpecialist(id)
    }
}