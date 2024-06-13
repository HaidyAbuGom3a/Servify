package org.haidy.servify.presentation.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.modifier.noRippleEffect

@Composable
fun ItemSpecialist(
    onClickFav: (String) -> Unit,
    onClickBookNow: (String) -> Unit,
    onClickMessage: (String) -> Unit,
    onClickCall: (String) -> Unit,
    specialist: Specialist,
    cardWidth: Dp = 170.dp
) {
    Surface(shadowElevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
        Card(
            modifier = Modifier
                .width(cardWidth),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Theme.colors.card)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .padding(4.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(specialist.imageUrl),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Row(modifier = Modifier.padding(top = 4.dp, end = 4.dp)) {
                    Spacer(modifier = Modifier.weight(1f))
                    FavButton(
                        specialistId = specialist.id,
                        onFavClick = onClickFav,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = Resources.images.filledStartIcon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(14.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = specialist.rating.toString(),
                    style = Theme.typography.body.copy(color = Theme.colors.contrast)
                )
                Spacer(modifier = Modifier.weight(1f))
                val discount = specialist.service.discount * 100
                if (discount > 0) {
                    Text(
                        text = "${Resources.strings.discount} $discount%",
                        style = Theme.typography.caption.copy(color = Theme.colors.contrast),
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Theme.colors.accent300)
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = Resources.images.specialistIcon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(14.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = "${Resources.strings.specialist}/${specialist.name}",
                    style = Theme.typography.body.copy(color = Theme.colors.dark200.copy(alpha = 0.7f))
                )
            }

            Text(
                text = specialist.service.name,
                style = Theme.typography.body.copy(
                    color = Theme.colors.contrast,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
            )
            ItemLocation(
                governorate = specialist.location.governorate,
                country = specialist.location.country,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
            )
            Row(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ServifyButton(
                    onClick = { onClickBookNow(specialist.id) },
                    text = Resources.strings.bookNow,
                    buttonHeight = 30.dp,
                    modifier = Modifier.width(100.dp),
                    buttonRadius = 4.dp,
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Theme.colors.accent300)
                ) {
                    Icon(
                        painter = painterResource(id = Resources.images.phoneIcon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(24.dp)
                            .noRippleEffect {
                                onClickCall(specialist.id)
                            }
                            .padding(4.dp)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Theme.colors.accent300)
                        .padding(start = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = Resources.images.messageIcon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(24.dp)
                            .noRippleEffect {
                                onClickMessage(specialist.id)
                            }
                            .padding(4.dp)
                    )
                }
            }
        }
    }


}
