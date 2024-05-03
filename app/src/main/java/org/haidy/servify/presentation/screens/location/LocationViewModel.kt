package org.haidy.servify.presentation.screens.location

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.domain.usecase.SpecialistsUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val specialistsUseCase: SpecialistsUseCase) :
    BaseViewModel<LocationUiState, LocationUiEffect>(LocationUiState()),
    LocationInteractionListener {

    init {
        getNearestSpecialists()
    }

    private fun getNearestSpecialists() {
        tryToExecute(
            { specialistsUseCase.getNearestSpecialists() },
            { nearestSpecialists ->
                _state.update {
                    it.copy(
                        nearestSpecialists = nearestSpecialists,
                        currentSpecialist = nearestSpecialists.first()
                    )
                }

            },
            {}
        )
    }

    override fun onClickBackIcon() {
        sendNewEffect(LocationUiEffect.NavigateBack)
    }

    override fun onClickLocation(specialist: Specialist) {
        _state.update { it.copy(currentSpecialist = specialist) }
    }
}