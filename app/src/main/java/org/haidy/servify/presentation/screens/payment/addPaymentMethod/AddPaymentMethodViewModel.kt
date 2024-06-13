package org.haidy.servify.presentation.screens.payment.addPaymentMethod

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.model.Date
import org.haidy.servify.domain.model.PaymentCard
import org.haidy.servify.domain.model.getLogo
import org.haidy.servify.domain.usecase.PaymentUseCase
import org.haidy.servify.domain.usecase.ValidationUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AddPaymentMethodViewModel @Inject constructor(
    private val paymentUseCase: PaymentUseCase,
    private val validationUseCase: ValidationUseCase
) :
    BaseViewModel<AddPaymentMethodUiState, AddPaymentMethodUiEffect>(
        AddPaymentMethodUiState()
    ), AddPaymentMethodInteractionListener {
    override fun onCardHolderChanged(cardHolder: String) {
        _state.update { it.copy(cardHolder = cardHolder) }
    }

    override fun onExpiryDateChanged(expiryDate: String) {
       _state.update { it.copy(cardExpiryDate = expiryDate) }
    }

    override fun onSecurityCodeChanged(securityCode: String) {
        _state.update { it.copy(securityCode = securityCode) }
    }

    override fun onCardNumberChanged(cardNumber: String) {
        _state.update { it.copy(cardNumber = cardNumber) }
        val cardType = validationUseCase.validateCardNumber(state.value.cardNumber)
        _state.update { it.copy(cardType = cardType) }
        val logo = getLogo(cardType)
        if (logo != 0) _state.update { it.copy(cardLogo = logo) }
    }

    override fun onClickSave() {
        tryToExecute(
            {
                val state = state.value
                val date = state.cardExpiryDate.split('/')
                val card = PaymentCard(
                    cardHolder = state.cardHolder,
                    expiryDate = Date(date.first(), date.last()),
                    cardNumber = state.cardNumber,
                    cardType = state.cardType,
                    securityCode = state.securityCode
                )
                paymentUseCase.addCard(card)
            },
            { sendNewEffect(AddPaymentMethodUiEffect.NavigateToHome) },
            {}
        )
    }

    override fun onClickBackIcon() {
        sendNewEffect(AddPaymentMethodUiEffect.NavigateUp)
    }
}