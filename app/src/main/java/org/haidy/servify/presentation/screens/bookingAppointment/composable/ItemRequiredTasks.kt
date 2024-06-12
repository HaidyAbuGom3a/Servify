package org.haidy.servify.presentation.screens.bookingAppointment.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme

@Composable
fun ItemRequiredTasks(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    text: String,
) {
    Column(modifier = modifier) {
        Text(
            text = Resources.strings.requiredTasks,
            style = Theme.typography.title,
            color = Theme.colors.contrast
        )
        OutlinedTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(148.dp),
            shape = RoundedCornerShape(8.dp),
            label = {
                Text(
                    text = Resources.strings.writeHere,
                    style = Theme.typography.bodyLarge.copy(fontSize = 16.sp),
                    color = Theme.colors.dark300.copy(alpha = 0.7f)
                )
            }
        )
    }
}