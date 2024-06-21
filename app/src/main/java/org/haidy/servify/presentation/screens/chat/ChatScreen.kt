package org.haidy.servify.presentation.screens.chat

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.screens.chat.composable.ChatTopBar
import org.haidy.servify.presentation.screens.chat.composable.MessageCard
import org.haidy.servify.presentation.util.EffectHandler

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            ChatUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    ChatScreenContent(state = state, listener = viewModel)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreenContent(state: ChatUiState, listener: ChatInteractionListener) {
    Scaffold(topBar = {
        ChatTopBar(
            onClickBackIcon = { listener.onClickBackIcon() },
            otherUserName = state.otherUserName,
            otherUserImageUrl = state.otherUserImageUrl
        )
    }, bottomBar = {
        Surface(
            shadowElevation = 4.dp,
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            ServifyTextField(
                text = state.text,
                onValueChange = { listener.onTextChanged(it) },
                leadingPainter = painterResource(id = Resources.images.emojiIcon),
                containerColor = Theme.colors.card,
                radius = 28.dp,
                hint = Resources.strings.typeMessageHere,
                trailingPainter = painterResource(id = Resources.images.sendIcon),
                trailingIconTint = Theme.colors.white,
                leadingIconTint = Theme.colors.contrast,
                trailingIconButtonColors = IconButtonDefaults
                    .iconButtonColors(containerColor = Theme.colors.accent100),
                trailingIconButtonModifier = Modifier
                    .clip(CircleShape)
                    .padding(2.dp),
                onLeadingIconClick = {},
                onTrailingIconClick = { listener.onClickSend() }
            )
        }
    }) {
        val listState = rememberLazyListState()
        LaunchedEffect(key1 = state.messages.size) {
            if (state.messages.isNotEmpty()) listState.scrollToItem(state.messages.lastIndex)
        }
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(vertical = 32.dp, horizontal = 24.dp),
            state = listState,
            verticalArrangement = if (state.messages.isEmpty()) Arrangement.Center else Arrangement.Bottom,
            horizontalAlignment = if (state.messages.isEmpty()) Alignment.CenterHorizontally else Alignment.Start
        ) {
            itemsIndexed(state.messages) { index, message ->
                val imageUrl =
                    if (message.senderId == state.userId) state.userImageUrl else state.otherUserImageUrl
                val isImageVisible =
                    (index == 0) || (index > 0 && state.messages[index].senderId != state.messages[index - 1].senderId)
                MessageCard(
                    message = message,
                    userId = state.userId,
                    imageUrl = imageUrl,
                    showImage = isImageVisible
                )
            }
        }
    }
}


