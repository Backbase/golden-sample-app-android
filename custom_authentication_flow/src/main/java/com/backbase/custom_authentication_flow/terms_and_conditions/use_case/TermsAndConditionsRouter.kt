package com.backbase.custom_authentication_flow.terms_and_conditions.use_case

import com.backbase.android.identity.common.routers.BBIdentityRouter
import com.backbase.android.identity.common.routers.BBIdentityRouterContext
import com.backbase.custom_authentication_flow.core.challenge.CustomChallengeContract

internal interface TermsAndConditionsRouter : BBIdentityRouter {
    fun promptScreen(
        actionUrl: String,
        flowId: String,
        contract: CustomChallengeContract,
        context: BBIdentityRouterContext
    )
}