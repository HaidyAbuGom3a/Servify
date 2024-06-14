package org.haidy.servify.presentation.screens.payment.paymentOption

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.PaymentUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentOptionViewModel @Inject constructor(
    private val paymentUseCase: PaymentUseCase
) : BaseViewModel<PaymentOptionUiState, PaymentOptionUiEffect>(PaymentOptionUiState()),
    PaymentOptionInteractionListener {
    init {
        getCards()
    }

    private fun getCards() {
        tryToCollect(
            { paymentUseCase.getCards() },
            { cards -> _state.update { it.copy(cards = cards) } },
            {}
        )
    }

    override fun onClickBackIcon() {
        sendNewEffect(PaymentOptionUiEffect.NavigateUp)
    }

    override fun onClickProceed() {
        sendNewEffect(PaymentOptionUiEffect.NavigateToPaymentSuccess)
    }

    override fun onClickMethod(index: Int) {
        if (state.value.cards.isEmpty()) {
            _state.update {
                it.copy(
                    selectedPaymentMethod = PaymentMethod.CASH,
                    selectedCard = null
                )
            }
        } else {
            if (index < state.value.cards.size) {
                _state.update {
                    it.copy(
                        selectedPaymentMethod = PaymentMethod.CARD,
                        selectedCard = state.value.cards[index]
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        selectedPaymentMethod = PaymentMethod.CASH,
                        selectedCard = null
                    )
                }
            }
        }
    }
}