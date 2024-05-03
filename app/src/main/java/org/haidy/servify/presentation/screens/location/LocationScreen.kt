package org.haidy.servify.presentation.screens.location

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.haidy.servify.app.LocalPaddingValues
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.Location
import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.screens.location.composable.ItemLocationSpecialist
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.OnLifecycleEvent
import org.haidy.servify.presentation.util.sum

@Composable
fun LocationScreen(viewModel: LocationViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            LocationUiEffect.NavigateBack -> navController.popBackStack()
        }

    }
    LocationContent(state, viewModel)
}


@Composable
fun LocationContent(state: LocationUiState, listener: LocationInteractionListener) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.nearestSpecialists
            )
        }
    ) { paddingValues ->
        val localPaddingValues = LocalPaddingValues.current
        val totalPaddingValues = localPaddingValues.sum(otherPaddingValues = paddingValues)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(totalPaddingValues),
            contentAlignment = Alignment.BottomCenter
        ) {
            GoogleMap(currentSpecialist = state.currentSpecialist)
            LazyRow(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                items(state.nearestSpecialists) { specialist ->
                    ItemLocationSpecialist(
                        { spec -> listener.onClickLocation(spec) },
                        specialist,
                    )
                }
            }
        }
    }
}

@Composable
fun GoogleMap(currentSpecialist: Specialist?) {
    val mapView = rememberMapViewWithLifeCycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background)
    ) {
        AndroidView(
            { mapView }
        ) { mapView ->
            CoroutineScope(Dispatchers.Main).launch {
                mapView.getMapAsync { googleMap ->
                    googleMap.uiSettings.isZoomControlsEnabled = true
                    currentSpecialist?.let{
                        val pickUp = LatLng(
                            it.location.latitude,
                            it.location.longitude
                        )
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pickUp, 15f))
                        val markerOptions = MarkerOptions()
                            .title(it.name)
                            .position(pickUp)
                        googleMap.addMarker(markerOptions)
                    }
                }
            }
        }
    }
}

@Composable
fun rememberMapViewWithLifeCycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = com.google.android.gms.maps.R.id.normal
        }
    }
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
            Lifecycle.Event.ON_START -> mapView.onStart()
            Lifecycle.Event.ON_RESUME -> mapView.onResume()
            Lifecycle.Event.ON_PAUSE -> mapView.onPause()
            Lifecycle.Event.ON_STOP -> mapView.onStop()
            else -> {}
        }
    }
    return mapView
}