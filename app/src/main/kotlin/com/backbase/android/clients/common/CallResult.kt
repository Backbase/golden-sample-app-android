package com.backbase.android.clients.common

import com.backbase.android.utils.net.response.Response

/**
 * A discriminate union for the response of suspended client functions.
 * @param T the class of the success payload.
 * Matches the success payload in the [Error] and [None] case, just for type-safe function returns.
 */
sealed class CallResult<T : Any> {

    /**
     * Success case.
     * Corresponds to HTTP status codes between 200 (inclusive) and 400 (exclusive).
     *
     * @param data the success payload.
     */
    data class Success<T : Any>(val data: T) : CallResult<T>()

    /**
     * Error case.
     *
     * @param errorResponse the error response as received from the client.
     */
    data class Error<T : Any>(val errorResponse: Response) : CallResult<T>()

    /*
     * None cannot be a data class because data classes need at least one field.
     * It doesn't make sense to add a field to a None result.
     * Therefore, None is a regular class, with manually-written equals(), hashCode(), and toString().
     */
    /**
     * No response, may it be due to cancellation or no call at all.
     */
    class None<T : Any> : CallResult<T>() {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        override fun toString(): String {
            return "None()"
        }
    }
}
