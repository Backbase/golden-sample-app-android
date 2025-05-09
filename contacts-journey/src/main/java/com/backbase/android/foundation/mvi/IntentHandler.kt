package com.backbase.android.foundation.mvi

import kotlin.reflect.KClass

/**
 * Handler for intents, in MVI this class is also know as `middleware`.
 *
 * `intentClass` is used to identify the type of the intent that will be handled by this handler.
 * `handleIntent` is the function that will be called when the intent is received.
 */
interface IntentHandler<I : Any, S, E> {

    val intentClass: KClass<I>

    fun handleIntent(
        viewModel: ViewModel<I, S, E>,
        intent: I,
        context: IntentContext<S, E>
    )
}

/**
 * Helper function to create an [IntentHandler].
 *
 * It reduces the amount of boilerplate code needed to create an [IntentHandler] by implementing
 * the [IntentHandler.intentClass] function.
 */
inline fun <reified I : Any, S, E> IntentHandler(
    noinline block: IntentScope<I, S, E>.() -> Unit
): IntentHandler<I, S, E> = object : IntentHandler<I, S, E> {

    override val intentClass: KClass<I>
        get() = I::class

    override fun handleIntent(
        viewModel: ViewModel<I, S, E>,
        intent: I,
        context: IntentContext<S, E>
    ) {
        val scope = IntentScope(viewModel, intent, context)
        scope.block()
    }
}
