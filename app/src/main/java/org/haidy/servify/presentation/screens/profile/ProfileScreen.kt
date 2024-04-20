package org.haidy.servify.presentation.screens.profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.domain.model.Gender
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyLoading
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.profile.composable.ItemProfileSection
import org.haidy.servify.presentation.util.EffectHandler

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val updateSuccessMsg = Resources.strings.imageUpdatedSuccessfully
    val errorMsg = Resources.strings.requestFailed
    val context = LocalContext.current
    EffectHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ProfileUiEffect.NavigateUp -> {
                navController.popBackStack()
            }

            is ProfileUiEffect.ShowUpdatedSuccessfully -> {
                Toast.makeText(context, updateSuccessMsg, Toast.LENGTH_SHORT).show()
            }

            is ProfileUiEffect.ShowError -> {
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }
    ProfileContent(state, viewModel)
}

@Composable
fun ProfileContent(state: ProfileUiState, listener: ProfileInteractionListener) {
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
                isBackIconVisible = true,
                title = Resources.strings.profile,
                backIconTint = Theme.colors.contrast,
                backIconBackground = Theme.colors.primary300,
                backgroundColor = Theme.colors.primary300
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            )
            {
                Image(
                    painter = painterResource(id = Resources.images.topScreenShape),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(Theme.colors.primary300),
                    contentScale = ContentScale.FillBounds
                )

                Box(
                    modifier = Modifier
                        .noRippleEffect {
                            launcher.launch("image/*")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    val model = state.imageUrl.ifEmpty {
                        bitmap ?: Resources.images.userImage
                    }
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = state.imageBitmap ?: model
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .size(130.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 75.dp, start = 95.dp)
                            .clip(CircleShape)
                            .background(Theme.colors.background)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(Theme.colors.grey100)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = Resources.images.editIcon),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Theme.colors.contrast),
                        )
                    }
                }

            }
        }
        val lazyColumnContentPadding =
            PaddingValues(bottom = paddingValues.calculateBottomPadding() + 24.dp)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 240.dp), contentPadding = lazyColumnContentPadding
        ) {
            item {
                ItemProfileSection(title = Resources.strings.username, value = state.username)
            }

            item {
                ItemProfileSection(title = Resources.strings.email, value = state.email)
            }

            item {
                AnimatedVisibility(visible = state.phone.isNotEmpty()) {
                    ItemProfileSection(title = Resources.strings.phoneNumber, value = state.phone)
                }
            }

            item {
                AnimatedVisibility(visible = state.address.isNotEmpty()) {
                    ItemProfileSection(title = Resources.strings.address, value = state.address)
                }
            }

            item {
                AnimatedVisibility(visible = state.gender != null) {
                    val gender = when(state.gender){
                        Gender.MALE -> Resources.strings.male
                        Gender.FEMALE -> Resources.strings.female
                        null -> ""
                    }
                    ItemProfileSection(title = Resources.strings.gender, value = gender)
                }
            }

            item {
                AnimatedVisibility(visible = state.country.isNotEmpty()) {
                    ItemProfileSection(title = Resources.strings.country, value = state.country)
                }
            }

            item {
                AnimatedVisibility(visible = state.governorate.isNotEmpty()) {
                    ItemProfileSection(
                        title = Resources.strings.governorate,
                        value = state.governorate
                    )
                }
            }

        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AnimatedVisibility(visible = state.isLoading) {
                ServifyLoading(isLoading = state.isLoading)
            }
        }

    }

}