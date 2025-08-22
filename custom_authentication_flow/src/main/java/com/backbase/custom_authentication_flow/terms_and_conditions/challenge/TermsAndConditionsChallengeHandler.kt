package com.backbase.custom_authentication_flow.terms_and_conditions.challenge

import android.content.Context
import com.backbase.android.identity.BBIdentityRoutersProvider
import com.backbase.android.identity.common.BBIdentityErrorCodes
import com.backbase.android.identity.common.flow.BBIdentityFlowType
import com.backbase.android.identity.common.routers.BBIdentityRouterContext
import com.backbase.android.utils.net.request.Request
import com.backbase.android.utils.net.response.Response
import com.backbase.custom_authentication_flow.core.challenge.CustomBBIdentityChallengeHandler
import com.backbase.custom_authentication_flow.terms_and_conditions.use_case.TermsAndConditionUseCase
import com.backbase.custom_authentication_flow.core.util.flowId
import com.backbase.custom_authentication_flow.core.util.getRouter
import com.google.gson.JsonObject
import java.util.UUID

internal class TermsAndConditionsChallengeHandler(
    private val routerProvider: BBIdentityRoutersProvider,
    context: Context
) : CustomBBIdentityChallengeHandler(routerProvider, context) {

    override val requireRouter: TermsAndConditionUseCase
        get() = requireNotNull(routerProvider.getRouter(TermsAndConditionUseCase::class.java)) {
            "Unable to get TermsAndConditionsRouter from routersProvider"
        }

    override val identityRouterContext: BBIdentityRouterContext by lazy {
        object : BBIdentityRouterContext {
            override val flowType: BBIdentityFlowType
                get() = BBIdentityFlowType.Registration
            override val flowID: UUID
                get() = UUID.randomUUID()
        }
    }

    override fun handleChallenge(request: Request, response: Response) {
        super.handleChallenge(request, response)

        val responseJson = response.stringResponse?.toJsonObject() ?: JsonObject()
        val actionUrl = responseJson.getChallengeValueString(KEY_ACTION_URL)
        if (actionUrl == null) {
            sendChallengeError(ERROR_CODE, "$KEY_ACTION_URL is not found", response)
            return
        }

        /**
         * If you have additional data to pass to the Terms and Conditions screen,
         * you can extract it from the responseJson here and pass it through this function.
         */
        requireRouter.promptScreen(
            actionUrl,
            request.flowId().toString(),
            contract,
            identityRouterContext
        )
    }

    override fun describe(): String = "Backbase identity tnc dpa challenge"

    override fun getChallenge(): String = CHALLENGE_TYPE_TNC_DPA

    companion object {
        const val CHALLENGE_TYPE_TNC_DPA = "tnc-dpa"
        private const val ERROR_CODE =
            BBIdentityErrorCodes.DEVICE_TERMS_AND_CONDITIONS_INVALID_RESPONSE
    }
}