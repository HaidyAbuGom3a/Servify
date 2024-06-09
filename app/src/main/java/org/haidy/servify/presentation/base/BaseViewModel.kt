package org.haidy.servify.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import org.haidy.servify.domain.utils.AuthorizationException
import org.haidy.servify.domain.utils.InternetException

open class BaseViewModel<S, E>(state: S) : ViewModel(){

    protected val _state = MutableStateFlow(state)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<E?>()
    val effect = _effect.asSharedFlow().throttleFirst(500).mapNotNull { it }

    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (ErrorState) -> Unit,
        inScope: CoroutineScope = viewModelScope
    ): Job {
        return runWithErrorCheck(onError, inScope) {
            val result = function()
            onSuccess(result)
        }
    }

    protected fun <T> tryToCollect(
        function: suspend () -> Flow<T>,
        onNewValue: (T) -> Unit,
        onError: (Exception) -> Unit,
        inScope: CoroutineScope = viewModelScope,
    ): Job {
        return inScope.launch(Dispatchers.IO) {
            try {
                function().distinctUntilChanged().collectLatest {
                    onNewValue(it)
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }


    private fun runWithErrorCheck(
        onError: (ErrorState) -> Unit,
        inScope: CoroutineScope = viewModelScope,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        function: suspend () -> Unit
    ): Job {
        return inScope.launch(dispatcher) {
            try {
                function()
            } catch (exception: InternetException) {
                handelInternetException(exception, onError)
            } catch (exception: AuthorizationException) {
                handelAuthorizationException(exception, onError)
            }
            catch (exception: Exception) {
                onError(ErrorState.RequestFailed)
            }
        }
    }

    protected fun sendNewEffect(newEffect: E) {
        viewModelScope.launch(Dispatchers.IO) {
            _effect.emit(newEffect)
        }
    }

    private fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
        require(periodMillis > 0)
        return flow {
            var lastTime = 0L
            collect { value ->
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastTime >= periodMillis) {
                    lastTime = currentTime
                    emit(value)
                }
            }
        }
    }
}