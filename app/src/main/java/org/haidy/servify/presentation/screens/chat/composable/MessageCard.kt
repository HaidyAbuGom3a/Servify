package org.haidy.servify.presentation.screens.chat.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.R
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.Message
import org.haidy.servify.presentation.util.convertTimestampToTimeString
import org.haidy.servify.presentation.util.sum

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageCard(message: Message, userId: String, imageUrl: String, showImage: Boolean = false) {
    val isMe = message.senderId == userId
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showImage && !isMe) {
            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        val imagePadding = if(!showImage) 36 else 0
        val columnPaddingValues =
            PaddingValues(start = if (isMe) 0.dp else (12 + imagePadding).dp, end = if (isMe) (12 + imagePadding).dp else 0.dp)
        Column(
            horizontalAlignment = if (isMe) Alignment.End else Alignment.Start,
            modifier = Modifier.padding(columnPaddingValues)
        ) {
            Text(
                text = message.text,
                style = Theme.typography.body.copy(fontFamily = FontFamily(Font(R.font.sora_regular))),
                color = if (isMe) Theme.colors.white else Theme.colors.contrast,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp,
                            bottomStart = if (isMe) 30.dp else 0.dp,
                            bottomEnd = if (isMe) 0.dp else 30.dp
                        )
                    )
                    .background(if (isMe) Theme.colors.accent100 else Theme.colors.grey100)
                    .padding(16.dp)
            )
            Text(
                text = convertTimestampToTimeString(message.createdAt),
                modifier = Modifier.padding(top = 4.dp),
                style = Theme.typography.caption.copy(Theme.colors.contrast),
            )
        }
        if (showImage && isMe) {
            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}