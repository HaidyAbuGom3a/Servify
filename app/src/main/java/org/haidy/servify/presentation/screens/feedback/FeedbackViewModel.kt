package org.haidy.servify.presentation.screens.feedback

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.servify.domain.usecase.SpecialistsUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val specialistsUseCase: SpecialistsUseCase
) :
    BaseViewModel<FeedbackUiState, FeedbackUiEffect>(FeedbackUiState()),
    FeedbackInteractionListener {

    private val args: FeedbackArgs = FeedbackArgs(savedStateHandle)

    init {
        getSpecialistData()
    }

    private fun getSpecialistData() {
        tryToExecute(
            { specialistsUseCase.getSpecialist(args.id) },
            { specialist ->
                _state.update {
                    it.copy(
                        specialistName = specialist.name,
                        serviceName = specialist.service.name,
                        specialistUrl = specialist.imageUrl
                    )
                }
            },
            {}
        )
    }

    override fun onClickSubmitFeedback() {
        _state.update {
            it.copy(rating = 1.0, feedback = "")
        }
        viewModelScope.launch {
            sendNewEffect(FeedbackUiEffect.ShowSuccessMessage)
            delay(2000)
            sendNewEffect(FeedbackUiEffect.NavigateToHome)
        }
    }

    override fun onClickBackIcon() {
        sendNewEffect(FeedbackUiEffect.NavigateUp)
    }

    override fun onClickStar(index: Int) {
        _state.update { it.copy(rating = (index + 1).toDouble()) }
    }

    override fun onFeedbackChanged(feedback: String) {
        _state.update { it.copy(feedback = feedback) }
    }
}