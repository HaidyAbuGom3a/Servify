package org.haidy.servify.presentation.screens.bookingAppointment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.screens.bookingAppointment.composable.ItemDate
import org.haidy.servify.presentation.screens.bookingAppointment.composable.ItemPrice
import org.haidy.servify.presentation.screens.bookingAppointment.composable.ItemRequiredTasks
import org.haidy.servify.presentation.screens.bookingAppointment.composable.ItemTime
import org.haidy.servify.presentation.screens.bookingAppointment.composable.SpecialistTobRow
import org.haidy.servify.presentation.util.EffectHandler


@Composable
fun BookingAppointmentScreen(viewModel: BookingAppointmentViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) {  effect, navController ->
        when(effect){
            BookingAppointmentUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    BookingAppointmentContent(state = state, listener = viewModel)
}

@Composable
fun BookingAppointmentContent(
    state: BookingAppointmentUiState,
    listener: BookingAppointmentInteractionListener
) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.booking
            )
        },
        bottomBar = {
            Surface {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(94.dp)
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(Theme.colors.background), contentAlignment = Alignment.Center
                ) {
                    ServifyButton(
                        onClick = { listener.onClickBookNow() },
                        text = Resources.strings.bookNow,
                        buttonRadius = 24.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    ) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
        ) {
            item {
                state.specialist?.let { specialist ->
                    SpecialistTobRow(
                        specialist = specialist,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 40.dp,
                                bottom = 24.dp
                            )
                    )
                }
            }

            item {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    thickness = 1.dp,
                    color = Theme.colors.dark300.copy(0.7f)
                )

            }

            item {
                ItemRequiredTasks(
                    onValueChange = { text -> listener.onRequiredTasksChanged(text) },
                    text = state.requiredTasks,
                    modifier = Modifier.fillMaxWidth().padding(
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 32.dp
                    )
                )
            }

            item {
                ItemDate(
                    onClickDate = { date -> listener.onChooseDate(date) },
                    date = state.day,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }

            item {
                ItemTime(
                    onClickTime = { time -> listener.onChooseTime(time) },
                    time = state.time,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 32.dp)
                )
            }

            item {
                ItemPrice(
                    onPriceChange = { price -> listener.onPriceChanged(price) },
                    price = state.price,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                )
            }
        }

    }

}