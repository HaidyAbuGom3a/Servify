package org.haidy.servify.presentation.screens.location

import org.haidy.servify.domain.model.Specialist

interface LocationInteractionListener {
    fun onClickBackIcon()
    fun onClickLocation(specialist: Specialist)
}