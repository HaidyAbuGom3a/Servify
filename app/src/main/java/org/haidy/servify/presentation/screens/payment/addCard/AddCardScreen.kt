package org.haidy.servify.presentation.screens.payment.addCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.payment.addPaymentMethod.navigateToAddPaymentMethod
import org.haidy.servify.presentation.screens.payment.composable.CardItem
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.sum

@Composable
fun AddCardScreen(viewModel: AddCardViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            AddCardUiEffect.NavigateToAddPaymentMethod -> navController.navigateToAddPaymentMethod()
            AddCardUiEffect.NavigateUp -> navController.navigateUp()
        }

    }
    AddCardContent(state, viewModel)
}

@Composable
fun AddCardContent(state: AddCardUiState, listener: AddCardInteractionListener) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.addPaymentMethod
            )
        }
    ) {
        LazyColumn(
            contentPadding = it.sum(
                otherPaddingValues = PaddingValues(
                    vertical = 24.dp,
                    horizontal = 16.dp
                )
            ),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(Theme.radius.large))
                        .noRippleEffect { listener.onClickAddCard() }
                        .background(Theme.colors.cardGrey)
                        .padding(horizontal = 16.dp, vertical = 24.dp), contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = Resources.images.plusIcon),
                            contentDescription = null,
                            tint = Theme.colors.contrast,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = Resources.strings.addCard,
                            style = Theme.typography.titleLarge.copy(Theme.colors.contrast),
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
            }

            items(state.cards) { card ->
                CardItem(
                    card = card,
                )
            }
        }
    }
}