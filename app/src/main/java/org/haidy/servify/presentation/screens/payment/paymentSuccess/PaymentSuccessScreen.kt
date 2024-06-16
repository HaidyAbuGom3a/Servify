package org.haidy.servify.presentation.screens.payment.paymentSuccess

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.LocalNavController
import org.haidy.servify.app.navigation.ServifyDestination
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.PaymentCard
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.screens.bokkingSuccess.navigateToBookingSuccess
import org.haidy.servify.presentation.screens.bookingAppointment.BookingAppointmentArgs
import org.haidy.servify.presentation.screens.bookingAppointment.BookingAppointmentViewModel
import org.haidy.servify.presentation.screens.payment.paymentOption.PaymentMethod
import org.haidy.servify.presentation.screens.payment.paymentOption.PaymentOptionViewModel
import org.haidy.servify.presentation.util.getCurrentDateFormatted
import org.haidy.servify.presentation.util.getCurrentTimeFormatted

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentSuccessScreen() {
    val navHostController = LocalNavController.current
    val backStackEntry = remember {
        navHostController.getBackStackEntry("${ServifyDestination.BOOKING_APPOINTMENT}/{${BookingAppointmentArgs.SPECIALIST_ID}}/{${BookingAppointmentArgs.ORDER_ID}}")
    }

    val bookingAppointmentViewModel: BookingAppointmentViewModel = hiltViewModel(backStackEntry)
    val secondBackStackEntry = remember {
        navHostController.getBackStackEntry(ServifyDestination.PAYMENT_OPTION)
    }

    val paymentOptionViewModel: PaymentOptionViewModel = hiltViewModel(secondBackStackEntry)
    val bookingAppointmentState by bookingAppointmentViewModel.state.collectAsState()
    val paymentOptionState by paymentOptionViewModel.state.collectAsState()
    paymentOptionState.selectedPaymentMethod?.let {
        val paymentMode = getPaymentMode(it, paymentOptionState.selectedCard)
        PaymentSuccessContent(
            onClickDone = {
                navHostController.navigateToBookingSuccess()
            },
            paymentMode = paymentMode,
            totalPay = bookingAppointmentState.price
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentSuccessContent(
    onClickDone: () -> Unit,
    paymentMode: String,
    totalPay: String
) {
    Scaffold(bottomBar = {
        Surface(
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            shadowElevation = 4.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(94.dp)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(Theme.colors.card), contentAlignment = Alignment.Center
            ) {
                ServifyButton(
                    onClick = { onClickDone() },
                    text = Resources.strings.done,
                    buttonRadius = 24.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }) {
        it
        Column(
            Modifier
                .fillMaxSize()
                .background(Theme.colors.backgroundGrey)
        )
        {
            Box(
                modifier = Modifier.padding(
                    top = 16.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 88.dp
                )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = Resources.images.paymentReceiptCard),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(Theme.colors.card)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = Resources.images.successOrangeShape),
                        contentScale = ContentScale.None,
                        modifier = Modifier.size(100.dp),
                        contentDescription = null
                    )
                }
                Column(modifier = Modifier.padding(top = 120.dp)) {
                    Text(
                        text = Resources.strings.great,
                        style = Theme.typography.titleLarge.copy(color = Theme.colors.accent100),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = Resources.strings.paymentSuccess,
                        style = Theme.typography.headline.copy(color = Theme.colors.contrast),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp),
                        textAlign = TextAlign.Center
                    )
                    RowTitleWithValue(
                        title = Resources.strings.paymentMode,
                        value = paymentMode,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
                    )
                    RowTitleWithValue(
                        title = Resources.strings.totalAmount,
                        value = "${Resources.strings.egp} $totalPay",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
                    )
                    RowTitleWithValue(
                        title = Resources.strings.payDate,
                        value = getCurrentDateFormatted(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
                    )
                    RowTitleWithValue(
                        title = Resources.strings.payTime,
                        value = getCurrentTimeFormatted(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = Resources.strings.totalPay,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = Theme.typography.title.copy(Theme.colors.dark200.copy(alpha = 0.7f))
                    )
                    Text(
                        text = "${Resources.strings.egp} $totalPay",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 96.dp),
                        textAlign = TextAlign.Center,
                        style = Theme.typography.headline.copy(color = Theme.colors.accent100)
                    )
                }
            }
        }
    }

}

@Composable
private fun getPaymentMode(paymentOption: PaymentMethod, card: PaymentCard?): String {
    return when (paymentOption) {
        PaymentMethod.CASH -> Resources.strings.cash.uppercase()
        PaymentMethod.CARD -> card!!.cardType.name.uppercase()
    }
}

@Composable
private fun RowTitleWithValue(title: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier) {
        Text(
            text = title,
            style = Theme.typography.title.copy(Theme.colors.dark200.copy(alpha = 0.7f))
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            style = Theme.typography.title.copy(Theme.colors.contrast)
        )
    }
}