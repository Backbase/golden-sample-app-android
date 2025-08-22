package com.backbase.custom_authentication_flow.core.util

import com.backbase.android.clients.common.Call
import com.backbase.android.clients.common.CallResult
import com.backbase.android.clients.common.RawResponseListener
import com.backbase.android.identity.BBIdentityRoutersProvider
import com.backbase.android.identity.common.routers.BBIdentityRouter
import com.backbase.android.utils.net.request.Request
import com.backbase.android.utils.net.response.Response
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


internal const val FLOW_ID_HEADER = "identity-mobile-flow-id"

internal fun <T : BBIdentityRouter> BBIdentityRoutersProvider.getRouter(clazz: Class<T>): T? =
    routers(clazz).firstOrNull()

internal fun Request.flowId(): UUID? =
    headers?.get(FLOW_ID_HEADER)?.let { UUID.fromString(it) }

internal suspend fun <T : Any> Call<T>.executeAsSuspendedRaw(): CallResult<Response> =
    suspendCoroutine { cont ->

        val callback = object : RawResponseListener {

            override fun onSuccess(response: Response) {
                cont.resume(CallResult.Success(response))
            }

            override fun onError(errorResponse: Response) {
                cont.resume(CallResult.Error(errorResponse))
            }
        }
        enqueue(callback)
    }