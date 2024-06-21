package org.haidy.servify.presentation.screens.serviceSpecialists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.screens.bookingAppointment.navigateToBookingAppointment
import org.haidy.servify.presentation.screens.chat.navigateToChat
import org.haidy.servify.presentation.screens.home.composable.ItemSpecialist
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.sum

@Composable
fun ServiceSpecialistsScreen(viewModel:  ServiceSpecialistsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            is  ServiceSpecialistsUiEffect.NavigateToBookAppointment -> navController.navigateToBookingAppointment(
                effect.specId,
                "_"
            )

            ServiceSpecialistsUiEffect.NavigateUp -> navController.popBackStack()
            is ServiceSpecialistsUiEffect.NavigateToChat -> navController.navigateToChat(effect.specId)
        }
    }
    ServiceSpecialistsScreenContent(state = state, listener = viewModel)
}

@Composable
fun ServiceSpecialistsScreenContent(
    state:  ServiceSpecialistsUiState,
    listener:  ServiceSpecialistsInteractionListener
) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = state.serviceName
            )
        }
    ) { paddingValues ->

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = 32.dp, horizontal = 8.dp).sum(
                otherPaddingValues = paddingValues
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.specialists) { specialist ->
                ItemSpecialist(
                    onClickFav = {},
                    onClickBookNow = { id -> listener.onClickBookNow(id) },
                    onClickMessage = { listener.onClickChat(it) },
                    onClickCall = {},
                    specialist = specialist,
                    cardWidth = 130.dp
                )
            }
        }
    }
}