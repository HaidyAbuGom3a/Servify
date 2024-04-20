package org.haidy.servify.domain.usecase

import org.haidy.servify.domain.model.Service
import org.haidy.servify.domain.repository.IServiceRepository
import javax.inject.Inject
import javax.inject.Named

class ServicesUseCase @Inject constructor(
    @Named("services") private val serviceRepo: IServiceRepository,
    @Named("fakeServices") private val fakeServicesRepo: IServiceRepository
) {
    suspend fun getAllServices(): List<Service> {
        return serviceRepo.getAllServices()
    }

    suspend fun getAllOffers(): List<String> {
        return fakeServicesRepo.getAllOffers()
    }
}