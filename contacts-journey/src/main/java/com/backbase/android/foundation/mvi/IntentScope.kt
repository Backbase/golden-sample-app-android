package com.backbase.android.foundation.mvi

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

class IntentScope<I : Any, S, E>(
    override val coroutineContext: CoroutineContext,
    val intent: I,
    val uiStateSnapshot: S,
    private val context: IntentContext<S, E>
): CoroutineScope {

    suspend fun launchEffect(effect: E) = context.launchEffect(effect)

    fun updateUiState(reducer: (S) -> S) = context.updateUiState(reducer)
}
