package com.backbase.android.foundation.mvi

class IntentContext<S, E>(
    private val emitState: (StateReducer<S>) -> Unit,
    private val emitEffect: suspend (E) -> Unit
) {

    suspend fun launchEffect(effect: E) {
        emitEffect(effect)
    }

    fun updateUiState(stateReducer: StateReducer<S>) {
        emitState(stateReducer)
    }
}