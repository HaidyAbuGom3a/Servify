package org.haidy.servify.app.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import org.haidy.servify.R

@Composable
fun headlineLarge(): TextStyle {
    return TextStyle(
        fontSize = 28.sp,
        lineHeight = 40.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display_bold)),
    )
}

@Composable
fun headline(): TextStyle {
    return TextStyle(
        fontSize = 20.sp,
        lineHeight = 32.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display_bold)),
    )
}

@Composable
fun titleLarge(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
    )
}

@Composable
fun title(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
    )
}

@Composable
fun bodyLarge(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
    )
}

@Composable
fun body(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
    )
}

@Composable
fun caption(): TextStyle {
    return TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
        fontSize = 12.sp,
        color = Theme.colors.grey300
    )
}