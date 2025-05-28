package com.backbase.android.foundation.mvi

/**
 * A context holder for managing state and effects in an intent-based architecture.
 *
 * This class is designed to encapsulate state and effect emission logic, typically used within
 * a ViewModel or similar component to ensure a clear separation of concerns between business logic
 * and UI updates.
 *
 * @param S The type representing the UI state.
 * @param E The type representing one-time effects (e.g., navigation, showing a toast).
 * @property emitState A function used to update the current UI state.
 * @property emitEffect A suspending function used to emit one-time UI effects.
 */
class IntentContext<S, E>(
    private val emitState: (S) -> Unit,
    private val emitEffect: suspend (E) -> Unit
) {

    /**
     * Emits a one-time effect, such as navigation or showing a toast.
     *
     * This function is `suspend` because the effect handler might involve asynchronous operations.
     *
     * @param effect The effect to be emitted.
     */
    suspend fun launchEffect(effect: E) {
        emitEffect(effect)
    }

    /**
     * Updates the current UI state by applying a reducer function.
     *
     * This approach encourages functional state management, where a new state is derived
     * from the current one via a pure function.
     *
     * @param actualState The current state before applying the reducer.
     * @param reducer A function that receives the current state and returns a new modified state.
     */
    fun updateUiState(actualState: S, reducer: (S) -> S) {
        emitState(reducer(actualState))
    }
}