package com.backbase.golden_sample_app.authentication

import com.backbase.android.clients.auth.oauth2.BBOAuth2AuthClient
import com.backbase.android.core.networking.auth.BBOAuth2InvalidAccessTokenResolver
import com.backbase.android.core.networking.error.ErrorResponseListener
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.utils.net.request.Request
import com.backbase.android.utils.net.response.Response

/**
 * Created by Backbase R&D B.V on 19/08/2021.
 *
 * Invalid session resolver implementation [InvalidSessionResolver]
 */
internal class InvalidSessionResolver(
    oAuth2AuthClient: BBOAuth2AuthClient,
    private val onSessionRefreshFailure: () -> Unit = {
        BBLogger.debug("InvalidSessionResolver", "Failed to refresh token")
    }
) : BBOAuth2InvalidAccessTokenResolver(oAuth2AuthClient, mutableMapOf<String, String>(), null) {

    override fun handleResponse(response: Response, request: Request, errorResponseListener: ErrorResponseListener) {
        val wrappedListener = ErrorResponseListenerWithCallbacks(errorResponseListener, onSessionRefreshFailure)
        super.handleResponse(response, request, wrappedListener)
    }

    /**
     * Warning: Designed for *single use*. Runs either [onSuccess] or [onFailure] exactly once. After running one of
     * those callbacks, forwards to [wrapped].
     */
    private class ErrorResponseListenerWithCallbacks(
        private val wrapped: ErrorResponseListener,
        private val onFailure: () -> Unit
    ) : ErrorResponseListener {

        private var hasRunCallback = false

        override fun requestDidSucceed(response: Response) = wrapped.requestDidSucceed(response)

        override fun requestDidFail() {
            runCallback(onFailure)
            wrapped.requestDidFail()
        }

        override fun requestDidFailWithResponse(response: Response) {
            runCallback(onFailure)
            wrapped.requestDidFailWithResponse(response)
        }

        /**
         * Run callbacks through this function to ensure only a single callback is run within a single instance of this
         * class.
         */
        private inline fun runCallback(callback: () -> Unit) {
            if (!hasRunCallback) {
                callback()
                hasRunCallback = true
            }
        }
    }
}
