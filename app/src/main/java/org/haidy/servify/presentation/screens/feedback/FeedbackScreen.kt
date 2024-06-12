package org.haidy.servify.presentation.screens.feedback

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.screens.feedback.composable.ServiceFeedbackItem
import org.haidy.servify.presentation.screens.home.navigateToHome
import org.haidy.servify.presentation.screens.location.composable.ItemRating
import org.haidy.servify.presentation.util.EffectHandler

@Composable
fun FeedbackScreen(viewModel: FeedbackViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val toastMsg = Resources.strings.feedbackSubmittedSuccessfully
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            FeedbackUiEffect.NavigateToHome -> navController.navigateToHome(true)
            FeedbackUiEffect.NavigateUp -> navController.popBackStack()
            FeedbackUiEffect.ShowSuccessMessage -> {
                Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }
    FeedbackContent(state = state, listener =viewModel )
}

@Composable
fun FeedbackContent(state: FeedbackUiState, listener: FeedbackInteractionListener) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.feedback
            )
        },
        bottomBar = {
            ServifyButton(
                onClick = { listener.onClickSubmitFeedback() },
                text = Resources.strings.submitFeedback,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(Theme.colors.background),
            contentPadding = PaddingValues(vertical = 32.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                ServiceFeedbackItem(
                    serviceName = state.serviceName,
                    specialistName = state.specialistName,
                    serviceImagePainter = rememberAsyncImagePainter(model = state.specialistUrl),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
            item {
                Text(
                    text = Resources.strings.howWouldYouRateExperienceAndService,
                    textAlign = TextAlign.Center,
                    style = Theme.typography.bodyLarge.copy(color = Theme.colors.contrast),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
            item {
                ItemRating(
                    showTextRating = false,
                    rating = state.rating,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    onClick = { index ->
                        listener.onClickStar(index.toInt())
                    },
                    startSize = 35
                )
            }
            item {
                val rating = when (state.rating.toInt()) {
                    1 -> Resources.strings.poor
                    2 -> Resources.strings.fair
                    3 -> Resources.strings.average
                    4 -> Resources.strings.good
                    else -> Resources.strings.excellent
                }
                Text(
                    text = "${state.rating.toInt()} - $rating",
                    textAlign = TextAlign.Center,
                    style = Theme.typography.bodyLarge.copy(color = Theme.colors.contrast),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }

            item {
                ServifyTextField(
                    text = state.feedback,
                    onValueChange = { listener.onFeedbackChanged(it) },
                    hint = Resources.strings.writeFeedbackHere,
                    minHeight = 188,
                    isSingleLine = false
                )
            }
        }
    }
}