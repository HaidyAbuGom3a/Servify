package org.haidy.servify.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import org.haidy.servify.app.LocalDrawerState
import org.haidy.servify.app.LocalPaddingValues
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyLoading
import org.haidy.servify.presentation.composable.ServifyNavigationDrawer
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.screens.home.composable.CarouselAutoPlayHandler
import org.haidy.servify.presentation.screens.home.composable.HomeTopBar
import org.haidy.servify.presentation.screens.home.composable.ItemSection
import org.haidy.servify.presentation.screens.home.composable.ItemService
import org.haidy.servify.presentation.screens.login.navigateToLogin
import org.haidy.servify.presentation.screens.onBoarding.composable.PagerIndicator
import org.haidy.servify.presentation.screens.payment.addCard.navigateToAddCard
import org.haidy.servify.presentation.screens.profile.navigateToProfile
import org.haidy.servify.presentation.screens.services.navigateToServices
import org.haidy.servify.presentation.screens.settings.navigateToSettings
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.OnLifecycleEvent

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    if (LocalDrawerState.current.isOpen) {
        systemUiController.setStatusBarColor(Theme.colors.drawer)
    } else {
        systemUiController.setStatusBarColor(Theme.colors.background)
    }
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            is HomeUiEffect.NavigateToProfile -> navController.navigateToProfile()
            is HomeUiEffect.NavigateToSettings -> navController.navigateToSettings()
            is HomeUiEffect.NavigateToNotifications -> {}
            HomeUiEffect.NavigateToLogin -> navController.navigateToLogin(true)
            HomeUiEffect.NavigateToBestSpecialists -> TODO()
            HomeUiEffect.NavigateToServices -> navController.navigateToServices()
            HomeUiEffect.NavigateToAddCard -> navController.navigateToAddCard()
            HomeUiEffect.NavigateToAddService -> TODO()
        }

    }
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.refreshData()
            }

            else -> {}
        }
    }
    ServifyNavigationDrawer(
        drawerState = LocalDrawerState.current,
        username = state.userName,
        listener = viewModel
    ) {
        HomeContent(state, viewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeContent(state: HomeUiState, listener: HomeInteractionListener) {
    val scope = rememberCoroutineScope()
    val drawerState = LocalDrawerState.current
    //val pullToRefreshState = rememberPullToRefreshState()
    Scaffold(
        topBar = {
            HomeTopBar(
                onClickMenu = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                onClickNotification = {},
                imageUrl = state.userImageUrl,
                userName = state.userName
            )
        },
    ) { paddingValues ->
        val localPaddingValues = LocalPaddingValues.current
        val overallPaddingValues = PaddingValues(
            top = paddingValues.calculateTopPadding() + localPaddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding() + localPaddingValues.calculateBottomPadding(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(overallPaddingValues)
        ) {

            ServifyTextField(
                text = "",
                onValueChange = {},
                hint = Resources.strings.search,
                readOnly = true,
                leadingPainter = painterResource(id = Resources.images.searchIcon),
                iconTint = Theme.colors.dark300.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp)
            )

            AnimatedVisibility(visible = state.offers.isNotEmpty()) {
                ItemSection(
                    title = Resources.strings.offers,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        val pagerState = rememberPagerState()
                        HorizontalPager(
                            pageCount = state.offers.size,
                            state = pagerState,
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            pageSpacing = 16.dp
                        ) { page ->
                            Image(
                                painter = rememberAsyncImagePainter(model = state.offers[page]),
                                contentDescription = "",
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(Theme.radius.medium)),
                                contentScale = ContentScale.Crop
                            )
                        }
                        PagerIndicator(
                            pagerState = pagerState,
                            numOfPages = state.offers.size,
                            circleSpacing = 4.dp,
                            activeLineWidth = 16.dp,
                            modifier = Modifier.padding(top = 16.dp)
                        )

                        CarouselAutoPlayHandler(
                            pagerState = pagerState,
                            carouselSize = state.offers.size
                        )
                    }

                }

            }

            ItemSection(
                title = Resources.strings.services,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                hasShowMore = true,
                onClickShowMore = { listener.onClickShowAllServices() }
            ) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
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
                            title = service.name
                        )
                    }
                }
            }

            ItemSection(
                title = Resources.strings.bestSpecialists,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                hasShowMore = true,
                onClickShowMore = { listener.onClickShowAllSpecialists() }
            ) {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                }
            }

        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AnimatedVisibility(visible = state.isLoading) {
                ServifyLoading(isLoading = state.isLoading)
            }
        }

    }
}