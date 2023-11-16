package com.backbase.accounts_journey.common

@Suppress("TooGenericExceptionCaught", "SwallowedException")
inline fun <T, R> T.mapCatching(block: T.() -> R): R? {
    return try {
        block()
    } catch (e: Throwable) {
        null
    }
}