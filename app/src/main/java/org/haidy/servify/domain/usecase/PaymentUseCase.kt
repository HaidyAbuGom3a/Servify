package org.haidy.servify.domain.usecase

import org.haidy.servify.domain.model.PaymentCard
import org.haidy.servify.domain.repository.IPaymentRepository
import javax.inject.Inject
import javax.inject.Named

class PaymentUseCase @Inject constructor(
    @Named("fakePayment") private val paymentRepo: IPaymentRepository
) {
    suspend fun getCards() = paymentRepo.getCards()

    suspend fun addCard(card: PaymentCard){
        paymentRepo.addCard(card)
    }
}