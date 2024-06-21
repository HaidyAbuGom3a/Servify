package org.haidy.servify.presentation.screens.bestSpecialists

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
import org.haidy.servify.presentation.screens.home.composable.ItemSpecialist
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.sum

@Composable
fun BestSpecialistsScreen(viewModel: SpecialistsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            is SpecialistsUiEffect.NavigateToBookAppointment -> navController.navigateToBookingAppointment(
                effect.specId,
                "_"
            )

            SpecialistsUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    BestSpecialistsScreenContent(state = state, listener = viewModel)
}

@Composable
fun BestSpecialistsScreenContent(
    state: SpecialistsUiState,
    listener: SpecialistsInteractionListener
) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.bestSpecialists
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
            items(state.bestSpecialists) { specialist ->
                ItemSpecialist(
                    onClickFav = {},
                    onClickBookNow = { id -> listener.onClickBookNow(id) },
                    onClickMessage = {},
                    onClickCall = {},
                    specialist = specialist,
                    cardWidth = 130.dp
                )
            }
        }
    }
}