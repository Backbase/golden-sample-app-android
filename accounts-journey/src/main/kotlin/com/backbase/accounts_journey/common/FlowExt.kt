package com.backbase.accounts_journey.common

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("TooGenericExceptionThrown")
fun <T> StateFlow<T>.mutable() = this as? MutableStateFlow<T>
    ?: throw Error("This StateFlow is not mutable")

@Suppress("TooGenericExceptionThrown")
fun <T> SharedFlow<T>.mutable() = this as? MutableSharedFlow<T>
    ?: throw Error("This SharedFlow is not mutable")