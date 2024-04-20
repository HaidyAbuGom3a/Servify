package org.haidy.servify.presentation.screens.forgotPassword.verifyCode.composable

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.app.theme.Theme

@Composable
fun CodeTextField(
    text: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
        .width(48.dp)
        .height(56.dp),
    focusRequester: FocusRequester = FocusRequester()
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        ServifyTextField(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester),
            text = text,
            cursorColor = Color.Transparent,
            onValueChange = onValueChanged,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.NumberPassword
            )
        )
        Text(
            text = text,
            style = Theme.typography.headline,
            color = Theme.colors.dark100,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}