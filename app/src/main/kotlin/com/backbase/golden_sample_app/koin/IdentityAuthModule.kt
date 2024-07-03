package com.backbase.golden_sample_app.koin

import com.backbase.android.Backbase
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.IdentityAuthClient1AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.NavigationEventEmitter
import com.backbase.android.identity.journey.authentication.routing.api.AuthenticationRouter
import com.backbase.android.identity.journey.authentication.use_case.api.AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.use_case.impl.DefaultNavigationEventEmitter
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

    factory<NavigationEventEmitter> { DefaultNavigationEventEmitter() }

    single<AuthenticationUseCase> {
        IdentityAuthClient1AuthenticationUseCase(
            get(),
            get(),
            get(),
        )
    }

    factory<AuthenticationRouter> {
        AuthenticationRouterImpl(get(), get(), get())
    }
}
