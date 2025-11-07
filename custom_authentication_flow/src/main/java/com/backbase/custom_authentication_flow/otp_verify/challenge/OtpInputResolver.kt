package com.backbase.custom_authentication_flow.otp_verify.challenge

import android.content.Context
import com.backbase.android.identity.BBIdentityRoutersProvider
import com.backbase.android.identity.common.challenge.BBIdentityChallengeHandler
import com.backbase.custom_authentication_flow.core.challenge.CustomErrorResponseResolver

internal class OtpInputResolver(
    private val context: Context,
    private val bbIdentityAuthenticatorsProvider: BBIdentityRoutersProvider
) : CustomErrorResponseResolver() {

    override val challenge: String
        get() = OtpInputChallengeHandler.Companion.CHALLENGE_TYPE_OTP_VERIFY

    override fun challengeHandler(): BBIdentityChallengeHandler =
        OtpInputChallengeHandler(bbIdentityAuthenticatorsProvider, context)
}