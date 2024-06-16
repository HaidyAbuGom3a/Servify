package org.haidy.servify.presentation.screens.bookingAppointment.composable

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.modifier.noRippleEffect
import java.util.Date

@Composable
fun ItemDate(onClickDate: (String) -> Unit, date: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            Resources.strings.day,
            style = Theme.typography.headline.copy(
                color = Theme.colors.contrast,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        val context = LocalContext.current
        ServifyTextField(
            text = date,
            onValueChange = {},
            hint = "dd/mm/yyyy",
            readOnly = true,
            modifier = Modifier.noRippleEffect {
                showDatePicker(
                    { date -> onClickDate(date) },
                    context
                ).show()
            },
            trailingPainter = painterResource(id = Resources.images.arrowDropDownIcon),
            onTrailingIconClick =  {
                showDatePicker(
                    { date -> onClickDate(date) },
                    context
                ).show()
            }
        )
    }
}

private fun showDatePicker(onClickDate: (String) -> Unit, context: Context): DatePickerDialog {
    val calendar = Calendar.getInstance()
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val day = calendar[Calendar.DAY_OF_MONTH]
    calendar.time = Date()
    return DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val updatedMonth = if (month+1 < 10) "0${month+1}" else month+1
            val updatedDate = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth
            onClickDate("$updatedDate/$updatedMonth/$year")
        },
        year,
        month,
        day
    )
}