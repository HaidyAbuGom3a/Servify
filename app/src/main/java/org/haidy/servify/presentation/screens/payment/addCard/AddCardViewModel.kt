package org.haidy.servify.presentation.screens.payment.addCard

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.PaymentUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(private val paymentUseCase: PaymentUseCase) :
    BaseViewModel<AddCardUiState, AddCardUiEffect>(AddCardUiState()), AddCardInteractionListener {

        init {
            getCards()
        }

    private fun getCards(){
        tryToCollect(
            { paymentUseCase.getCards() },
            { cards -> _state.update { it.copy(cards = cards) } },
            {}
        )
    }
    override fun onClickBackIcon() {
        sendNewEffect(AddCardUiEffect.NavigateUp)
    }

    override fun onClickAddCard() {
        sendNewEffect(AddCardUiEffect.NavigateToAddPaymentMethod)
    }
}