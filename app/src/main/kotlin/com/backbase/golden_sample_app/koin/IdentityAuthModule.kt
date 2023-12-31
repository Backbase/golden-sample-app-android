package com.backbase.golden_sample_app.koin

import com.backbase.android.Backbase
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.AuthenticationRouter
import com.backbase.android.identity.journey.authentication.AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.IdentityAuthClient1AuthenticationUseCase
import com.backbase.android.listeners.NavigationEventListener
import com.backbase.android.retail.journey.NavigationEventEmitter
import com.backbase.android.retail.journey.SessionEmitter
import com.backbase.golden_sample_app.router.AuthenticationRouterImpl
import org.koin.dsl.module

/**
 * Dependency setup for Identity Authentication.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
internal fun identityAuthModule(
    sessionEmitter: SessionEmitter,
) = module {
    single { AuthenticationConfiguration { } }

    factory { Backbase.requireInstance() }

    factory { get<Backbase>().authClient }

    if (Backbase.requireInstance().authClient is BBIdentityAuthClient) {
        factory { Backbase.requireInstance().authClient as BBIdentityAuthClient }
    }

    single { sessionEmitter }

    factory<NavigationEventEmitter> { DefaultNavigationEventEmitter(Backbase.requireInstance()) }

    single<AuthenticationUseCase> {
        IdentityAuthClient1AuthenticationUseCase(
            get(),
            get()
        )
    }

    factory<AuthenticationRouter> {
        AuthenticationRouterImpl(get(), get(), get())
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
