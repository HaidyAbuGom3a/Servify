package org.haidy.servify.presentation.screens.bookingCancellation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.servify.domain.usecase.OrdersUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class BookingCancellationViewModel @Inject constructor(
    private val orderUseCase: OrdersUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BookingCancellationUiState, BookingCancellationUiEffect>(
    BookingCancellationUiState()
), BookingCancellationInteractionListener {

    val args = BookingCancellationArgs(savedStateHandle)

    override fun onReasonChanged(reason: String) {
        _state.update {
            it.copy(reason = reason)
        }
    }

    override fun onClickConfirm() {
        tryToExecute(
            { orderUseCase.cancelOrder(args.orderId) },
            {
                viewModelScope.launch {
                    sendNewEffect(BookingCancellationUiEffect.ShowSuccessMessage)
                    delay(1500)
                    sendNewEffect(BookingCancellationUiEffect.NavigateUp)
                }

            },
            {}
        )
    }

    override fun onClickReason(reason: String) {
        _state.update { it.copy(selectedReason = reason) }
    }

    override fun onClickBack() {
        sendNewEffect(BookingCancellationUiEffect.NavigateUp)
    }

}