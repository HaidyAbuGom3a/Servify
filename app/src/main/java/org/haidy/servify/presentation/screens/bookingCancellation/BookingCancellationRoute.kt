package org.haidy.servify.presentation.screens.bookingCancellation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.BOOKING_CANCELLATION
fun NavController.navigateToBookingCancellation(orderId: String) {
    navigate("$ROUTE/$orderId")
}

fun NavGraphBuilder.bookingCancellationRoute() {
    composable(
        route = "$ROUTE/{${BookingCancellationArgs.ID}}",
        arguments = listOf( navArgument(BookingCancellationArgs.ID){ NavType.StringType } )
    ){
        BookingCancellationScreen()
    }
}


class BookingCancellationArgs(savedStateHandle: SavedStateHandle) {
    val orderId: String = checkNotNull(savedStateHandle[ID])

    companion object {
        const val ID = "orderId"
    }
}