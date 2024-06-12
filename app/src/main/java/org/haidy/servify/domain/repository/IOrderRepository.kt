package org.haidy.servify.domain.repository

import kotlinx.coroutines.flow.Flow
import org.haidy.servify.domain.model.ServiceOrder

interface IOrderRepository {
    suspend fun getCancelledOrders(): Flow<List<ServiceOrder>>
    suspend fun getUpcomingOrders(): Flow<List<ServiceOrder>>
    suspend fun getCompletedOrders(): Flow<List<ServiceOrder>>
    suspend fun bookAppointment(order: ServiceOrder)
    suspend fun cancelOrder(orderId: String)
    suspend fun updateOrder(order: ServiceOrder)
    suspend fun getOrder(orderId: String): ServiceOrder
}