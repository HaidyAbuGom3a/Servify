package org.haidy.servify.presentation.screens.search

sealed class SearchUiEffect {
    object NavigateUp: SearchUiEffect()
    object NavigateToFilter: SearchUiEffect()
    data class NavigateToChat(val specialistId: String): SearchUiEffect()
}