package org.haidy.servify.presentation.screens.bookingTrack

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.LocalPaddingValues
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.screens.bookingAppointment.navigateToBookingAppointment
import org.haidy.servify.presentation.screens.bookingCancellation.navigateToBookingCancellation
import org.haidy.servify.presentation.screens.bookingTrack.composable.ItemOrder
import org.haidy.servify.presentation.screens.feedback.navigateToFeedback
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.sum


@Composable
fun BookingTrackScreen(viewModel: BookingTrackViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            is BookingTrackUiEffect.NavigateToFeedback -> navController.navigateToFeedback(effect.specialistId)
            is BookingTrackUiEffect.NavigateToScheduling -> navController.navigateToBookingAppointment(effect.specialistId, effect.orderId)
            BookingTrackUiEffect.NavigateUp -> navController.popBackStack()
            is BookingTrackUiEffect.NavigateToBookingCancellation -> navController.navigateToBookingCancellation(
                effect.orderId
            )
        }
    }
    BookingTrackContent(state, viewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookingTrackContent(state: BookingTrackUiState, listener: BookingTrackInteractionListener) {
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = {
            Column {
                ServifyAppBar(
                    onNavigateUp = { listener.onClickBackIcon() },
                    isBackIconVisible = true,
                    title = Resources.strings.booking
                )
                BookingTabRow(pagerState)
            }

        }
    ) {
        HorizontalPager(
            pageCount = Tabs.values().size,
            state = pagerState,
            modifier = Modifier
                .padding(it.sum(otherPaddingValues = LocalPaddingValues.current))
        ) { index ->
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .background(Theme.colors.background)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                when (index) {
                    0 -> {
                        items(state.upcomingOrders) { order ->
                            ItemOrder(order = order, listener = listener)
                        }
                    }

                    1 -> {
                        items(state.completedOrders) { order ->
                            ItemOrder(order = order, listener = listener)
                        }
                    }

                    2 -> {
                        items(state.cancelledOrders) { order ->
                            ItemOrder(order = order, listener = listener)
                        }
                    }
                }

            }
        }
    }
}