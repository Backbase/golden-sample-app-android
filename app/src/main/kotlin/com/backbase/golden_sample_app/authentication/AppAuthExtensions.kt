package com.backbase.golden_sample_app.authentication

import android.app.Application
import com.backbase.android.clients.auth.AuthClient
import com.backbase.android.clients.auth.oauth2.BBOAuth2AuthClient
import com.backbase.android.core.networking.error.ErrorResponseResolver
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.resolver.BBIdentityChallengesResolver
import com.backbase.android.identity.resolver.BBIdentityPostChallengesActions
import com.backbase.android.listeners.SessionListener
import com.backbase.android.modules.SessionState
import com.backbase.golden_sample_app.koin.NotificationsAppStateProvider
import com.backbase.mobilenotifications.core.component.devicemanagement.DeviceManagementTokenSynchronizer
import org.koin.android.ext.android.get
import org.koin.core.context.KoinContextHandler

/**
 * Created by Backbase R&D B.V on 19/08/2021.
 * helpers for creating default auth error response resolvers
 */
internal fun Application.createDefaultAuthErrorResponseResolvers(
    authClient: AuthClient
): List<ErrorResponseResolver> {
    val authErrorResponseResolvers = mutableListOf<ErrorResponseResolver>()
    if (authClient is BBOAuth2AuthClient) {
        authErrorResponseResolvers.add(
            InvalidSessionResolver(authClient, onSessionRefreshFailure = {
                // SessionEndedRouter typically depends on Activity Context, so re-get it from Koin each time to avoid
                //  leaking that Activity:
                val sessionEndedRouter = KoinContextHandler.getOrNull()?.get<SessionEndedRouter>()
                sessionEndedRouter?.onSessionEnded()
                    ?: BBLogger.warning("session", "Session has expired but no SessionEndedRouter is available")
            })
        )

        if (authClient is BBIdentityAuthClient) {
            // Developer heads-up: This is a manual mimic of what BBIdentityAuthClient registers by default. We must
            //  mimic this behavior ourselves because there is no way to get access to what it internally registered.
            val identityResolver = BBIdentityChallengesResolver(
                applicationContext,
                authClient,
                BBIdentityPostChallengesActions(authClient)
            )
            authErrorResponseResolvers.add(identityResolver)
        }
    }

    return authErrorResponseResolvers
}

internal fun Application.pushAuthSessionListener(authClient: AuthClient) = SessionListener {
    val loggedIn = it == SessionState.VALID

    get<NotificationsAppStateProvider>().loggedIn = loggedIn

    if (loggedIn && authClient is BBIdentityAuthClient) {
        get<DeviceManagementTokenSynchronizer>().deviceId = authClient.deviceAuthenticator?.deviceId
    }
}
