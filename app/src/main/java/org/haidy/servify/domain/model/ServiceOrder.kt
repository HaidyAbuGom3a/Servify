package org.haidy.servify.domain.model

data class ServiceOrder(
    val id: String,
    val serviceName: String,
    val specialist: Specialist,
    val timeStamp: Long,
    val status: OrderStatus
)

enum class OrderStatus {
    UPCOMING,
    COMPLETED,
    CANCELLED
}