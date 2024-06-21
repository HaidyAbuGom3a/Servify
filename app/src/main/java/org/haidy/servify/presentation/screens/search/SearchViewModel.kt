package org.haidy.servify.presentation.screens.search

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.SpecialistsUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val specialistsUseCase: SpecialistsUseCase) :
    BaseViewModel<SearchUiState, SearchUiEffect>(SearchUiState()), SearchInteractionListener {

    override fun onTextChanged(text: String) {
        _state.update { it.copy(searchText = text, filter = it.filter.copy(serviceName = text)) }
        val filter = state.value.filter
        tryToExecute(
            {
                specialistsUseCase.getFilteredSpecialists(
                    serviceName = filter.serviceName,
                    filter.specialistName,
                    filter.rating
                )
            },
            { specialists ->
                _state.update { it.copy(specialists = specialists) }
            },
            { }
        )
    }

    override fun onClickBackIcon() {
        sendNewEffect(SearchUiEffect.NavigateUp)
    }

    override fun onClickFilter() {
        sendNewEffect(SearchUiEffect.NavigateToFilter)
    }

    override fun onClickSearchIcon() {
        val filter = state.value.filter
        tryToExecute(
            {
                specialistsUseCase.getFilteredSpecialists(
                    serviceName = filter.serviceName,
                    filter.specialistName,
                    filter.rating
                )
            },
            { specialists ->
                _state.update { it.copy(specialists = specialists) }
            },
            { _state.update { it.copy(specialists = emptyList()) } }
        )
    }

    override fun onClickChat(specialistId: String) {
        sendNewEffect(SearchUiEffect.NavigateToChat(specialistId))
    }

    fun updateFilter(filter: SearchFilter) {
        _state.update { it.copy(filter = filter) }
    }

}