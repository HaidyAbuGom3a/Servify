package org.haidy.servify.presentation.screens.feedback.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.theme.Theme

@Composable
fun ServiceFeedbackItem(
    serviceName: String,
    specialistName: String,
    serviceImagePainter: Painter,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(16.dp),
                color = Theme.colors.grey100
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = serviceImagePainter,
            contentDescription = null,
            modifier = Modifier
                .height(62.dp)
                .width(85.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(start = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = serviceName,
                modifier = Modifier.padding(bottom = 4.dp),
                style = Theme.typography.title.copy(
                    color = Theme.colors.contrast,
                    fontWeight = FontWeight.Bold
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically){
                Spacer(
                    modifier = Modifier
                        .size(4.dp)
                        .clip(CircleShape)
                        .background(Theme.colors.dark300.copy(alpha = 0.7f))
                )
                Text(
                    text = specialistName,
                    style = Theme.typography.body.copy(color = Theme.colors.dark300.copy(alpha = 0.7f)),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

        }
    }
}