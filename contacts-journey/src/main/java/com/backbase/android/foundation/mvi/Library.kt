package com.backbase.android.foundation.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.collections.find

// TODO Review if we can remove this interface
interface Intent

abstract class ViewModel<I : Intent, S>(
    initialState: S,
    private val intentHandlers: List<IntentHandler<out I, S>>
) : androidx.lifecycle.ViewModel() {

    constructor(
        initialState: S,
        vararg handlers: IntentHandler<out I, S>
    ) : this(initialState, intentHandlers = handlers.toList())

    private val _uiState = MutableStateFlow(value = initialState)
    val uiState: StateFlow<S> = _uiState

    fun handle(intent: I) {
        viewModelScope.launch {
            findHandler(intent)
                .handleIntent(viewModel = this@ViewModel, intent, uiState.value)
                .collect { _uiState.value = it.reduce(_uiState.value) }
        }
    }

    private fun findHandler(intent: I): IntentHandler<I, S> = intentHandlers
        .find { it.canHandle(intent) } as? IntentHandler<I, S>
        ?: error("No handler for intent: $intent")
}

interface IntentHandler<I : Intent, S> {

    fun canHandle(intent: Intent): Boolean

    fun handleIntent(viewModel: androidx.lifecycle.ViewModel, intent: I, uiState: S): Flow<StateReducer<S>>
}

fun interface StateReducer<S> {

    fun reduce(currentState: S): S
}

inline fun <reified I : Intent, S> IntentHandler(
    noinline block: androidx.lifecycle.ViewModel.(I, S) -> Flow<StateReducer<S>>
): IntentHandler<I, S> = object : IntentHandler<I, S> {

    override fun canHandle(intent: Intent): Boolean =
        intent is I

    override fun handleIntent(viewModel: androidx.lifecycle.ViewModel, intent: I, uiState: S): Flow<StateReducer<S>> =
        viewModel.block(intent, uiState)
}
