package com.backbase.accounts_journey.common

/**
 *  Try catch a mapping.
 *
 *  Created by Backbase R&D B.V on 04/10/2023.
 */
@Suppress("TooGenericExceptionCaught", "SwallowedException")
inline fun <T, R> T.mapCatching(block: T.() -> R): R? {
    return try {
        block()
    } catch (e: Throwable) {
        null
    }
}
