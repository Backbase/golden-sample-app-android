package com.backbase.android.retail.authorization

import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.IdentityAuthClient1AuthenticationUseCase
import com.backbase.android.retail.BackbaseClient
import org.koin.dsl.module

internal fun authenticationAppModule() = module {
    single { AuthenticationConfiguration { } }

    if (BackbaseClient.authClient is BBIdentityAuthClient) {
        factory { BackbaseClient.authClient as BBIdentityAuthClient }
    }

    single<AuthenticationUseCase> {
        IdentityAuthClient1AuthenticationUseCase(
            get(),
            get()
        )
    }
}
