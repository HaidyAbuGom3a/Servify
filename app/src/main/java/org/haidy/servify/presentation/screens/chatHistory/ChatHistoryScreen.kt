package org.haidy.servify.presentation.screens.chatHistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.R
import org.haidy.servify.app.LocalPaddingValues
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.chat.navigateToChat
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.bottomBorder
import org.haidy.servify.presentation.util.calculateTimeDifference
import org.haidy.servify.presentation.util.sum

@Composable
fun ChatHistoryScreen(viewModel: ChatHistoryViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            is ChatHistoryUiEffect.NavigateToChat -> navController.navigateToChat(effect.otherUseId)
            ChatHistoryUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    ChatHistoryContent(state = state, listener = viewModel)
}

@Composable
fun ChatHistoryContent(state: ChatHistoryUiState, listener: ChatHistoryInteractionListener) {
    Scaffold(
        topBar = {
            Column {
                ServifyAppBar(
                    onNavigateUp = { listener.onClickBackIcon() },
                    isBackIconVisible = true,
                    title = Resources.strings.chats
                )
            }

        }
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 32.dp).sum(
                otherPaddingValues = it.sum(
                    otherPaddingValues = LocalPaddingValues.current
                )
            ),
        ) {
            items(state.chats) { chat ->
                ChatHistoryItem(
                    onClickItem = { listener.onClickChat(chat.otherUserId) },
                    otherUserImageUrl = chat.otherUserImageUrl,
                    otherUserName = chat.otherUserName,
                    lastMessage = chat.lastMessage,
                    otherUserId = chat.otherUserId,
                    lastUpdate = calculateTimeDifference(
                        chat.updatedAt,
                        Resources.languageCode.value
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun ChatHistoryItem(
    onClickItem: (String) -> Unit,
    otherUserName: String,
    otherUserId: String,
    otherUserImageUrl: String,
    lastMessage: String,
    lastUpdate: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
            .noRippleEffect {
                onClickItem(otherUserId)
            }
            .bottomBorder(
                (1).dp,
                Theme.colors.primary300
            )
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = otherUserImageUrl),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(
                    otherUserName,
                    style = Theme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(R.font.sora_medium)),
                        color = Theme.colors.contrast
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    lastUpdate, style = Theme.typography.caption.copy(
                        fontFamily = FontFamily(Font(R.font.sora_regular)),
                        color = Theme.colors.contrast
                    )
                )
            }
            Text(
                lastMessage,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                style = Theme.typography.caption.copy(
                    fontFamily = FontFamily(Font(R.font.sora_regular)),
                    color = Theme.colors.dark200.copy(alpha = 0.7f)
                )
            )
        }
    }
}