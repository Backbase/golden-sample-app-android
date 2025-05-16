package com.backbase.android.foundation.mvi

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

class IntentScope<I : Any, S, E>(
    override val coroutineContext: CoroutineContext,
    val intent: I,
    private val currentUiState: () -> S,
    private val intentContext: IntentContext<S, E>
): CoroutineScope {

    suspend fun launchEffect(effect: E) = intentContext.launchEffect(effect)

    fun updateUiState(reducer: (S) -> S) {
        val currentState = currentUiState()
        intentContext.updateUiState(currentState, reducer)
    }

    companion object {
        val <I : Any, S, E> IntentScope<I, S, E>.uiStateSnapshot: S
            get() = currentUiState()
    }
}


