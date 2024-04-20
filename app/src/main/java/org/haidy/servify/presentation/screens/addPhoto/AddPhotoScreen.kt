package org.haidy.servify.presentation.screens.addPhoto

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.LocalNavController
import org.haidy.servify.app.navigation.ServifyDestination
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.signup.SignUpFormUiState
import org.haidy.servify.presentation.screens.signup.SignupViewModel
import org.haidy.servify.presentation.screens.verifyEmail.navigateToVerifyEmail
import org.haidy.servify.presentation.util.EffectHandler

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun AddPhotoScreen(viewModel: AddPhotoViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val selectImageMsg = Resources.strings.pleaseSelectAnImage
    val requestFailedMsg = Resources.strings.requestFailed
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            is AddPhotoUiEffect.NavigateToVerifyEmail -> navController.navigateToVerifyEmail(effect.email)
            AddPhotoUiEffect.NavigateUp -> navController.popBackStack()
            AddPhotoUiEffect.ShowPickImageError -> Toast.makeText(
                context,
                selectImageMsg,
                Toast.LENGTH_SHORT
            ).show()

            AddPhotoUiEffect.ShowRequestFailed -> Toast.makeText(
                context,
                requestFailedMsg,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    val navHostController = LocalNavController.current
    val backStackEntry = remember {
        navHostController.getBackStackEntry(ServifyDestination.SIGNUP)
    }
    val signUpViewModel: SignupViewModel = hiltViewModel(backStackEntry)
    val signUpForm = signUpViewModel.state.value.signUpForm
    AddPhotoContent(viewModel, signUpForm)
}

@Composable
fun AddPhotoContent(listener: AddPhotoInteractionListener, signUpForm: SignUpFormUiState) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                bitmap = if (Build.VERSION.SDK_INT < 28) {
                    @Suppress("DEPRECATION")
                    MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
                listener.onImagePicked(bitmap)
            }
        }

    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Theme.colors.background)
                .padding(
                    top = paddingValues.calculateTopPadding() + 32.dp,
                    bottom = 32.dp,
                    start = 24.dp,
                    end = 24.dp
                )
                .fillMaxSize(),
        ) {
            Text(
                text = Resources.strings.addPhoto,
                style = Theme.typography.headlineLarge,
                color = Theme.colors.dark100,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp),
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .noRippleEffect {
                        launcher.launch("image/*")
                    }
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                val model = bitmap ?: Resources.images.userImage
                Image(
                    painter = rememberAsyncImagePainter(
                        model = model
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(top = 145.dp, start = 150.dp)
                        .clip(CircleShape)
                        .background(Theme.colors.error300)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = Resources.images.plusIcon),
                        contentDescription = null
                    )
                }
            }

            Text(
                text = Resources.strings.addYourPhoto,
                style = Theme.typography.headline,
                color = Theme.colors.dark100,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = Resources.strings.addPhotoDescription,
                style = Theme.typography.body,
                color = Theme.colors.dark200,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 56.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp, start = 16.dp, end = 16.dp)
            ) {

                ServifyButton(
                    onClick = {
                        listener.onClickContinue(signUpForm)
                    },
                    text = Resources.strings.continueSteps,
                    containerColor = Theme.colors.accent100,
                    contentColor = Theme.colors.grey50,
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.width(150.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                ServifyButton(
                    onClick = { listener.onClickSkip(signUpForm) },
                    text = Resources.strings.skip,
                    containerColor = Theme.colors.grey50,
                    contentColor = Theme.colors.dark200,
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.width(150.dp)
                )
            }

        }
    }
}