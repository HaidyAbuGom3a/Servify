package org.haidy.servify.domain.repository

import org.haidy.servify.domain.model.Service

interface IServiceRepository {
    suspend fun getAllServices(): List<Service>
    suspend fun getAllOffers(): List<String>
}