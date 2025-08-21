package com.backbase.android.foundation.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class ViewModel<I : Any, S, E : Any>(
    initialState: S,
    private val reducer: Reducer<I, S, E>,
    private val sideEffectHandler: SideEffectHandler<I, S, E>,
) : androidx.lifecycle.ViewModel() {

    /**
     * The reactive stream representing the current UI state.
     */
    private val _uiState = MutableStateFlow(value = initialState)

    /**
     * Public [StateFlow] exposing the current UI state for observation (e.g., in Compose).
     */
    val uiState: StateFlow<S> = _uiState

    /**
     * Internal flow for emitting one-time effects.
     */
    private val _effects = MutableSharedFlow<E>(replay = 0, extraBufferCapacity = 1)

    /**
     * Public [SharedFlow] exposing emitted effects for one-time handling in the UI.
     */
    val effects: SharedFlow<E> = _effects.asSharedFlow()

    fun handle(intent: I) {
        with(reducer) {
            _uiState.value = uiState.value.reduce(intent = intent) { sideEffect ->
                viewModelScope.launch {
                    sideEffectHandler.handleEffect(_uiState.value, sideEffect) { newIntent -> handle(newIntent) }
                }
            }
        }
    }
}
