package org.haidy.servify.presentation.screens.payment.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.theme.localThemeMode
import org.haidy.servify.app.utils.getIsDarkTheme
import org.haidy.servify.domain.model.PaymentCard

@Composable
fun CardItem(card: PaymentCard, modifier: Modifier = Modifier) {
    var isDark by remember { mutableStateOf(false) }
    isDark = getIsDarkTheme(themeMode = localThemeMode.current)
    val elevation = if (isDark) 0.dp else 4.dp
    Surface(elevation = elevation, shape = RoundedCornerShape(Theme.radius.large)) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(Theme.radius.large))
                .background(Theme.colors.card)
                .padding(horizontal = 24.dp, vertical = 32.dp)

        ) {
            Image(
                painter = painterResource(id = card.logoDrawable),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(40.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = formatCardNumber(card.cardNumber),
                    style = Theme.typography.headline.copy(color = Theme.colors.contrast),
                    modifier = Modifier.padding(top = 32.dp)
                )
                Row(modifier = Modifier.padding(vertical = 8.dp)){
                    Text(
                        Resources.strings.cardHolder,
                        style = Theme.typography.titleLarge.copy(
                            color = Theme.colors.dark300.copy(alpha = 0.3f)
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        Resources.strings.expires,
                        style = Theme.typography.titleLarge.copy(
                            color = Theme.colors.dark300.copy(alpha = 0.3f)
                        )
                    )
                }

                Row {
                    Text(
                        card.cardHolder,
                        style = Theme.typography.titleLarge.copy(
                            color = Theme.colors.dark200.copy(alpha = 0.7f)
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        "${ card.expiryDate.month }/${ card.expiryDate.year }",
                        style = Theme.typography.titleLarge.copy(
                            color = Theme.colors.dark200.copy(alpha = 0.7f)
                        )
                    )
                }

            }
        }
    }
}

private fun formatCardNumber(cardNumber: String): String {
    val sanitizedCardNumber = cardNumber.replace("\\s".toRegex(), "")
    return sanitizedCardNumber.chunked(4).joinToString(" ")
}