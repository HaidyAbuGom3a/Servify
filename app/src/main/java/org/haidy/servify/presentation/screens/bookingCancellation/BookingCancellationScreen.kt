package org.haidy.servify.presentation.screens.bookingCancellation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.util.EffectHandler

@Composable
fun BookingCancellationScreen(viewModel: BookingCancellationViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val toastMsg = Resources.strings.orderCancelled
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            BookingCancellationUiEffect.NavigateUp -> navController.popBackStack()
            BookingCancellationUiEffect.ShowSuccessMessage -> {
                Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }
    BookingCancellationContent(state, viewModel)
}

@Composable
fun BookingCancellationContent(
    state: BookingCancellationUiState,
    listener: BookingCancellationInteractionListener
) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBack() },
                isBackIconVisible = true,
                title = Resources.strings.bookingCancellation
            )
        },
        bottomBar = {
            ServifyButton(
                onClick = { listener.onClickConfirm() },
                text = Resources.strings.confirm,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues, modifier = Modifier
                .background(Theme.colors.background)
        ) {
            item {
                Text(
                    text = Resources.strings.whyDoYouWantToCancel,
                    style = Theme.typography.titleLarge.copy(
                        color = Theme.colors.contrast,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .background(Theme.colors.background)
                        .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                )
            }
            item {
                val radioOptions = listOf(
                    Resources.strings.haveYouExperiencedChanges,
                    Resources.strings.didYouEncounterProblemWithService,
                    Resources.strings.haveYouDiscoveredBetterOptions,
                    Resources.strings.unexpectedFinancialProblems,
                    Resources.strings.travelPlans,
                    Resources.strings.anotherReason
                )
                radioOptions.forEach { optionReason ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .noRippleEffect { listener.onClickReason(optionReason) })
                    {
                        RadioButton(
                            selected = (optionReason == state.selectedReason),
                            onClick = {
                                listener.onClickReason(optionReason)
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Theme.colors.blue,
                                unselectedColor = Theme.colors.blue
                            )
                        )
                        Text(
                            text = optionReason,
                            style = Theme.typography.body.copy(Theme.colors.dark100),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            item {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 32.dp, bottom = 24.dp),
                    thickness = 1.dp,
                    color = Theme.colors.accent100
                )
            }

            item {
                ServifyTextField(
                    text = state.reason,
                    onValueChange = { listener.onReasonChanged(it) },
                    hint = Resources.strings.addReasonHere,
                    minHeight = 188,
                    isSingleLine = false,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
                    readOnly = state.selectedReason != Resources.strings.anotherReason
                )
            }

        }
    }
}