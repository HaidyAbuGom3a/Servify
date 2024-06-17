package org.haidy.servify.presentation.screens.search

interface SearchInteractionListener {
    fun onTextChanged(text: String)
    fun onClickBackIcon()
    fun onClickFilter()
    fun onClickSearchIcon()
}