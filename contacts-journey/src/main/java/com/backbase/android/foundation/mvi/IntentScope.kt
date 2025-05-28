package com.backbase.android.foundation.mvi

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * A scoped context for handling a specific intent within an intent-based architecture.
 *
 * `IntentScope` provides access to:
 * - The current intent being handled.
 * - The current UI state (as a snapshot or through update).
 * - The ability to launch one-time effects (e.g., showing a snackbar, navigation).
 * - Coroutine support for async operations during intent handling.
 *
 * Typically used inside an [IntentHandler] to encapsulate the processing logic for an intent.
 *
 * @param I The type of the intent being handled.
 * @param S The type of the UI state.
 * @param E The type of the one-time UI effect.
 * @property coroutineContext The coroutine context in which this scope runs.
 * @property intent The intent instance currently being handled.
 * @property currentUiState A function returning the current UI state at invocation time.
 * @property intentContext Internal context that manages state and effect emissions.
 */
class IntentScope<I : Any, S, E>(
    override val coroutineContext: CoroutineContext,
    val intent: I,
    private val currentUiState: () -> S,
    private val intentContext: IntentContext<S, E>
): CoroutineScope {

    /**
     * Emits a one-time effect from within the intent handler.
     *
     * This can be used for navigation, toast messages, dialogs, etc.
     *
     * @param effect The effect to emit.
     */
    suspend fun launchEffect(effect: E) = intentContext.launchEffect(effect)

    /**
     * Updates the UI state by applying the given reducer function to the current state.
     *
     * This enables functional-style state updates, ensuring immutability and traceability.
     *
     * @param reducer A function that takes the current state and returns a new state.
     */
    fun updateUiState(reducer: (S) -> S) {
        val currentState = currentUiState()
        intentContext.updateUiState(currentState, reducer)
    }

    companion object {
        /**
         * A convenience property to get a snapshot of the current UI state from the [IntentScope].
         */
        val <I : Any, S, E> IntentScope<I, S, E>.uiStateSnapshot: S
            get() = currentUiState()
    }
}


