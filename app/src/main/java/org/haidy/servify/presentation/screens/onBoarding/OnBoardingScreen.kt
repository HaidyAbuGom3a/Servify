package org.haidy.servify.presentation.screens.onBoarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.login.navigateToLogin
import org.haidy.servify.presentation.screens.onBoarding.composable.PagerIndicator
import org.haidy.servify.presentation.screens.onBoarding.composable.ServifyCircularProgressIndicator
import org.haidy.servify.presentation.util.EffectHandler

@Composable
fun OnBoardingScreen(viewModel: OnBoardingViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            OnBoardingUiEffect.NavigateToLogin -> navController.navigateToLogin(true)
        }
    }
    OnBoardingContent(state, viewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingContent(state: OnBoardingUiState, listener: OnBoardingInteractionListener) {
    val pagerState = rememberPagerState()
    val pages = state.pages

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        topBar = {
            val scope = rememberCoroutineScope()
            ServifyAppBar(
                onNavigateUp = {
                    scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                },
                isBackIconVisible = pagerState.currentPage > 0,
                backIconBackground = Theme.colors.accent100,
                backIconTint = Color.White,
                actions = {
                    if (pagerState.currentPage < pages.lastIndex) Text(
                        text = Resources.strings.skip,
                        style = Theme.typography.titleLarge.copy(color = Theme.colors.accent100),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .noRippleEffect {
                                scope.launch {
                                    pagerState.animateScrollToPage(pages.lastIndex)
                                }
                            }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                pageCount = pages.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(525.dp)
                    .padding(top = 16.dp)
            ) { currentPage ->

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(pages[currentPage].imageDrawable),
                        contentDescription = null,
                        modifier = Modifier.size(350.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = pages[currentPage].title,
                        style = Theme.typography.headlineLarge,
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                        textAlign = TextAlign.Center,
                        color = Theme.colors.dark100,
                    )
                    Text(
                        text = pages[currentPage].description,
                        style = Theme.typography.bodyLarge,
                        modifier = Modifier
                            .height(50.dp)
                            .padding(start = 32.dp, end = 32.dp, top = 16.dp),
                        color = Theme.colors.dark200,
                        textAlign = TextAlign.Center
                    )
                }

            }

            PagerIndicator(
                pagerState = pagerState,
                numOfPages = pages.size,
                modifier = Modifier.padding(end = 48.dp)
            )

            val progress = (pagerState.currentPage + 1) / pages.size.toFloat()
            AnimatedVisibility(visible = pagerState.currentPage < pages.lastIndex) {
                ServifyCircularProgressIndicator(progress = progress, pagerState = pagerState)
            }
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(visible = pagerState.currentPage == pages.lastIndex) {
                ServifyButton(
                    onClick = { listener.onClickGetStarted() },
                    text = Resources.strings.getStarted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 56.dp, start = 24.dp, end = 24.dp),
                )
            }

        }
    }

}

