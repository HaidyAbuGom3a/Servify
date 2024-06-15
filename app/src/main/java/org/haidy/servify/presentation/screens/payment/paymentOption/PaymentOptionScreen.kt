package org.haidy.servify.presentation.screens.payment.paymentOption

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.payment.paymentSuccess.navigateToPaymentSuccess
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.sum

@Composable
fun PaymentOptionScreen(viewModel: PaymentOptionViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            PaymentOptionUiEffect.NavigateToPaymentSuccess -> navController.navigateToPaymentSuccess()
            PaymentOptionUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    PaymentOptionContent(state = state, listener = viewModel)
}

@Composable
fun PaymentOptionContent(state: PaymentOptionUiState, listener: PaymentOptionInteractionListener) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.paymentOption
            )
        },
        bottomBar = {
            Surface(
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                shadowElevation = 4.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(94.dp)
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(Theme.colors.background), contentAlignment = Alignment.Center
                ) {
                    ServifyButton(
                        onClick = { listener.onClickProceed() },
                        text = Resources.strings.proceed,
                        buttonRadius = 24.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    ) {
        val scrollState = rememberLazyListState()
        LazyColumn(
            contentPadding = it.sum(otherPaddingValues = PaddingValues(vertical = 24.dp)),
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                if (state.cards.isNotEmpty()) {
                    Text(
                        text = Resources.strings.cards,
                        style = Theme.typography.titleLarge.copy(
                            color = Theme.colors.contrast,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }
            }
            itemsIndexed(state.cards) { index, card ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .noRippleEffect { listener.onClickMethod(index) }
                        .padding(bottom = 8.dp)
                )
                {
                    RadioButton(
                        selected = (card == state.selectedCard),
                        onClick = {
                            listener.onClickMethod(index)
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Theme.colors.blue,
                            unselectedColor = Theme.colors.blue
                        )
                    )
                    Image(
                        painter = rememberAsyncImagePainter(model = card.logoDrawable),
                        contentDescription = null,
                        modifier = Modifier
                            .height(35.dp)
                            .width(40.dp),
                        contentScale = ContentScale.Fit
                    )
                    val maskedCardNumber = maskCardNumber(card.cardNumber)
                    Text(
                        text = maskedCardNumber,
                        style = Theme.typography.body.copy(Theme.colors.dark100),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = rememberAsyncImagePainter(model = Resources.images.cardIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp),
                        tint = Theme.colors.contrast
                    )
                }
            }
            item {
                Text(
                    text = Resources.strings.cash,
                    style = Theme.typography.titleLarge.copy(
                        color = Theme.colors.contrast,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 24.dp, top = 24.dp)
                )
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .noRippleEffect { listener.onClickMethod(state.cards.size) }
                        .padding(bottom = 8.dp)
                )
                {
                    RadioButton(
                        selected = (state.selectedPaymentMethod == PaymentMethod.CASH),
                        onClick = {
                            listener.onClickMethod(state.cards.size)
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Theme.colors.blue,
                            unselectedColor = Theme.colors.blue
                        )
                    )
                    Text(
                        text = Resources.strings.cash,
                        style = Theme.typography.body.copy(Theme.colors.dark100),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = rememberAsyncImagePainter(model = Resources.images.cashIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp),
                        tint = Theme.colors.contrast
                    )
                }
            }

        }
    }
}

private fun maskCardNumber(cardNumber: String): String {
    val digitsOnly = cardNumber.filter { it.isDigit() }

    if (digitsOnly.length <= 4) return digitsOnly

    val maskedPart = "*".repeat(digitsOnly.length - 4)
    val lastFourDigits = digitsOnly.takeLast(4)

    return maskedPart + lastFourDigits
}