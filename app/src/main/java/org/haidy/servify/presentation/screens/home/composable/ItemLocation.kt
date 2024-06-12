package org.haidy.servify.presentation.screens.home.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme

@Composable
fun ItemLocation(
    governorate: String,
    country: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 12.sp,
    iconWidth: Dp = 10.dp,
    iconHeight: Dp = 12.dp
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = Resources.images.locationIcon),
            contentDescription = "",
            tint = Theme.colors.accent100,
            modifier = Modifier.width(iconWidth).height(iconHeight)
        )
        Text(
            text = "$governorate/$country",
            style = Theme.typography.caption,
            modifier = Modifier.padding(start = 4.dp),
            fontSize = fontSize
        )
    }
}