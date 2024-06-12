package org.haidy.servify.domain.repository

import kotlinx.coroutines.flow.Flow
import org.haidy.servify.domain.model.PaymentCard

interface IPaymentRepository {
    suspend fun getCards(): Flow<List<PaymentCard>>
    suspend fun addCard(card: PaymentCard)
}