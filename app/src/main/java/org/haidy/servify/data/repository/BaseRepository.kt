package org.haidy.servify.data.repository

import org.haidy.servify.domain.utils.InvalidCredentialsException
import retrofit2.Response
import java.util.concurrent.CancellationException

open class BaseRepository {
    protected suspend fun <T> wrapResponse(function: suspend () -> Response<T>): T? {
        try {
            val response = function()
            return if (response.isSuccessful) {
                response.body()
            } else {
                when (response.code()) {
                    401 -> throw InvalidCredentialsException("")
                    else -> throw Exception(response.message())
                }
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    protected fun <T> wrapResponseNoCoroutine(function: () -> Response<T>): T? {
        try {
            val response = function()
            return if (response.isSuccessful) {
                response.body()
            } else {
                when (response.code()) {
                    401 -> throw InvalidCredentialsException("")
                    else -> throw Exception(response.message())
                }
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }
}