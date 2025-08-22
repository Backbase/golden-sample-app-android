package com.backbase.custom_authentication_flow.core.challenge

import com.backbase.android.identity.common.routers.BBIdentityRouterContract
import com.backbase.android.utils.net.response.Response

internal interface CustomChallengeContract : BBIdentityRouterContract {
    fun setChallengeResponse(response: Response)
}

