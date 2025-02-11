package com.bartburg.contacts_journey.view

open class DefaultReducer {

    fun reduce(currentState: DefaultState, intent: SomeIntent): DefaultState {
        return when (intent) {
            is SomeIntent.LoadData -> handleLoadData(currentState)
            is SomeIntent.SubmitData -> handleSubmitData(currentState, intent.input)
            is SomeIntent.Retry -> handleRetry(currentState)
        }
    }

    // Handle LoadData intent
    protected open fun handleLoadData(currentState: DefaultState): DefaultState {
        return currentState.copy(
            isLoading = true,
            error = null
        )
    }

    // Handle SubmitData intent
    protected open fun handleSubmitData(currentState: DefaultState, input: String): DefaultState {
        return currentState.copy(
            isLoading = false,
            data = input,
            error = null
        )
    }

    // Handle Retry intent
    protected open fun handleRetry(currentState: DefaultState): DefaultState {
        return currentState.copy(
            isLoading = true,
            error = null
        )
    }
}