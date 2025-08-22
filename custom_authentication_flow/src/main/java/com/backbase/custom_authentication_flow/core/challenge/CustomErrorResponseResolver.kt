package com.backbase.custom_authentication_flow.core.challenge

import com.backbase.android.clients.auth.AuthHeadersUtils
import com.backbase.android.core.networking.error.ErrorResponseListener
import com.backbase.android.core.networking.error.ErrorResponseResolver
import com.backbase.android.identity.common.challenge.BBIdentityChallengeHandler
import com.backbase.android.identity.common.challenge.BBIdentityChallengeHandlerListener
import com.backbase.android.utils.net.request.Request
import com.backbase.android.utils.net.response.Response


abstract class CustomErrorResponseResolver : ErrorResponseResolver {

    abstract val challenge: String
    abstract fun challengeHandler(): BBIdentityChallengeHandler

    override fun canHandleResponse(response: Response, request: Request): Boolean {
        return AuthHeadersUtils.getAuthHeader("challenge_types=$challenge", response) != null
    }

    override fun handleResponse(
        response: Response,
        request: Request,
        errorResponseListener: ErrorResponseListener
    ) {
        challengeHandler().run {
            setListener(challengeHandlerListener(errorResponseListener))
            handleChallenge(request, response)
        }
    }

    private fun challengeHandlerListener(errorResponseListener: ErrorResponseListener) =
        object : BBIdentityChallengeHandlerListener {
            override fun onIdentityChallengeCompleted(challenge: String, response: Response) {
                errorResponseListener.requestDidSucceed(response)
            }

            override fun onIdentityChallengeFailed(challenge: String, response: Response) {
                errorResponseListener.requestDidFailWithResponse(response)
            }
        }
}