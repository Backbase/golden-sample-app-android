package com.backbase.android.foundation.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface Intent

abstract class ViewModel<I : Intent, S>(
    private val intentHandlers: IntentHandlerCollection<I, S>
) : androidx.lifecycle.ViewModel() {

    abstract val uiState: StateFlow<S>

    fun handle(intent: I) {
        val handler = intentHandlers[intent] as IntentHandler<I, S>
        val start = System.currentTimeMillis()

        try {
            println("Handling intent: $intent with ${handler::class.simpleName}")
            with(handler) { handle(intent) { newState -> updateState(newState) } }
        } finally {
            val duration = System.currentTimeMillis() - start
            println("Intent ${intent::class.simpleName} handled in ${duration}ms")
        }
    }

    protected abstract fun updateState(newState: S)
}

interface IntentHandler<I : Intent, S> {

    fun ViewModel<I, S>.handle(intent: I, onUiStateUpdated: (S) -> Unit)

    fun canHandle(intent: Intent): Boolean
}

inline fun <reified I : Intent, S> intentHandler(
    noinline block: ViewModel<I, S>.(I, (S) -> Unit) -> Unit
): IntentHandler<I, S> = object : IntentHandler<I, S> {

    override fun canHandle(intent: Intent): Boolean = intent is I

    override fun ViewModel<I, S>.handle(intent: I, onUiStateUpdated: (S) -> Unit) {
        block(intent, onUiStateUpdated)
    }
}

class IntentHandlerCollection<I : Intent, S>(
    private val handlers: List<IntentHandler<out I, S>>
) {

    operator fun get(intent: I): IntentHandler<I, S> {
        return handlers
            .find { it.canHandle(intent) } as? IntentHandler<I, S>
            ?: error("No handler for intent: $intent")
    }
}
