package com.backbase.android.foundation.mvi

interface Reducer<I, S, E> {

    fun S.reduce(intent: I, throwSideEffect: (E) -> Unit): S
}
