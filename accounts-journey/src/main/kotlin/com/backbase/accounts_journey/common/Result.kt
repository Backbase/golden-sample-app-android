package com.backbase.accounts_journey.common

/**
 * Keeps the state of an object. It can be success or error.
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
sealed interface Result<out T : Any?> {
    data class Success<out T : Any>(val value: T) : Result<T>

    data class Error(
        val exception: Throwable,
        val message: String? = exception.localizedMessage
    ) : Result<Nothing>
}

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(this.value)
    return this
}

fun <T : Any> Result<T>.onSuccessValue(): T {
    return (this as Result.Success<T>).value
}

inline fun <T : Any> Result<T>.onError(action: (Throwable) -> Unit): Result<T> {
    if (this is Result.Error) action(this.exception)
    return this
}

fun <T : Any> Result<T>.onErrorValue(): Result.Error {
    return this as Result.Error
}
