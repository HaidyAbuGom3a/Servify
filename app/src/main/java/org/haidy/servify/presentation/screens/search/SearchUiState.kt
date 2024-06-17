package org.haidy.servify.presentation.screens.search

import org.haidy.servify.domain.model.Specialist

data class SearchUiState(
    val searchText: String = "",
    val specialists: List<Specialist> = emptyList(),
    val filter: SearchFilter = SearchFilter()
)

data class SearchFilter(
    val serviceName: String = "",
    val specialistName: String = "",
    val rating: String = ""
)