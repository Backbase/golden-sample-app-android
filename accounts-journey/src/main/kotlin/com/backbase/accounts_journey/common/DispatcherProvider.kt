package com.backbase.accounts_journey.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Provides coroutine dispatcher and can be replaced for testing by implementing the interface.
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
interface DispatcherProvider {
    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun io(): CoroutineDispatcher = Dispatchers.IO
}

class DefaultDispatcherProvider : DispatcherProvider
