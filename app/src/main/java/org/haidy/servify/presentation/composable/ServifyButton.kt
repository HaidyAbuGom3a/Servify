package org.haidy.servify.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.haidy.servify.R
import org.haidy.servify.app.theme.Theme

@Composable
fun ServifyButton(
    onClick: () -> Unit,
    text: String,
    iconPainter: Painter? = null,
    modifier: Modifier = Modifier,
    containerColor: Color = Theme.colors.accent100,
    contentColor: Color = Color.White,
    enabled: Boolean = true,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(),
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 16.sp,
    isLoading: Boolean = false
) {
    Button(
        { onClick() },
        modifier = modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = Theme.colors.grey100,
            disabledContentColor = Theme.colors.dark200,
        ),
        shape = RoundedCornerShape(Theme.radius.large),
        enabled = enabled,
        elevation = elevation
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec
                .RawRes(R.raw.loading)
        )
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = isLoading,
            restartOnPlay = false
        )

        AnimatedVisibility(visible = iconPainter != null) {
            Image(painter = iconPainter!!, contentDescription = null)
        }

        Spacer(modifier = Modifier.weight(1f))
        AnimatedVisibility(visible = isLoading) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text,
            style = Theme.typography.titleLarge,
            fontWeight = fontWeight,
            fontSize = fontSize
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview(showSystemUi = false)
private fun PreviewServifyButton() {
    ServifyButton(onClick = { }, "Get Started")
}