package org.haidy.servify.presentation.screens.bookingAppointment.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyTextField

@Composable
fun ItemPrice(onPriceChange: (String) -> Unit, price: String, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(
            Resources.strings.thePrice,
            style = Theme.typography.headline.copy(
                color = Theme.colors.contrast,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        ServifyTextField(text = price, onValueChange = { onPriceChange(it) }, hint = Resources.strings.price)
    }
}