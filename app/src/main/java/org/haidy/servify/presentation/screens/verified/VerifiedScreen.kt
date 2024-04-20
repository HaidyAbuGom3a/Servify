package org.haidy.servify.presentation.screens.verified

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.screens.login.navigateToLogin
import org.haidy.servify.presentation.util.EffectHandler

@Composable
fun VerifiedScreen(viewModel: VerifiedViewModel = hiltViewModel()) {
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            VerifiedUiEffect.NavigateToHome -> navController.navigateToLogin(true)
        }
    }
    VerifiedContent(viewModel)
}

@Composable
fun VerifiedContent(listener: VerifiedInteractionListener) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 130.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = Resources.images.verifiedImage),
            contentDescription = null,
            Modifier.size(280.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = Resources.strings.verified,
            style = Theme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            color = Theme.colors.dark100,
        )
        Text(
            text = Resources.strings.yourAccountIsVerified,
            style = Theme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, bottom = 32.dp),
            color = Theme.colors.dark200,
            textAlign = TextAlign.Center
        )

        ServifyButton(
            onClick = { listener.onClickExplore() },
            text = Resources.strings.explore,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}