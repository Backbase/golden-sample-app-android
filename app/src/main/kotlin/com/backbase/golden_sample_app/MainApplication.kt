package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.clients.auth.AuthClient
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.device.BBDeviceAuthenticator
import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.AuthenticationUseCase
import com.backbase.android.identity.journey.authentication.identity_auth_client_1.IdentityAuthClient1AuthenticationUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Backbase.initialize(applicationContext, "backbase/config.json", false)
        val backbase = checkNotNull(Backbase.getInstance()) {
            "Backbase must be initialized before initializeJourneyApplication is called"
        }

        val appConfiguration = AppConfiguration {
            authClient = BBIdentityAuthClient(applicationContext, "your-client-secret").apply {
                addAuthenticator(BBDeviceAuthenticator())
            }
        }

        val authClient = appConfiguration.authClient
        backbase.registerAuthClient(authClient)

        initializeJourneyApplication(appConfiguration) {
            module {
                single { AuthenticationConfiguration {} }
                single {
                    IdentityAuthClient1AuthenticationUseCase(
                        get(),
                        get()
                    )
                } bind AuthenticationUseCase::class
            }
        }
    }

    private fun initializeJourneyApplication(
        appConfiguration: AppConfiguration,
        additionalModules: () -> Module
    ) {
        // TODO: what to do with authClient?
        val authClient = appConfiguration.authClient

        val applicationModule = module {
            factory { Backbase.requireInstance() }
            factory { get<Backbase>().authClient }
            if (appConfiguration.authClient is BBIdentityAuthClient)
                factory { get<AuthClient>() as BBIdentityAuthClient }
        }

        startKoinIfNotStarted()
        loadKoinModules(listOf(applicationModule, additionalModules()))
    }

    private fun Application.startKoinIfNotStarted() {
        if (KoinContextHandler.getOrNull() == null) {
            startKoin { androidContext(applicationContext) }
        }
    }
}

