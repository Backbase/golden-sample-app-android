package com.backbase.android.foundation.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

class IntentScope<I : Any, S, E>(
    private val viewModel: ViewModel<I, S, E>,
    val intent: I,
    private val context: IntentContext<S, E>
) {

    val uiStateSnapshot: S get() = viewModel.uiState.value
    val coroutineScope: CoroutineScope get() = viewModel.viewModelScope

    suspend fun launchEffect(effect: E) = context.launchEffect(effect)

    fun updateUiState(stateReducer: StateReducer<S>) = context.updateUiState(stateReducer)
}
