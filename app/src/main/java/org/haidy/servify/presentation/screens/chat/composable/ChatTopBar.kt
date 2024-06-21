package org.haidy.servify.presentation.screens.chat.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.modifier.noRippleEffect

@Composable
fun ChatTopBar(
    onClickBackIcon: () -> Unit,
    otherUserName: String,
    otherUserImageUrl: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.colors.background)
        ) {
            val rotationDegree = when (LocalLayoutDirection.current) {
                LayoutDirection.Ltr -> 0f
                LayoutDirection.Rtl -> 180f
            }
            Icon(
                painter = painterResource(id = Resources.images.backIcon),
                contentDescription = "",
                modifier = Modifier
                    .noRippleEffect { onClickBackIcon() }
                    .padding(8.dp)
                    .rotate(rotationDegree),
                tint = Theme.colors.primary100,
            )
        }
        val image = otherUserImageUrl.ifEmpty { Resources.images.userImage }
        Image(
            painter = rememberAsyncImagePainter(model = image),
            contentDescription = "",
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(Modifier.padding(start = 16.dp)) {
            Text(
                otherUserName,
                style = Theme.typography.headline.copy(
                    color = Theme.colors.contrast,
                    fontSize = 18.sp
                ),
            )
        }
    }
}