package com.backbase.android.foundation.mvi

/**
 * Given the currentState, return a new state based on the previous one.
 */
fun interface StateReducer<S> {

    fun reduce(currentState: S): S
}
