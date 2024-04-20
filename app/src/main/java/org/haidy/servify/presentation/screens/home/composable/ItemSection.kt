package org.haidy.servify.presentation.screens.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.utils.LanguageCode
import org.haidy.servify.presentation.modifier.noRippleEffect

@Composable
fun ItemSection(
    title: String,
    modifier: Modifier = Modifier,
    hasShowMore: Boolean = false,
    onClickShowMore: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, style = Theme.typography.headline.copy(color = Theme.colors.contrast))
            Spacer(modifier = Modifier.weight(1f))
            if (hasShowMore) {
                Text(
                    text = Resources.strings.showMore,
                    style = Theme.typography.caption.copy(color = Theme.colors.dark200),
                    modifier = Modifier.noRippleEffect { onClickShowMore() })
                val iconModifier = if(Resources.languageCode.value != LanguageCode.EN ) Modifier.size(10.dp)
                else Modifier.size(10.dp).rotate(180f)
                Icon(
                    painter = painterResource(id = Resources.images.backIcon),
                    contentDescription = "",
                    modifier = iconModifier,
                    tint = Theme.colors.dark200,
                )
            }
        }
        content()
    }
}