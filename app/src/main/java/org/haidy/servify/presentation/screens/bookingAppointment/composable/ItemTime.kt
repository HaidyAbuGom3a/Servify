package org.haidy.servify.presentation.screens.bookingAppointment.composable

import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
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

@Composable
fun ItemTime(onClickTime: (String) -> Unit, time: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            Resources.strings.time,
            style = Theme.typography.headline.copy(
                color = Theme.colors.contrast,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        val context = LocalContext.current

        val amPm = if(
            time.isNotEmpty()
            && time.substring(range = IntRange(time.length - 2, time.length - 1)) == "am"
            ) Resources.strings.am else Resources.strings.pm

        val text =  if (time.isNotEmpty()) {
            val subStr = time.substring(range = IntRange(0, time.length - 4))
            "$subStr $amPm"
        } else time

        ServifyTextField(
            text = text,
            onValueChange = {},
            hint = "00:00",
            readOnly = true,
            modifier = Modifier.noRippleEffect {
                showTimePicker(
                    { time -> onClickTime(time) },
                    context
                ).show()
            },
            trailingPainter = painterResource(id = Resources.images.arrowDropDownIcon),
            onTrailingIconClick = {
                showTimePicker(
                    { time -> onClickTime(time) },
                    context
                ).show()
            }
        )
    }
}

fun showTimePicker(onClickTime: (String) -> Unit, context: Context): TimePickerDialog {
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]
    return TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            val amPm = if (calendar.get(Calendar.AM_PM) == Calendar.AM) "Am" else "Pm"
            val updatedHour = if (hour % 12 < 10) "0${hour % 12}" else hour % 12
            val updatedMinute = if (minute < 10) "0$minute" else minute
            val formattedTime = "$updatedHour:$updatedMinute $amPm"
            onClickTime(formattedTime)
        },
        hour,
        minute,
        false
    )

}