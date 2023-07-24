package com.backbase.golden_sample_app.koin

import com.backbase.android.Backbase
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.IdentityAuthClient1AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.OutOfBandTransactionSigningUseCaseImpl
import com.backbase.android.identity.journey.oob_transaction.OutOfBandTransactionSigningRouter
import com.backbase.android.identity.journey.oob_transaction.OutOfBandTransactionSigningUseCase
import com.backbase.android.listeners.NavigationEventListener
import com.backbase.android.retail.journey.NavigationEventEmitter
import com.backbase.android.retail.journey.SessionEmitter
import com.backbase.golden_sample_app.authentication.OutOfBandTransactionSigningRouterImpl
import org.koin.dsl.module

/**
 * Created by Backbase R&D B.V on 19/08/2021.
 *
 */
internal fun identityAuthModule(
    configuration: AuthenticationConfiguration,
    sessionEmitter: SessionEmitter,
) = module {
    single { configuration }

    factory { Backbase.requireInstance() }

    factory { get<Backbase>().authClient }

    if (Backbase.requireInstance().authClient is BBIdentityAuthClient) {
        factory { Backbase.requireInstance().authClient as BBIdentityAuthClient }
    }

    single<SessionEmitter> { sessionEmitter }

    factory<NavigationEventEmitter> { DefaultNavigationEventEmitter(Backbase.requireInstance()) }

    single<AuthenticationUseCase> {
        IdentityAuthClient1AuthenticationUseCase(
            get(),
            get(),
            authenticationDeregistrationListener = get()
        )
    }

    single<OutOfBandTransactionSigningRouter> { OutOfBandTransactionSigningRouterImpl(get()) }

    single<OutOfBandTransactionSigningUseCase> { OutOfBandTransactionSigningUseCaseImpl(get()) }
}

private class DefaultNavigationEventEmitter(
    private val backbase: Backbase
) : NavigationEventEmitter {
    override fun registerNavigationEventListener(listener: NavigationEventListener) =
        backbase.registerNavigationEventListener(listener)

    override fun unregisterNavigationEventListener(listener: NavigationEventListener) =
        backbase.unregisterNavigationEventListener(listener)
}
