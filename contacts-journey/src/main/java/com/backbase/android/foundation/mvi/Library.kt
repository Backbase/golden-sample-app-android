package com.backbase.android.foundation.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

// TODO Review if we can remove this interface
interface Intent

abstract class ViewModel<I : Intent, S, E>(
    initialState: S,
    intentHandlers: List<IntentHandler<out I, S, E>>
) : androidx.lifecycle.ViewModel() {

    private val handlerMap: Map<KClass<out I>, IntentHandler<out I, S, E>> = intentHandlers.associateBy { it.intentClass }

    private val _uiState = MutableStateFlow(value = initialState)
    val uiState: StateFlow<S> = _uiState

    private val _effects = MutableSharedFlow<E>(replay = 0, extraBufferCapacity = 1)
    val effects: SharedFlow<E> = _effects.asSharedFlow()

    fun handle(intent: I) {
        viewModelScope.launch {
            val handler = handlerMap[intent::class] as? IntentHandler<I, S, E> ?: error("No handler for ${intent::class}")

            handler.handleIntent(
                viewModel = this@ViewModel,
                intent = intent,
                emitState = { _uiState.value = it.reduce(_uiState.value) },
                emitEffect = { _effects.emit(it) }
            )
        }
    }
}

val <I : Intent, S, E> ViewModel<I, S, E>.uiStateSnapshot: S get() = uiState.value

interface IntentHandler<I : Intent, S, E> {

    val intentClass: KClass<I>

    fun handleIntent(
        viewModel: ViewModel<I, S, E>,
        intent: I,
        emitState: (StateReducer<S>) -> Unit,
        emitEffect: suspend (E) -> Unit
    )
}

fun interface StateReducer<S> {

    fun reduce(currentState: S): S
}

inline fun <reified I : Intent, S, E> IntentHandler(
    noinline block: ViewModel<I, S, E>.(I, (StateReducer<S>) -> Unit, suspend (E) -> Unit) -> Unit
): IntentHandler<I, S, E> = object : IntentHandler<I, S, E> {

    override val intentClass: KClass<I>
        get() = I::class

    override fun handleIntent(
        viewModel: ViewModel<I, S, E>,
        intent: I,
        emitState: (StateReducer<S>) -> Unit,
        emitEffect: suspend (E) -> Unit
    ) {
        viewModel.block(intent, emitState, emitEffect)
    }
}
