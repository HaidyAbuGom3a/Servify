package org.haidy.servify.presentation.screens.home.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)
@Composable
fun CarouselAutoPlayHandler(
    pagerState: PagerState,
    carouselSize: Int
) {
    var pageKey by remember { mutableStateOf(0) }

    val effectFlow = MutableSharedFlow<Interaction>()

    LaunchedEffect(effectFlow) {
        effectFlow.collectLatest {
            if (it is DragInteraction.Stop) pageKey++
        }
    }

    LaunchedEffect(pageKey) {
        delay(5000)
        val newPage = (pagerState.currentPage + 1) % carouselSize
        pagerState.animateScrollToPage(newPage)
        pageKey++
    }
}
