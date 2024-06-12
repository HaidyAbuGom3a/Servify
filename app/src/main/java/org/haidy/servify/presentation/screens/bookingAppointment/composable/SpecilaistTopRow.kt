package org.haidy.servify.presentation.screens.bookingAppointment.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.presentation.screens.home.composable.ItemLocation

@Composable
fun SpecialistTobRow(
    specialist: Specialist,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Image(
            painter = rememberAsyncImagePainter(model = specialist.imageUrl),
            modifier = Modifier
                .size(92.dp)
                .clip(CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(start = 16.dp)){
            Text(
                text = specialist.name,
                style = Theme.typography.titleLarge.copy(
                    color = Theme.colors.contrast,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = specialist.service.name,
                style = Theme.typography.title.copy(
                    color = Theme.colors.dark200.copy(alpha = 0.6f),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 4.dp)

            )
            ItemLocation(
                country = specialist.location.country,
                governorate = specialist.location.governorate,
                fontSize =  14.sp,
                iconWidth = 14.dp,
                iconHeight = 17.dp,
            )
        }
    }
}