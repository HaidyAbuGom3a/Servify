package org.haidy.servify.presentation.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.Service

@Composable
fun ItemSpecialist(
    onClickFav: (String) -> Unit,
    name: String,
    service: Service,
    governorate: String,
    country: String,
    rating: Double,
    isFav: Boolean,
    serviceDiscount: Double,
    specialistId: String,
) {
    Row {
        Card(
            modifier = Modifier
                .height(180.dp)
                .weight(1f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Theme.colors.card)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(4.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(service.imageUrl),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    FavButton(
                        specialistId = specialistId,
                        onFavClick = onClickFav,
                        modifier = Modifier.padding(top = 4.dp, end = 4.dp)
                    )
                }
            }



        }
    }
}
