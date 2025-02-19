package com.backbase.app_common

import com.backbase.analytics.event.ApiEvent
import com.backbase.android.dbs.DBSDataProvider
import com.backbase.android.dbs.DBSDataProviderListener
import com.backbase.android.observability.Tracker
import com.backbase.android.utils.net.NetworkConnector
import com.backbase.android.utils.net.NetworkConnectorBuilder
import com.backbase.android.utils.net.request.Request
import com.backbase.android.utils.net.request.RequestMethods
import com.backbase.android.utils.net.response.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomNetworkDBSDataProvider(
    private val tracker: Tracker,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DBSDataProvider {

    @Suppress("TooGenericExceptionCaught")
    override fun execute(request: Request, listener: DBSDataProviderListener) {
        val coroutineScope = CoroutineScope(ioDispatcher)

        try {
            coroutineScope.launch {
                val response = buildConnectionForRequest(request).connect()
                tracker.publish(coroutineScope, ApiEvent.ApiMethodEvent(request.requestMethod))
                tracker.publish(coroutineScope, ApiEvent.ApiUrlEvent(request.uri))
                tracker.publish(coroutineScope, ApiEvent.ApiBodyEvent(request.body))
                tracker.publish(coroutineScope, ApiEvent.ApiResponseCodeEvent(response.responseCode))
                tracker.publish(coroutineScope, ApiEvent.ApiSuccessEvent(response.stringResponse))

                listener.onSuccess(response)
            }
        } catch (e: Exception) {
            val errorResponse = Response()
            errorResponse.errorMessage = e.localizedMessage
            tracker.publish(coroutineScope, ApiEvent.ApiResponseCodeEvent(errorResponse.responseCode))
            tracker.publish(coroutineScope, ApiEvent.ApiErrorEvent(errorResponse.errorMessage))

            listener.onError(errorResponse)
        }
    }

    private fun buildConnectionForRequest(request: Request): NetworkConnector {
        val builder = NetworkConnectorBuilder(request.uri?.toURL().toString())
        builder.addRequestMethod(RequestMethods.getRequestMethod(request.requestMethod))
        builder.addBody(request.body)
        builder.addHeaders(request.headers)
        return builder.buildConnection()
    }
}
