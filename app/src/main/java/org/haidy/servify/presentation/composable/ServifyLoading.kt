package org.haidy.servify.presentation.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.haidy.servify.R
import org.haidy.servify.app.theme.Theme

@Composable
fun ServifyLoading(isLoading: Boolean) {
    Dialog(onDismissRequest = { }) {
        Card(
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            colors = CardDefaults.cardColors(Theme.colors.card)
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
            Spacer(modifier = Modifier.weight(1f))
            LottieAnimation(
                composition = composition,
                progress = { progress },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
@Preview
private fun PreviewLoading(){
    ServifyLoading(true)
}