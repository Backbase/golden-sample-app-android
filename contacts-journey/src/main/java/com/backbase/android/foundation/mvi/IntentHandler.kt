package com.backbase.android.foundation.mvi

import kotlin.reflect.KClass

/**
 * Represents a handler for a specific type of intent in an intent-based architecture.
 *
 * Each `IntentHandler` is responsible for processing a particular intent type [I],
 * potentially modifying state [S] and/or emitting effects [E] via the provided [IntentScope].
 *
 * @param I The type of intent this handler is responsible for.
 * @param S The type representing the UI state.
 * @param E The type representing one-time effects (e.g., navigation, showing a toast).
 */
interface IntentHandler<I : Any, S, E> {

    /**
     * The [KClass] of the intent type this handler handles.
     * This is used for intent dispatching and runtime type resolution.
     */
    val intentClass: KClass<I>

    /**
     * Executes the logic associated with the intent using the given [IntentScope].
     *
     * This function is suspending to allow for asynchronous operations within the handler.
     *
     * @param scope The scope providing access to the intent, state, and effect emitters.
     */
    suspend fun runIn(scope: IntentScope<I, S, E>)
}

/**
 * Creates an [IntentHandler] for the specified intent type [I] using the given block of logic.
 *
 * This inline function leverages Kotlin's reified generics to capture the intent class type,
 * allowing for a more concise and readable way to define handlers.
 *
 * @param block The logic to run when handling the intent. Executed within the [IntentScope].
 * @return A new instance of [IntentHandler] for the given intent type.
 */
inline fun <reified I : Any, S, E> IntentHandler(
    noinline block: IntentScope<I, S, E>.() -> Unit
): IntentHandler<I, S, E> = object : IntentHandler<I, S, E> {

    override val intentClass: KClass<I>
        get() = I::class

    override suspend fun runIn(scope: IntentScope<I, S, E>) = scope.block()
}
