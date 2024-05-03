package org.haidy.servify.presentation.screens.location.composable

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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.home.composable.ItemLocation
import org.haidy.servify.presentation.util.bottomBorder
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun ItemLocationSpecialist(onClickDirection: (Specialist) -> Unit, specialist: Specialist) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Theme.colors.card)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .bottomBorder(
                    (1.5).dp,
                    Theme.colors.accent100
                )
                .padding(top = 12.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(specialist.imageUrl),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 8.dp, end = 24.dp)) {
                Text(
                    text = specialist.name, style = Theme.typography.titleLarge,
                    color = Theme.colors.contrast
                )
                ItemLocation(
                    country = specialist.location.country,
                    governorate = specialist.location.governorate,
                    modifier = Modifier.padding(top = 4.dp)
                )
                ItemRating(rating = specialist.rating, modifier = Modifier.padding(top = 4.dp))
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Theme.colors.accent100)
                    .size(40.dp)
                    .noRippleEffect { onClickDirection(specialist) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = Resources.images.directionIcon),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
        Row(
            modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = Resources.images.walkIcon),
                contentDescription = "",
                tint = Theme.colors.accent100,
            )
            val distance = getDistanceFromLatLonInMeters(
                lat2 = specialist.location.latitude,
                lon2 = specialist.location.longitude
            )
            Text(
                text = "${distance.toInt()} meters",
                style = Theme.typography.body,
                modifier = Modifier.padding(start = 4.dp),
                color = Theme.colors.contrast
            )
        }
    }
}


private fun getDistanceFromLatLonInMeters(
    lat1: Double = 31.426378853648238,
    lon1: Double = 31.651234867462943,
    lat2: Double,
    lon2: Double
): Double {
    val r = 6371000.0 // Radius of the earth in meters
    val dLat = deg2rad(lat2 - lat1) // deg2rad below
    val dLon = deg2rad(lon2 - lon1)
    val a =
        sin(dLat / 2) * sin(dLat / 2) +
                cos(deg2rad(lat1)) * cos(deg2rad(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return r * c // Distance in meters
}

private fun deg2rad(deg: Double): Double {
    return deg * (PI / 180)
}