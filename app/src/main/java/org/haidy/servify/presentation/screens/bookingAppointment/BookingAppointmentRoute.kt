package org.haidy.servify.presentation.screens.bookingAppointment

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.haidy.servify.app.navigation.ServifyDestination


private const val ROUTE = ServifyDestination.BOOKING_APPOINTMENT
fun NavController.navigateToBookingAppointment(
    specialistId: String,
    orderId: String,
    clearBackStack: Boolean = false
) {
    navigate("$ROUTE/$specialistId/$orderId") {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}


fun NavGraphBuilder.bookingAppointmentRoute() {
    composable(
        route = "$ROUTE/{${BookingAppointmentArgs.SPECIALIST_ID}}/{${BookingAppointmentArgs.ORDER_ID}}",
        arguments = listOf(
            navArgument(BookingAppointmentArgs.SPECIALIST_ID) { NavType.StringType },
            navArgument(BookingAppointmentArgs.ORDER_ID) { NavType.StringType }
        )
    ) {
        BookingAppointmentScreen()
    }
}

class BookingAppointmentArgs(savedStateHandle: SavedStateHandle) {
    val specialistId: String = checkNotNull(savedStateHandle[SPECIALIST_ID])
    val orderId: String = checkNotNull(savedStateHandle[ORDER_ID])

    companion object {
        const val SPECIALIST_ID = "specialistId"
        const val ORDER_ID = "orderId"
    }
}


