package com.backbase.accounts_journey.common

/**
 * Custom exceptions.
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
class FailedGetDataException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)

class NoInternetException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)

class NoResponseException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)
