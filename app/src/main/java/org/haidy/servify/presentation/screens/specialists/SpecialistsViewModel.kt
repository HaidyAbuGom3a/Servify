package org.haidy.servify.presentation.screens.specialists

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.SpecialistsUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialistsViewModel @Inject constructor(
    private val specialistsUseCase: SpecialistsUseCase
) :
    BaseViewModel<SpecialistsUiState, SpecialistsUiEffect>(SpecialistsUiState()), SpecialistsInteractionListener {

        init {
            getBestSpecialists()
        }

    private fun getBestSpecialists(){
        tryToExecute(
            { specialistsUseCase.getBestSpecialists() },
            { specialists -> _state.update { it.copy(bestSpecialists = specialists) }},
            {}
        )
    }

    override fun onClickBookNow(specId: String) {
        sendNewEffect(SpecialistsUiEffect.NavigateToBookAppointment(specId))
    }

    override fun onClickBackIcon() {
        sendNewEffect(SpecialistsUiEffect.NavigateUp)
    }
}