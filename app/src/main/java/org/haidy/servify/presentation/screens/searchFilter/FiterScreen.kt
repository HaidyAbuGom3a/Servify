package org.haidy.servify.presentation.screens.searchFilter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.LocalNavController
import org.haidy.servify.app.navigation.ServifyDestination
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.location.composable.ItemRating
import org.haidy.servify.presentation.screens.search.SearchFilter
import org.haidy.servify.presentation.screens.search.SearchViewModel
import org.haidy.servify.presentation.util.EffectHandler

@Composable
fun FilterScreen(viewModel: FilterViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { filterUiEffect, navController ->
        when (filterUiEffect) {
            FilterUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    val navHostController = LocalNavController.current
    val backStackEntry = remember {
        navHostController.getBackStackEntry(ServifyDestination.SEARCH)
    }
    val searchViewModel: SearchViewModel = hiltViewModel(backStackEntry)
    FilterContent(state = state, listener = viewModel) {
        searchViewModel.updateFilter(it)
    }
}

@Composable
fun FilterContent(
    state: FilterUiState,
    listener: FilterInteractionListener,
    updateFilter: (SearchFilter) -> Unit
) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.filter
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(paddingValues)
        ) {
            Text(
                text = Resources.strings.services,
                style = Theme.typography.headline.copy(color = Theme.colors.contrast),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.services) {
                    SearchServiceItem(
                        serviceName = it.name,
                        isSelected = it.name == state.selectedService,
                        onClickService = { serviceName -> listener.onClickService(serviceName) }
                    )
                }
            }

            Text(
                text = Resources.strings.rating,
                style = Theme.typography.headline.copy(color = Theme.colors.contrast),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
            )

            for (rating in 5 downTo 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                        .noRippleEffect { listener.onClickRating(rating.toString()) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ItemRating(rating = rating.toDouble(), startSize = 24, showTextRating = false)
                    Text(
                        text = if (rating == 5) 5.toDouble()
                            .toString() else "${rating.toDouble()} - ${(rating + 1).toDouble()}",
                        style = Theme.typography.titleLarge.copy(color = Theme.colors.contrast),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    RadioButton(
                        selected = (rating.toString() == state.selectedRating),
                        onClick = {
                            listener.onClickRating(rating.toString())
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Theme.colors.blue,
                            unselectedColor = Theme.colors.blue
                        )
                    )
                }
            }

            Text(
                text = Resources.strings.name,
                style = Theme.typography.headline.copy(color = Theme.colors.contrast),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
            )

            ServifyTextField(
                text = state.specialistName,
                onValueChange = { listener.onNameChanged(it) },
                hint = Resources.strings.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val updatedFilter =
                    SearchFilter(state.selectedService, state.specialistName, state.selectedRating)
                ServifyButton(
                    onClick = {
                        updateFilter(SearchFilter())
                        listener.onClickResetFilter()
                    },
                    text = Resources.strings.resetFilter,
                    contentColor = Theme.colors.accent100,
                    containerColor = Theme.colors.accent300,
                    modifier = Modifier.weight(1f),
                )
                ServifyButton(
                    onClick = {
                        updateFilter(updatedFilter)
                        listener.onClickApplyFilter()
                    },
                    text = Resources.strings.applyFilter,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}


@Composable
private fun SearchServiceItem(
    serviceName: String,
    isSelected: Boolean = false,
    onClickService: (String) -> Unit
) {
    val textColor = if (isSelected) Theme.colors.white else Theme.colors.dark300.copy(alpha = 0.5f)
    val containerColor = if (isSelected) Theme.colors.accent100 else Theme.colors.background
    Text(
        text = serviceName,
        style = Theme.typography.title.copy(color = textColor),
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(containerColor)
            .padding(16.dp)
            .noRippleEffect { onClickService(serviceName) }
    )
}
