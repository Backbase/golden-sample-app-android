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
 * A base [androidx.lifecycle.ViewModel] implementation for intent-based MVI architecture.
 *
 * This class wires together:
 * - A reactive [StateFlow] of UI state [S].
 * - A [SharedFlow] of one-time UI effects [E].
 * - A set of [IntentHandler]s responsible for processing intents [I].
 *
 * Subclasses should provide a list of [IntentHandler]s and an initial UI state.
 *
 * @param I The type of intents that can be handled.
 * @param S The type of the UI state.
 * @param E The type of one-time UI effects.
 * @property initialState The initial value of the UI state.
 * @property intentHandlers A list of handlers, each capable of processing a specific intent type.
 */
abstract class ViewModel<I : Any, S, E>(
    initialState: S,
    intentHandlers: List<IntentHandler<out I, S, E>>
) : androidx.lifecycle.ViewModel() {

    /**
     * Internal mapping from intent class to its corresponding handler.
     */
    private val handlerMap: Map<KClass<out I>, IntentHandler<out I, S, E>> =
        intentHandlers.associateBy { it.intentClass }

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

    /**
     * Dispatches the given [intent] to its corresponding [IntentHandler], if one is registered.
     *
     * The handler runs within a coroutine launched in [viewModelScope] and has access to the current
     * UI state and the ability to emit effects or update the state.
     *
     * @param intent The intent to be handled.
     * @throws IllegalStateException if no handler is registered for the given intent type.
     */
    fun handle(intent: I) {
        viewModelScope.launch {
            val intentHandler = handlerMap[intent::class] as? IntentHandler<I, S, E>
                ?: error("No handler founded for ${intent::class}")

            val intentScope = IntentScope(
                coroutineContext = viewModelScope.coroutineContext,
                intent = intent,
                currentUiState = { _uiState.value },
                intentContext = IntentContext<S, E>(
                    emitState = { _uiState.value = it },
                    emitEffect = { effect -> _effects.emit(effect) }
                )
            )

            intentHandler.runIn(intentScope)
        }
    }
}
