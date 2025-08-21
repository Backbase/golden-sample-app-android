package com.backbase.android.foundation.mvi

interface SideEffectHandler<I, S, E : Any> {

    suspend fun handleEffect(currentState: S, effect: E, throwIntent: (I) -> Unit)
}