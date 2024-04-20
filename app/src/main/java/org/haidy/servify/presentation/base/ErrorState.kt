package org.haidy.servify.presentation.base

import org.haidy.servify.domain.utils.AuthorizationException
import org.haidy.servify.domain.utils.InternetException
import org.haidy.servify.domain.utils.InvalidCredentialsException
import org.haidy.servify.domain.utils.NetworkNotSupportedException
import org.haidy.servify.domain.utils.NoInternetException
import org.haidy.servify.domain.utils.UnAuthorizedException
import org.haidy.servify.domain.utils.UserNotFoundException
import org.haidy.servify.domain.utils.WifiDisabledException


sealed interface ErrorState {
    // region Authorization
    object UnAuthorized : ErrorState
    object InvalidCredentials: ErrorState
    data class EmailAlreadyExist(val message: String) : ErrorState
    data class PhoneAlreadyExist(val message: String) : ErrorState
    data class UserNotFound(val message: String) : ErrorState
    // endregion

    // region Internet
    object WifiDisabled : ErrorState
    object NoInternet : ErrorState
    object NetworkNotSupported : ErrorState
    object RequestFailed : ErrorState
    // endregion
}


fun handelInternetException(
    exception: InternetException,
    onError: (error: ErrorState) -> Unit,
) {
    when (exception) {
        is NetworkNotSupportedException -> onError(ErrorState.NetworkNotSupported)
        is NoInternetException -> onError(ErrorState.NoInternet)
        is WifiDisabledException -> onError(ErrorState.WifiDisabled)
    }
}

fun handelAuthorizationException(
    exception: AuthorizationException,
    onError: (error: ErrorState) -> Unit,
) {
    when (exception) {
        is UnAuthorizedException -> onError(ErrorState.UnAuthorized)
        is InvalidCredentialsException -> onError(ErrorState.InvalidCredentials)
        is UserNotFoundException -> onError(ErrorState.UserNotFound(exception.errorMessage ?: ""))
    }
}