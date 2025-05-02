package com.backbase.android.foundation.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// TODO Review if we can remove this interface
interface Intent

abstract class ViewModel<I : Intent, S>(
    initialState: S,
    private val intentHandlers: IntentHandlerCollection<I, S>
) : androidx.lifecycle.ViewModel() {

    private val _uiState = MutableStateFlow(value = initialState)
    val uiState: StateFlow<S> = _uiState

    fun handle(intent: I) {
        viewModelScope.launch {
            intentHandlers
                .findHandler(intent)
                .handleIntent(viewModel = this@ViewModel, intent)
                .collect { _uiState.value = it }
        }
    }
}

interface IntentHandler<I : Intent, S> {

    fun canHandle(intent: Intent): Boolean

    fun handleIntent(viewModel: ViewModel<I,S> , intent: I): Flow<S>
}

inline fun <reified I : Intent, S> IntentHandler(
    noinline block: ViewModel<I, S>.(I) -> Flow<S>
): IntentHandler<I, S> = object : IntentHandler<I, S> {

    override fun canHandle(intent: Intent): Boolean = intent is I

    override fun handleIntent(viewModel: ViewModel<I,S> , intent: I): Flow<S> = viewModel.block(intent)
}

class IntentHandlerCollection<I : Intent, S>(
    private val intentHandlers: List<IntentHandler<out I, S>>
) {

    constructor(vararg handlers: IntentHandler<out I, S>) : this(intentHandlers = handlers.toList())

    fun findHandler(intent: I): IntentHandler<I, S> = intentHandlers
        .find { it.canHandle(intent) } as? IntentHandler<I, S>
        ?: error("No handler for intent: $intent")
}