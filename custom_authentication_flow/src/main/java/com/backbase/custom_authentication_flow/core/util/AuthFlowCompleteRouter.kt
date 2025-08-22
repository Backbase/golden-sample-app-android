package com.backbase.custom_authentication_flow.core.util

import com.backbase.android.identity.journey.authentication.use_case.impl.NavigationEvent

interface AuthFlowCompleteRouter {
    fun sendEvent(navigationEvent: NavigationEvent)
}