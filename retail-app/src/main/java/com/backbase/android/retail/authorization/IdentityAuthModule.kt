package com.backbase.android.retail.authorization

import com.backbase.android.Backbase
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.identity.journey.authentication.AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.IdentityAuthClient1AuthenticationUseCase
import com.backbase.android.listeners.NavigationEventListener
import com.backbase.android.retail.BackbaseClient
import com.backbase.android.retail.AppSessionEmitter
import com.backbase.android.retail.journey.NavigationEventEmitter
import com.backbase.android.retail.journey.SessionEmitter
import org.koin.dsl.module

internal fun authenticationModule() = module {
    single { AuthenticationConfiguration { } }

    factory { BackbaseClient }

    factory { BackbaseClient.authClient }

    if (BackbaseClient.authClient is BBIdentityAuthClient) {
        factory { BackbaseClient.authClient as BBIdentityAuthClient }
    }

    single<SessionEmitter> { AppSessionEmitter }

    factory<NavigationEventEmitter> { DefaultNavigationEventEmitter(BackbaseClient) }

    single<AuthenticationUseCase> {
        IdentityAuthClient1AuthenticationUseCase(
            get(),
            get()
        )
    }

    factory<AuthenticationRouter> {
        AuthenticationRouterImpl()
    }
}

private class DefaultNavigationEventEmitter(
    private val backbase: Backbase
) : NavigationEventEmitter {
    override fun registerNavigationEventListener(listener: NavigationEventListener) =
        backbase.registerNavigationEventListener(listener)

    override fun unregisterNavigationEventListener(listener: NavigationEventListener) =
        backbase.unregisterNavigationEventListener(listener)
}
