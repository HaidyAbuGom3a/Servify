package org.haidy.servify.presentation.screens.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.Circle
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.modifier.noRippleEffect

@Composable
fun FavButton(specialistId: String, onFavClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Theme.colors.card)
            .noRippleEffect { onFavClick(specialistId) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = Resources.images.favIcon),
            contentDescription = "",
            tint = Theme.colors.grey300
        )
    }
}