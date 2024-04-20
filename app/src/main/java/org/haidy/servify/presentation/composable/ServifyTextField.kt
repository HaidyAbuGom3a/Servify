package org.haidy.servify.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.app.theme.Theme

@Composable
fun ServifyTextField(
    text: String,
    onValueChange: (String) -> Unit,
    hintColor: Color = Theme.colors.dark300.copy(alpha = 0.7f),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    hint: String = "",
    trailingPainter: Painter? = null,
    leadingPainter: Painter? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    radius: Dp = Theme.radius.large,
    errorMessage: String = "",
    isError: Boolean = errorMessage.isNotEmpty(),
    onTrailingIconClick: () -> Unit = {},
    onLeadingIconClick: () -> Unit = {},
    isSingleLine: Boolean = true,
    showPassword: Boolean = false,
    readOnly: Boolean = false,
    trailingIconEnabled: Boolean = onTrailingIconClick != {},
    iconTint: Color? = null,
    cursorColor: Color = Theme.colors.accent100,
    outlinedTextFieldDefaults: TextFieldColors = outlinedTextFieldColorDefaults(cursorColor)

) {
    Column(
        modifier = modifier.background(Color.Transparent),
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp, max = 160.dp),
            value = text,
            placeholder = {
                Text(
                    hint,
                    style = Theme.typography.bodyLarge.copy(fontSize = 16.sp),
                    color = hintColor,
                )
            },
            onValueChange = onValueChange,
            shape = RoundedCornerShape(radius),
            textStyle = Theme.typography.bodyLarge.copy(color = Theme.colors.dark200, fontSize = 16.sp),
            singleLine = isSingleLine,
            keyboardOptions = keyboardOptions,
            visualTransformation = servifyVisualTransformation(keyboardType, showPassword),
            isError = isError,
            trailingIcon = {
                trailingPainter?.let {
                    IconButton(
                        onClick = onTrailingIconClick,
                        enabled = trailingIconEnabled,
                    ) {
                        Icon(
                            painter = trailingPainter,
                            contentDescription = "trailing icon",
                            tint = iconTint ?: Theme.colors.dark300.copy(alpha = 0.7f)
                        )
                    }
                }
            },
            leadingIcon = if (leadingPainter != null) {
                {
                    Icon(
                        painter = leadingPainter,
                        contentDescription = "leading icon",
                        tint = iconTint ?: Theme.colors.dark300.copy(alpha = 0.7f),
                        modifier = Modifier.size(20.dp).noRippleEffect(onLeadingIconClick)
                    )
                }
            } else null,
            colors = outlinedTextFieldDefaults,
            readOnly = readOnly,
        )
        AnimatedVisibility(isError) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(top = 8.dp),
                style = Theme.typography.caption.copy(fontSize = 16.sp),
                color = Theme.colors.error100.copy(alpha = 0.8f)
            )
        }
    }

}

@Composable
fun outlinedTextFieldColorDefaults(cursorColor: Color) = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = Theme.colors.grey200,
    unfocusedContainerColor = Theme.colors.grey200,
    errorCursorColor = Theme.colors.error100.copy(alpha = 0.8f),
    cursorColor = cursorColor,
    focusedBorderColor = Theme.colors.grey100,
    unfocusedBorderColor = Theme.colors.grey200,
    errorBorderColor = Theme.colors.error100.copy(alpha = 0.8f),
)

@Composable
private fun servifyVisualTransformation(
    keyboardType: KeyboardType,
    showPassword: Boolean
): VisualTransformation {
    return if (showPassword || keyboardType != KeyboardType.Password && keyboardType != KeyboardType.NumberPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
}

@Composable
@Preview(showSystemUi = false)
private fun PreviewServifyTextField() {
    ServifyTextField(text = "Haidy El Sayed", hint = "Placeholder", onValueChange = {})
}