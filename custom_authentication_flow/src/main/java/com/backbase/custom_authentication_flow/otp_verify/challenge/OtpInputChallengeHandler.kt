package com.backbase.custom_authentication_flow.otp_verify.challenge

import android.content.Context
import com.backbase.android.identity.BBIdentityRoutersProvider
import com.backbase.android.identity.common.BBIdentityErrorCodes
import com.backbase.android.identity.common.flow.BBIdentityFlowType
import com.backbase.android.identity.common.routers.BBIdentityRouterContext
import com.backbase.android.utils.net.request.Request
import com.backbase.android.utils.net.response.Response
import com.backbase.custom_authentication_flow.core.challenge.CustomBBIdentityChallengeHandler
import com.backbase.custom_authentication_flow.core.util.flowId
import com.backbase.custom_authentication_flow.core.util.getRouter
import com.backbase.custom_authentication_flow.otp_verify.use_case.OtpInputRouterUseCase
import com.google.gson.JsonObject
import java.util.UUID

internal class OtpInputChallengeHandler(
    private val routerProvider: BBIdentityRoutersProvider,
    context: Context
) : CustomBBIdentityChallengeHandler(routerProvider, context) {

    override val requireRouter: OtpInputRouterUseCase
        get() = requireNotNull(routerProvider.getRouter(OtpInputRouterUseCase::class.java)) {
            "Unable to get BBOtpInputRouter from routersProvider"
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
        val referenceNumber = responseJson.getChallengeValueString(REF_NUMBER)
        if (referenceNumber == null) {
            sendChallengeError(
                BBIdentityErrorCodes.DEVICE_OTP_ENTRY_INVALID_RESPONSE,
                "$REF_NUMBER is not found",
                response
            )
            return
        }

        /**
         * If you have additional data to pass to the OTP screen,
         * you can extract it from the responseJson here and pass it through this function.
         */
        requireRouter.promptForOtp(
            referenceNumber,
            originalRequest.body.orEmpty(),
            request.flowId().toString(),
            contract,
            identityRouterContext
        )
    }

    override fun describe(): String = "Backbase identity custom otp challenge"

    override fun getChallenge(): String = CHALLENGE_TYPE_OTP_VERIFY

    companion object Companion {
        const val CHALLENGE_TYPE_OTP_VERIFY = "otp-verify"
        private const val REF_NUMBER = "referenceNumber"
    }
}