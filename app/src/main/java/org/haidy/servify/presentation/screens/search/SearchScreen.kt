package org.haidy.servify.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.home.composable.ItemSpecialist
import org.haidy.servify.presentation.screens.searchFilter.navigateToFilter
import org.haidy.servify.presentation.util.EffectHandler

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            SearchUiEffect.NavigateUp -> navController.popBackStack()
            SearchUiEffect.NavigateToFilter -> navController.navigateToFilter()
        }
    }
    SearchContent(state = state, listener = viewModel)
}

@Composable
fun SearchContent(state: SearchUiState, listener: SearchInteractionListener) {
    Scaffold(
        topBar = {
            Column {
                ServifyAppBar(
                    onNavigateUp = { listener.onClickBackIcon() },
                    isBackIconVisible = true,
                    title = Resources.strings.titleSearch
                )
                ServifyTextField(
                    text = state.searchText,
                    onValueChange = { listener.onTextChanged(it) },
                    hint = Resources.strings.titleSearch,
                    leadingPainter = painterResource(id = Resources.images.searchIcon),
                    iconTint = Theme.colors.dark300.copy(alpha = 0.7f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 16.dp),
                    onLeadingIconClick = { listener.onClickSearchIcon() }
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    Text(
                        text = Resources.strings.result,
                        style = Theme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Theme.colors.dark300.copy(alpha = 0.7f)
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = rememberAsyncImagePainter(model = Resources.images.filterIcon),
                        contentDescription = null,
                        tint = Theme.colors.contrast,
                        modifier = Modifier
                            .size(24.dp)
                            .noRippleEffect { listener.onClickFilter() }
                    )
                }

            }
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(paddingValues),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = 32.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            items(state.specialists) { specialist ->
                ItemSpecialist(
                    onClickFav = {},
                    onClickBookNow = { },
                    onClickMessage = {},
                    onClickCall = {},
                    specialist = specialist,
                    cardWidth = 130.dp
                )
            }
        }
    }
}