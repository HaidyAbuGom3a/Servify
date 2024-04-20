package org.haidy.servify.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.modifier.noRippleEffect


@Composable
fun ServifyAppBar(
    onNavigateUp: (() -> Unit)? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    title: String = "",
    isBackIconVisible: Boolean = true,
    backIconTint: Color = Theme.colors.primary100,
    backIconBackground: Color = Theme.colors.background,
    backgroundColor: Color = Theme.colors.background,
    leading: (@Composable (() -> Unit))? = null,
    actions: (@Composable (() -> Unit))? = null
) {
    Box(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            val rotationDegree = when (LocalLayoutDirection.current) {
                LayoutDirection.Ltr -> 0f
                LayoutDirection.Rtl -> 180f
            }
            if (isBackIconVisible) {
                Box(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(backIconBackground)
                ) {
                    Icon(
                        painter = painterResource(id = Resources.images.backIcon),
                        contentDescription = "",
                        modifier = Modifier
                            .noRippleEffect { onNavigateUp?.invoke() }
                            .padding(8.dp)
                            .rotate(rotationDegree),
                        tint = backIconTint,
                    )
                }

            } else {
                leading?.invoke()
            }
            Spacer(modifier = Modifier.weight(1f))
            actions?.invoke()
        }
        Text(
            text = title,
            style = Theme.typography.headline,
            color = Theme.colors.contrast,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = false)
@Composable
private fun PreviewServifyAppBar() {
    ServifyAppBar(
        onNavigateUp = {},
    )
}