package org.haidy.servify.presentation.screens.onBoarding

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.app.resources.DrawableResources
import org.haidy.servify.app.resources.strings.IStringResources
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    @JvmSuppressWildcards private val userManagement: UserUseCase,
    private val stringRes: IStringResources,
    private val drawableResources: DrawableResources
) : BaseViewModel<OnBoardingUiState, OnBoardingUiEffect>(OnBoardingUiState()), OnBoardingInteractionListener {
    init {
        _state.update { it.copy(pages = getPages()) }
    }

    private fun getPages(): List<OnBoardingPage> {
        return listOf(
            OnBoardingPage(
                stringRes.hello,
                stringRes.helloPageDescription,
                drawableResources.helloImage
            ),
            OnBoardingPage(
                stringRes.arrangement,
                stringRes.arrangementPageDescription,
                drawableResources.arrangementImage
            ),
            OnBoardingPage(
                stringRes.solving,
                stringRes.solvingPageDescription,
                drawableResources.solvingImage
            ),
        )
    }

    override fun onClickGetStarted() {
        tryToExecute(
            {userManagement.saveIfFirstTimeUseApp(false)},
            { sendNewEffect(OnBoardingUiEffect.NavigateToLogin) },
            {}
        )
    }
}