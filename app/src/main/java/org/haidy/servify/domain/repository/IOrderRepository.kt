package org.haidy.servify.domain.repository

import org.haidy.servify.domain.model.ServiceOrder

interface IOrderRepository {
    suspend fun getCancelledOrders(): List<ServiceOrder>
    suspend fun getUpcomingOrders(): List<ServiceOrder>
    suspend fun getCompletedOrders(): List<ServiceOrder>
}