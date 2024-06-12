package org.haidy.servify.presentation.screens.bookingAppointment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.servify.domain.model.OrderStatus
import org.haidy.servify.domain.model.ServiceOrder
import org.haidy.servify.domain.usecase.OrdersUseCase
import org.haidy.servify.domain.usecase.SpecialistsUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import org.haidy.servify.presentation.util.convertToTimestamp
import org.haidy.servify.presentation.util.toDateString
import org.haidy.servify.presentation.util.toTimeString
import javax.inject.Inject

@HiltViewModel
class BookingAppointmentViewModel @Inject constructor(
    private val ordersUseCase: OrdersUseCase,
    private val specialistsUseCase: SpecialistsUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel<BookingAppointmentUiState, BookingAppointmentUiEffect>(
        BookingAppointmentUiState()
    ), BookingAppointmentInteractionListener {

    private val args = BookingAppointmentArgs(savedStateHandle)
    init {
        viewModelScope.launch {
            getSpecialist()
            getOrder()
        }
    }

    private fun getOrder() {
       if(args.orderId == "_") return
        tryToExecute(
            { ordersUseCase.getOrder(args.orderId) },
            { order ->
                _state.update {
                    it.copy(
                        time = order.timeStamp.toTimeString(),
                        day = order.timeStamp.toDateString(),
                        price = order.totalPrice,
                        requiredTasks = order.requiredTasks
                    )
                }
            },
            {}
        )
    }

    private fun getSpecialist() {
        tryToExecute(
            { specialistsUseCase.getSpecialist(args.specialistId) },
            { specialist -> _state.update { it.copy(specialist = specialist) } },
            {}
        )
    }

    override fun onRequiredTasksChanged(text: String) {
        _state.update { it.copy(requiredTasks = text) }
    }

    override fun onClickBackIcon() {
        sendNewEffect(BookingAppointmentUiEffect.NavigateUp)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickBookNow() {
        val state = _state.value
        state.specialist?.let {
            val order = ServiceOrder(
                id = args.orderId,
                status = OrderStatus.UPCOMING,
                specialist = it,
                serviceName = it.service.name,
                timeStamp = convertToTimestamp(state.day, state.time),
                totalPrice = state.price,
                requiredTasks = state.requiredTasks
            )
            if (args.orderId != "_") {
                tryToExecute(
                    { ordersUseCase.updateOrder(order) },
                    { sendNewEffect(BookingAppointmentUiEffect.NavigateUp) },
                    {}
                )
            } else {
                tryToExecute(
                    { ordersUseCase.addOrder(order) },
                    { sendNewEffect(BookingAppointmentUiEffect.NavigateUp) },
                    {}
                )
            }
        }

    }

    override fun onPriceChanged(price: String) {
        _state.update { it.copy(price = price) }
    }

    override fun onChooseTime(time: String) {
        _state.update { it.copy(time = time) }
    }

    override fun onChooseDate(date: String) {
        _state.update { it.copy(day = date) }
    }
}