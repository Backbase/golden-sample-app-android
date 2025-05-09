package com.backbase.android.foundation.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * Base class for view models that support the MVI architecture.
 */
abstract class ViewModel<I : Any, S, E>(
    initialState: S,
    intentHandlers: List<IntentHandler<out I, S, E>>
) : androidx.lifecycle.ViewModel() {

    private val handlerMap: Map<KClass<out I>, IntentHandler<out I, S, E>> =
        intentHandlers.associateBy { it.intentClass }

    private val _uiState = MutableStateFlow(value = initialState)
    val uiState: StateFlow<S> = _uiState

    private val _effects = MutableSharedFlow<E>(replay = 0, extraBufferCapacity = 1)
    val effects: SharedFlow<E> = _effects.asSharedFlow()

    fun handle(intent: I) {
        viewModelScope.launch {
            val handler = handlerMap[intent::class] as? IntentHandler<I, S, E>
                ?: error("No handler for ${intent::class}")

            handler.handleIntent(
                viewModel = this@ViewModel,
                intent = intent,
                emitState = { _uiState.value = it.reduce(_uiState.value) },
                emitEffect = { _effects.emit(it) }
            )
        }
    }
}

/**
 * Returns a snapshot of the current UI state.
 */
val <I : Any, S, E> ViewModel<I, S, E>.uiStateSnapshot: S get() = uiState.value
