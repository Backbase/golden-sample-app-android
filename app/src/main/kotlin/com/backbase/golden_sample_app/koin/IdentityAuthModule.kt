package com.backbase.golden_sample_app.koin

import com.backbase.android.Backbase
import com.backbase.android.clients.auth.AuthClient
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.NavigationEventEmitter
import com.backbase.android.identity.journey.authentication.routing.api.AuthenticationRouter
import com.backbase.android.identity.journey.authentication.use_case.DefaultAuthenticationFlowsUseCase
import com.backbase.android.identity.journey.authentication.use_case.api.AuthenticationFlowsUseCase
import com.backbase.android.identity.journey.authentication.use_case.impl.DefaultNavigationEventEmitter
import com.backbase.android.retail.journey.SessionEmitter
import com.backbase.golden_sample_app.router.AuthenticationRouterImpl
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Dependency setup for Identity Authentication.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
internal fun identityAuthModule(
    sessionEmitter: SessionEmitter,
) = module {
    factory { Backbase.requireInstance() }
    factory { get<Backbase>().authClient }

    single { AuthenticationConfiguration {} }

    if (Backbase.requireInstance().authClient is BBIdentityAuthClient) {
        factory<BBIdentityAuthClient> { get<AuthClient>() as BBIdentityAuthClient }
    }

    factory { sessionEmitter }

    single { DefaultNavigationEventEmitter() } bind NavigationEventEmitter::class

    single {
        DefaultAuthenticationFlowsUseCase(
            application = get(),
            authClient = get(),
            sessionEmitter = get(),
            credentialsStorage = get(),
            navigationEventEmitter = get(),
            authenticationDeregistrationListener = getOrNull()
        )
    } bind AuthenticationFlowsUseCase::class

    factory { AuthenticationRouterImpl(get(), get(), get()) } bind AuthenticationRouter::class
}
