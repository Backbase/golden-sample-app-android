package com.backbase.custom_authentication_flow.terms_and_conditions.challenge

import android.content.Context
import com.backbase.android.identity.BBIdentityRoutersProvider
import com.backbase.android.identity.common.challenge.BBIdentityChallengeHandler
import com.backbase.custom_authentication_flow.core.challenge.CustomErrorResponseResolver

internal class TermsAndConditionsResolver(
    private val context: Context,
    private val bbIdentityAuthenticatorsProvider: BBIdentityRoutersProvider
) : CustomErrorResponseResolver() {

    override val challenge: String
        get() = TermsAndConditionsChallengeHandler.CHALLENGE_TYPE_TNC_DPA

    override fun challengeHandler(): BBIdentityChallengeHandler =
        TermsAndConditionsChallengeHandler(bbIdentityAuthenticatorsProvider, context)
}