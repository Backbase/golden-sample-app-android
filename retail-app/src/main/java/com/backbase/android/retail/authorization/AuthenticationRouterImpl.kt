package com.backbase.android.retail.authorization

import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.journey.authentication.AuthenticationRouter

internal class AuthenticationRouterImpl(private val onAuthenticated: () -> Unit) : AuthenticationRouter {
    override fun onAuthenticated() {
        BBLogger.debug("Authentication", "Authenticated")
        onAuthenticated.invoke()
    }
}
