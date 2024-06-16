package org.haidy.servify.presentation.screens.payment.addPaymentMethod

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.Date
import org.haidy.servify.domain.model.PaymentCard
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.screens.home.navigateToHome
import org.haidy.servify.presentation.screens.payment.composable.CardItem
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.sum

@Composable
fun AddPaymentMethodScreen(viewModel: AddPaymentMethodViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            AddPaymentMethodUiEffect.NavigateToHome -> navController.navigateToHome(clearBackStack = true)
            AddPaymentMethodUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    AddPaymentMethodContent(state = state, listener = viewModel)
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AddPaymentMethodContent(
    state: AddPaymentMethodUiState,
    listener: AddPaymentMethodInteractionListener
) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.addPaymentMethod
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
                        .background(Theme.colors.card), contentAlignment = Alignment.Center
                ) {
                    ServifyButton(
                        onClick = { listener.onClickSave() },
                        text = Resources.strings.save,
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
                .padding(horizontal = 16.dp)
        ) {
            item {
                val date = state.cardExpiryDate.split('/')
                val card = PaymentCard(
                    cardHolder = state.cardHolder,
                    expiryDate = Date(date.first(), date.last()),
                    cardNumber = state.cardNumber,
                    cardType = state.cardType,
                    securityCode = state.securityCode
                )
                CardItem(
                    card = card,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                ServifyTextField(
                    text = state.cardNumber,
                    hint = "0000 0000 0000 0000",
                    onValueChange = { cardNumber -> listener.onCardNumberChanged(cardNumber) },
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
                )
            }
            item {
                rememberCoroutineScope().launch {
                    scrollState.animateScrollToItem(3)
                }
                ServifyTextField(
                    text = state.cardHolder,
                    hint = Resources.strings.cardHolder,
                    onValueChange = { cardHolder -> listener.onCardHolderChanged(cardHolder) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }

            item {
                rememberCoroutineScope().launch {
                    scrollState.animateScrollToItem(3)
                }
                ServifyTextField(
                    text = state.cardExpiryDate,
                    hint = Resources.strings.expiryDate,
                    onValueChange = { expiryDate -> listener.onExpiryDateChanged(expiryDate) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
            item {
                rememberCoroutineScope().launch {
                    scrollState.animateScrollToItem(4)
                }
                ServifyTextField(
                    text = state.securityCode,
                    hint = Resources.strings.securityCode,
                    onValueChange = { securityCode -> listener.onSecurityCodeChanged(securityCode) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}