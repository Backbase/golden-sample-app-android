package com.backbase.cards_journey.mvi

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, Effect : UiEffect, Event : UiEvent> : ViewModel() {

    protected val currentState: State
        get() = _state.value

    @Suppress("VariableNaming")
    @VisibleForTesting
    val _state = MutableStateFlow(createInitialState())
    val state: StateFlow<State> = _state.asStateFlow()

    @Suppress("VariableNaming")
    protected val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    private val event: SharedFlow<Event> = _event.asSharedFlow()

    @Suppress("VariableNaming")
    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            event.collect { handleEvent(it) }
        }
    }

    fun sendEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reduce: State.() -> State) {
        _state.value = currentState.reduce()
    }

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch { _effect.send(effect) }
    }

    protected fun launch(block: suspend () -> Unit) = viewModelScope.launch { block() }

    abstract fun handleEvent(event: Event)

    protected abstract fun createInitialState(): State
}
