package org.haidy.servify.data.repository.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.model.Location
import org.haidy.servify.domain.model.OrderStatus
import org.haidy.servify.domain.model.Service
import org.haidy.servify.domain.model.ServiceOrder
import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.domain.repository.IOrderRepository
import javax.inject.Inject

class FakeOrderRepository @Inject constructor() : IOrderRepository {
    override suspend fun getCancelledOrders(): Flow<List<ServiceOrder>> {
        return cancelledFlow
    }

    override suspend fun getUpcomingOrders(): Flow<List<ServiceOrder>> {
        return upcomingFlow
    }

    override suspend fun getCompletedOrders(): Flow<List<ServiceOrder>> {
        return completedFlow
    }

    override suspend fun bookAppointment(order: ServiceOrder) {
        upcomingFlow.update {
            it + listOf(
                ServiceOrder(
                    id = it.size.toString(),
                    specialist = order.specialist,
                    timeStamp = order.timeStamp,
                    serviceName = order.serviceName,
                    status = OrderStatus.UPCOMING,
                    requiredTasks = order.requiredTasks,
                    totalPrice = order.totalPrice
                )
            )
        }
    }

    override suspend fun cancelOrder(orderId: String) {
        upcomingFlow.update {
            it.toMutableList().filterNot { item -> item.id == orderId }
        }
        val order = orders.find { it.id == orderId }!!.copy(status = OrderStatus.CANCELLED)
        cancelledFlow.update { it + listOf(order) }
    }

    override suspend fun updateOrder(order: ServiceOrder) {
        val ordersFlow = when (order.status) {
            OrderStatus.UPCOMING -> upcomingFlow
            OrderStatus.COMPLETED -> completedFlow
            OrderStatus.CANCELLED -> cancelledFlow
        }
        ordersFlow.update {
            it.map { serviceOrder ->
                if (serviceOrder.id == order.id) {
                    order
                } else {
                    serviceOrder
                }
            }
        }
    }

    override suspend fun getOrder(orderId: String): ServiceOrder {
        return orders.find { it.id == orderId }!!
    }
}

private val orders = listOf(
    ServiceOrder(
        "3",
        specialist = Specialist(
            name = "Salah",
            location = Location(
                latitude = 31.430430,
                longitude = 31.649057,
                country = "Egypt",
                governorate = "New Damietta"
            ),
            id = 4.toString(),
            rating = 4.5,
            service = Service(
                id = 3.toString(),
                discount = 0.10,
                imageUrl = "https://www.shutterstock.com/image-photo/portrait-confident-male-carpenter-standing-600nw-2268295179.jpg",
                description = "This is carpentry work",
                name = "Carpentry",
                status = true
            ),
            imageUrl = "https://www.shutterstock.com/image-photo/profession-carpentry-woodwork-people-concept-600nw-559842814.jpg"
        ),
        status = OrderStatus.UPCOMING,
        serviceName = "Carpentry",
        timeStamp = 1716011829,
        requiredTasks = """
            1 - fix the window
            2 - fix the door 
        """.trimIndent(),
        totalPrice = "300EGP"
    ),
    ServiceOrder(
        "2",
        specialist = Specialist(
            name = "Nader",
            location = Location(
                latitude = 31.418125,
                longitude = 31.814249,
                country = "Egypt",
                governorate = "Damietta"
            ),
            id = 2.toString(),
            rating = 5.0,
            service = Service(
                id = 2.toString(),
                discount = 0.10,
                imageUrl = "https://www.neit.edu/wp-content/uploads/2023/12/image-2.png",
                description = "This is air conditioning work",
                name = "Air Conditioning",
                status = true
            ),
            imageUrl = "https://www.shutterstock.com/image-photo/technician-service-cleaning-air-conditioner-600nw-1498805081.jpg"
        ),
        status = OrderStatus.CANCELLED,
        serviceName = "Air Conditioning",
        timeStamp = 1715517330,
        requiredTasks = "Fix air conditioner ",
        totalPrice = "500EGP"
    ),
    ServiceOrder(
        "4",
        specialist = Specialist(
            name = "Waleed",
            location = Location(
                latitude = 31.425991,
                longitude = 31.654069,
                country = "Egypt",
                governorate = "New Damietta"
            ),
            id = 5.toString(),
            rating = 5.0,
            service = Service(
                id = 2.toString(),
                discount = 0.10,
                imageUrl = "https://www.neit.edu/wp-content/uploads/2023/12/image-2.png",
                description = "This is air conditioning work",
                name = "Air Conditioning",
                status = true
            ),
            imageUrl = "https://www.shutterstock.com/image-photo/technician-service-cleaning-air-conditioner-600nw-1498805081.jpg"
        ),
        status = OrderStatus.UPCOMING,
        serviceName = "Carpentry",
        timeStamp = 1716467730,
        requiredTasks = "Fix air conditioner ",
        totalPrice = "500EGP"
    ),
    ServiceOrder(
        "1",
        specialist = Specialist(
            name = "Salah",
            location = Location(
                latitude = 31.430430,
                longitude = 31.649057,
                country = "Egypt",
                governorate = "New Damietta"
            ),
            id = 4.toString(),
            rating = 4.5,
            service = Service(
                id = 3.toString(),
                discount = 0.10,
                imageUrl = "https://www.shutterstock.com/image-photo/portrait-confident-male-carpenter-standing-600nw-2268295179.jpg",
                description = "This is carpentry work",
                name = "Carpentry",
                status = true
            ),
            imageUrl = "https://www.shutterstock.com/image-photo/profession-carpentry-woodwork-people-concept-600nw-559842814.jpg"
        ),
        status = OrderStatus.COMPLETED,
        serviceName = "Carpentry",
        timeStamp = 1715366130,
        requiredTasks = """
            1 - fix the window
            2 - fix the door 
        """.trimIndent(),
        totalPrice = "300EGP"
    ),

    )

private val upcomingFlow = MutableStateFlow(orders.filter { it.status == OrderStatus.UPCOMING })

private val cancelledFlow = MutableStateFlow(orders.filter { it.status == OrderStatus.CANCELLED })

private val completedFlow = MutableStateFlow(orders.filter { it.status == OrderStatus.COMPLETED })