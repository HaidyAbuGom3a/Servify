package org.haidy.servify.presentation.screens.bookingTrack

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.servify.domain.usecase.OrdersUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class BookingTrackViewModel @Inject constructor(private val ordersUseCase: OrdersUseCase) :
    BaseViewModel<BookingTrackUiState, BookingTrackUiEffect>(
        BookingTrackUiState()
    ), BookingTrackInteractionListener {
    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            tryToExecute(
                { ordersUseCase.getUpcomingOrders() },
                { upcoming -> _state.update { it.copy(upcomingOrders = upcoming) } },
                {}
            )
            tryToExecute(
                { ordersUseCase.getCancelledOrders() },
                { cancelled -> _state.update { it.copy(cancelledOrders = cancelled) } },
                {}
            )
            tryToExecute(
                { ordersUseCase.getCompletedOrders() },
                { completed -> _state.update { it.copy(completedOrders = completed) } },
                {}
            )

        }
    }

    override fun onClickAddRating(specialistId: String) {
        sendNewEffect(BookingTrackUiEffect.NavigateToFeedback(specialistId))
    }

    override fun onClickReschedule(orderId: String) {
        sendNewEffect(BookingTrackUiEffect.NavigateToScheduling(orderId))
    }

    override fun onClickCancel(orderId: String) {
        //cancel the order
    }

    override fun onClickReBook(specialistId: String) {
        //rebook order
    }

    override fun onClickBackIcon() {
        sendNewEffect(BookingTrackUiEffect.NavigateUp)
    }
}