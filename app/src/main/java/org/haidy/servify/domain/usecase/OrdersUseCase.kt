package org.haidy.servify.domain.usecase

import org.haidy.servify.domain.repository.IOrderRepository
import javax.inject.Inject
import javax.inject.Named

class OrdersUseCase @Inject constructor(
    @Named("fakeOrders") private val ordersRepo: IOrderRepository
) {
    suspend fun getUpcomingOrders() = ordersRepo.getUpcomingOrders()

    suspend fun getCompletedOrders() = ordersRepo.getCompletedOrders()

    suspend fun getCancelledOrders() = ordersRepo.getCancelledOrders()

    suspend fun cancelOrder(orderId: String){
        ordersRepo.cancelOrder(orderId)
    }
}