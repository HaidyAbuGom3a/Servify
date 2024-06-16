package org.haidy.servify.presentation.screens.bokkingSuccess

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.OrdersUseCase
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class BookingSuccessViewModel @Inject constructor(private val userUseCase: UserUseCase, private val ordersUseCase: OrdersUseCase) :
    BaseViewModel<BookingSuccessUiState, BookingSuccessUiEffect>(
        BookingSuccessUiState()
    ), BookingSuccessInteractionListener {

        init {
            getUser()
            getOrder()
        }

    private fun getUser(){
        tryToExecute(
            { userUseCase.getUserProfile() },
            { user -> _state.update { it.copy(customerName = user.username) }},
            {}
        )
    }
    private fun getOrder(){
        tryToCollect(
            { ordersUseCase.getUpcomingOrders() },
            { orders -> _state.update { it.copy(bookedOrder = orders.last()) }},
            {}
        )
    }
    override fun onClickShowBooking() {
        sendNewEffect(BookingSuccessUiEffect.NavigateToBookingScreen)
    }
}