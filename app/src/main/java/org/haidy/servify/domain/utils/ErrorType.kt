package org.haidy.servify.domain.utils

open class InternetException : Exception()
object WifiDisabledException : InternetException()
object NoInternetException : InternetException()
object NetworkNotSupportedException : InternetException()

open class AuthorizationException(val errorMessage: String? = null) : Exception()
object UnAuthorizedException : AuthorizationException()
class UserNotFoundException(message: String) : AuthorizationException(message)
class InvalidCredentialsException(message: String) : AuthorizationException(message)
