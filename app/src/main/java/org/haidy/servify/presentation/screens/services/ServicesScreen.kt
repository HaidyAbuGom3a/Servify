package org.haidy.servify.presentation.screens.services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.screens.home.composable.ItemService
import org.haidy.servify.presentation.util.sum

@Composable
fun ServicesScreen(viewModel: ServicesViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    ServicesContent(state = state, listener = viewModel)
}

@Composable
fun ServicesContent(state: ServicesUiState, listener: ServicesInteractionListener) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.services
            )
        }
    ) { paddingValues ->

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().background(Theme.colors.background),
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(vertical = 32.dp, horizontal = 8.dp).sum(
                otherPaddingValues = paddingValues
            ),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.services) { service ->
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(service.imageUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                )
                ItemService(
                    onClick = { },
                    painter = painter,
                    title = service.name,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}