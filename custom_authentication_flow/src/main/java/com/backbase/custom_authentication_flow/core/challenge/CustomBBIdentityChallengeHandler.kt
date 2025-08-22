package com.backbase.custom_authentication_flow.core.challenge

import android.content.Context
import com.backbase.android.identity.BBIdentityRoutersProvider
import com.backbase.android.identity.common.BBIdentityErrorCodes
import com.backbase.android.identity.common.challenge.BBIdentityChallengeHandler
import com.backbase.android.identity.common.routers.BBIdentityRouter
import com.backbase.android.identity.common.routers.BBIdentityRouterContext
import com.backbase.android.utils.net.request.Request
import com.backbase.android.utils.net.response.Response
import com.google.gson.Gson
import com.google.gson.JsonObject

internal abstract class CustomBBIdentityChallengeHandler(
    routerProvider: BBIdentityRoutersProvider,
    context: Context
) : BBIdentityChallengeHandler(routerProvider, context) {

    abstract val requireRouter: BBIdentityRouter
    abstract val identityRouterContext: BBIdentityRouterContext

    protected val contract = object : CustomChallengeContract {
        override fun setChallengeResponse(response: Response) {
            this@CustomBBIdentityChallengeHandler.setChallengeResponse(response)
        }

        override fun cancel(
            routerContext: BBIdentityRouterContext,
            error: Response
        ) {
            this@CustomBBIdentityChallengeHandler.setChallengeResponse(error)
        }

        override fun finish(routerContext: BBIdentityRouterContext) {
            finishChallenge()
        }
    }

    override fun handleChallenge(request: Request, response: Response) {
        super.handleChallenge(request, response)

        if (!validateChallengeJson(response, ERROR_CODE)) return
    }

    protected fun String.toJsonObject(): JsonObject? = runCatching {
        Gson().fromJson(this, JsonObject::class.java)
    }.getOrNull()

    protected fun JsonObject.getChallengeValueString(key: String): String? = runCatching {
        val challenge = getAsJsonArray(KEY_CHALLENGES).first().asJsonObject
        challenge.get(key).asString
    }.getOrNull()

    companion object {
        const val KEY_ACTION_URL = "actionUrl"
        private const val KEY_CHALLENGES = "challenges"
        private const val ERROR_CODE =
            BBIdentityErrorCodes.UNKNOWN_ERROR
    }
}