package org.haidy.servify.presentation.screens.bokkingSuccess

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.screens.bookingTrack.composable.ItemOrder
import org.haidy.servify.presentation.screens.bookingTrack.navigateToBookingTrack
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.convertToDayOfMonth
import org.haidy.servify.presentation.util.sum

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingSuccessScreen(viewModel: BookingSuccessViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when(effect){
            BookingSuccessUiEffect.NavigateToBookingScreen -> navController.navigateToBookingTrack(true)
        }
    }
    BookingSuccessContent(
        state = state,
        listener = viewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingSuccessContent(
    state: BookingSuccessUiState,
    listener: BookingSuccessInteractionListener
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
                    onClick = { listener.onClickShowBooking() },
                    text = Resources.strings.viewBooking,
                    buttonRadius = 24.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(it.sum(otherPaddingValues = PaddingValues(bottom = 24.dp)))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = Resources.images.successGreenShape),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp),
                    contentDescription = null
                )
            }
            Text(
                text = Resources.strings.bookingSuccessful,
                style = Theme.typography.headline.copy(color = Theme.colors.success100),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            state.bookedOrder?.let { order ->
                Text(
                    text = Resources.strings.bookingSuccessMessage(
                        state.customerName,
                        serviceName = order.serviceName,
                        serviceDate = order.timeStamp.convertToDayOfMonth()
                    ),
                    color = Theme.colors.contrast,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 56.dp, start = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.weight(1f))
                ItemOrder(order = state.bookedOrder, modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}