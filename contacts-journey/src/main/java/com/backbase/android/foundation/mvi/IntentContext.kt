package com.backbase.android.foundation.mvi

class IntentContext<S, E>(
    private val state: S,
    private val emitState: (S) -> Unit,
    private val emitEffect: suspend (E) -> Unit
) {

    suspend fun launchEffect(effect: E) {
        emitEffect(effect)
    }

    fun updateUiState(reducer: (S) -> S) {
        emitState(reducer(state))
    }
}