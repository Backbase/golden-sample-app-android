package com.backbase.accounts_journey.common

class FailedGetDataException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)

class FailedAddDataException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)

class FailedDeleteDataException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)

class DataNotFoundException(
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
