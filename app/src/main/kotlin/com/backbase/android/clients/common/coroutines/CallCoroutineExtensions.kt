package com.backbase.android.clients.common.coroutines

import com.backbase.android.clients.common.Call
import com.backbase.android.clients.common.CallResult
import com.backbase.android.clients.common.ResponseListener
import com.backbase.android.core.errorhandling.ErrorCodes
import com.backbase.android.utils.net.response.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Coroutines extensions for the Call.
 *
 * Executes the call in a coroutine, in the calling context.
 * @return the result of the call.
 */
suspend inline fun <reified T : Any> Call<T>.executeAsSuspended(): CallResult<T> =
    suspendCoroutine { cont ->

        val callback = object : ResponseListener<T> {
            override fun onSuccess(payload: T) {
                when {
                    T::class.java.isAssignableFrom(payload::class.java) -> {
                        cont.resume(CallResult.Success(payload))
                    }

                    else -> {
                        cont.resume(
                            CallResult.Error(
                                Response().apply {
                                    responseCode = ErrorCodes.GENERAL_TARGETING_ERROR.code
                                    errorMessage =
                                        "Expected ${T::class.java} but got ${payload::class.java}"
                                }
                            )
                        )
                    }
                }
            }

            override fun onError(errorResponse: Response) {
                cont.resume(CallResult.Error(errorResponse))
            }
        }
        enqueue(callback)
    }
