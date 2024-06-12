package org.haidy.servify.data.repository.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.model.Date
import org.haidy.servify.domain.model.PaymentCard
import org.haidy.servify.domain.model.PaymentCardType
import org.haidy.servify.domain.repository.IPaymentRepository
import javax.inject.Inject

class FakePaymentRepository @Inject constructor() : IPaymentRepository {
    override suspend fun getCards(): Flow<List<PaymentCard>> {
        return cards
    }

    override suspend fun addCard(card: PaymentCard) {
        cards.update { listOf(card) + it }
    }


}


private val cards = MutableStateFlow(
    listOf(
        PaymentCard(
            cardHolder = "Eren Yeager",
            cardNumber = "4539361674441165",
            cardType = PaymentCardType.VISA,
            securityCode = "357",
            expiryDate = Date("10", "25")
        )
    )
)