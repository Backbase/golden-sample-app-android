package com.backbase.app_common.auth

import android.app.Application
import com.backbase.android.clients.auth.AuthClient
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.IdentityAuthClient1AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.NavigationEventEmitter
import com.backbase.android.identity.journey.authentication.use_case.DefaultAuthenticationFlowsUseCase
import com.backbase.android.identity.journey.authentication.use_case.api.AuthenticationFlowsUseCase
import com.backbase.android.identity.journey.authentication.use_case.api.AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.use_case.impl.DefaultNavigationEventEmitter
import com.backbase.app_common.auth.session.SessionManager
import org.koin.core.module.Module
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module

/**
 * Dependency setup for Identity Authentication.
 */
fun Application.initIdentityAuthModule(additionalDependencies: ModuleDeclaration = {}): Module =
    module {
        factory<BBIdentityAuthClient> {
            val authClient = get<AuthClient>()

            if (authClient is BBIdentityAuthClient)
                authClient
            else
                BBIdentityAuthClient(applicationContext)
        }

        single<NavigationEventEmitter> {
            DefaultNavigationEventEmitter()
        }

        single<AuthenticationUseCase> {
            IdentityAuthClient1AuthenticationUseCase(
                application = get(),
                authClient = get(),
                credentialsStorage = get()
            )
        }

        single<AuthenticationFlowsUseCase> {
            DefaultAuthenticationFlowsUseCase(
                application = get(),
                authClient = get(),
                sessionEmitter = get(),
                credentialsStorage = get(),
                navigationEventEmitter = get(),
                authenticationDeregistrationListener = getOrNull()
            )
        }

        single {
            SessionManager(
                authClient = get(),
                userRepository = get(),
            )
        }

        additionalDependencies(this)
    }
