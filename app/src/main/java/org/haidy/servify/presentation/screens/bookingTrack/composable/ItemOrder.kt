package org.haidy.servify.presentation.screens.bookingTrack.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
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
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.OrderStatus
import org.haidy.servify.domain.model.ServiceOrder
import org.haidy.servify.domain.utils.toDateString
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.composable.ServifyOutlinedButton
import org.haidy.servify.presentation.screens.bookingTrack.BookingTrackInteractionListener
import org.haidy.servify.presentation.screens.home.composable.ItemLocation
import org.haidy.servify.presentation.screens.location.composable.ItemRating
import org.haidy.servify.presentation.util.bottomBorder

@Composable
fun ItemOrder(
    order: ServiceOrder,
    listener: BookingTrackInteractionListener?= null,
    modifier: Modifier = Modifier
) {
    Surface(shadowElevation = 2.dp, shape = RoundedCornerShape(16.dp), modifier = modifier) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Theme.colors.card)
        ) {
            Text(
                text = order.timeStamp.toDateString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp)
                    .bottomBorder(
                        (1.5).dp,
                        Theme.colors.accent100
                    )
                    .padding(top = 12.dp, bottom = 8.dp),
                style = Theme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Theme.colors.contrast
                )
            )
            val bottomBorderStroke = if(listener != null) (1.5).dp else 0.dp
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp)
                    .bottomBorder(
                        bottomBorderStroke,
                        Theme.colors.accent100
                    )
                    .padding(top = 16.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(order.specialist.imageUrl),
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 8.dp, end = 24.dp)) {
                    Text(
                        text = order.specialist.name, style = Theme.typography.titleLarge,
                        color = Theme.colors.contrast
                    )
                    ItemLocation(
                        country = order.specialist.location.country,
                        governorate = order.specialist.location.governorate,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    ItemRating(
                        rating = order.specialist.rating,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            if(listener!= null){
                Row(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (order.status != OrderStatus.CANCELLED) {
                        val firstButtonText = when (order.status) {
                            OrderStatus.UPCOMING -> Resources.strings.reschedule
                            OrderStatus.COMPLETED -> Resources.strings.addRating
                            else -> ""
                        }
                        val secondButtonText = when (order.status) {
                            OrderStatus.UPCOMING -> Resources.strings.cancel
                            OrderStatus.COMPLETED -> Resources.strings.rebook
                            else -> ""
                        }
                        ServifyButton(
                            onClick = {
                                when (order.status) {
                                    OrderStatus.UPCOMING -> listener.onClickReschedule(order.specialist.id, order.id)
                                    OrderStatus.COMPLETED -> listener.onClickAddRating(order.specialist.id)
                                    else -> {}
                                }
                            },
                            text = firstButtonText,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            fontSize = 14.sp
                        )
                        ServifyOutlinedButton(
                            onClick = {
                                when (order.status) {
                                    OrderStatus.UPCOMING -> listener.onClickCancel(order.id)
                                    OrderStatus.COMPLETED -> listener.onClickReBook(order.specialist.id)
                                    else -> {}
                                }
                            },
                            text = secondButtonText,
                            modifier = Modifier.weight(1f),
                            fontSize = 14.sp
                        )
                    } else {
                        ServifyButton(
                            onClick = { listener.onClickAddRating(specialistId = order.specialist.id) },
                            text = Resources.strings.addRating,
                            modifier = Modifier.padding(end = 8.dp),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}