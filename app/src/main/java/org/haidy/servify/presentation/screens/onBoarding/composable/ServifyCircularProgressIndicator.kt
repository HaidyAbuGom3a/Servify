package org.haidy.servify.presentation.screens.onBoarding.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.haidy.servify.app.theme.Theme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServifyCircularProgressIndicator(progress: Float, pagerState: PagerState){
    Box(Modifier.padding(top = 40.dp)) {
        CircularProgressIndicator(
            progress = animateFloatAsState(targetValue = progress, label = "").value,
            modifier = Modifier.size(80.dp),
            color = Theme.colors.accent100,
            strokeWidth = 2.dp,
            backgroundColor = Theme.colors.grey300,
        )
        Box(modifier = Modifier
            .padding(15.dp)
            .size(50.dp)
            .clip(CircleShape)
            .background(Theme.colors.accent100),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = (pagerState.currentPage + 1).toString(),
                style = Theme.typography.title.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}