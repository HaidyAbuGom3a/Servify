package org.haidy.servify.presentation.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.modifier.noRippleEffect

@Composable
fun HomeTopBar(
    onClickNotification: () -> Unit,
    onClickMenu: () -> Unit,
    imageUrl: String,
    userName: String
) {
    ServifyAppBar(
        isBackIconVisible = false,
        leading = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                val image = imageUrl.ifEmpty { Resources.images.userImage }
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Column(Modifier.padding(start = 16.dp)) {
                    val bodyLargeStyle = Theme.typography.bodyLarge
                    val helloTitle = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = bodyLargeStyle.fontFamily,
                                fontSize = bodyLargeStyle.fontSize,
                                fontWeight = bodyLargeStyle.fontWeight,
                                color = Theme.colors.contrast
                            )
                        ) {
                            append(Resources.strings.helloUser)
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = bodyLargeStyle.fontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = bodyLargeStyle.fontSize,
                                color = Theme.colors.contrast
                            )
                        ) {
                            append(userName)
                        }
                    }
                    Text(helloTitle)
                    Text(
                        text = Resources.strings.howCanWeHelpYou,
                        style = Theme.typography.body.copy(Theme.colors.contrast),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

            }
        },
        actions = {
            Row( modifier = Modifier.padding(end = 16.dp)){
                Icon(
                    painter = painterResource(id = Resources.images.homeNotificationIcon),
                    contentDescription = "",
                    modifier = Modifier.noRippleEffect { onClickNotification() },
                    tint = Theme.colors.primary100
                )
                Icon(
                    painter = painterResource(id = Resources.images.menuIcon),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .noRippleEffect { onClickMenu() },
                    tint = Theme.colors.primary100
                )
            }
        },
    )
}