package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.configuration.accountlist.AccountListScreenConfiguration
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.backbase.android.identity.journey.authentication.stopAuthenticationJourney
import com.backbase.app_common.auth.initIdentityAuthModule
import com.backbase.app_common.sdk.initializeAuthClient
import com.backbase.app_common.sdk.initializeBackbase
import com.backbase.app_common.sdk.startKoinIfNotStarted
import com.backbase.golden_sample_app.auth.retailAuthModule
import com.backbase.golden_sample_app.authentication.CompositeSessionListener
import com.backbase.golden_sample_app.common.TAG
import com.backbase.golden_sample_app.koin.appModule
import com.backbase.golden_sample_app.koin.featureFilterModule
import com.backbase.golden_sample_app.koin.presentationModule
import com.backbase.golden_sample_app.koin.securityModule
import com.backbase.golden_sample_app.koin.servicesModule
import com.backbase.golden_sample_app.koin.userModule
import org.koin.core.context.loadKoinModules

/**
 * Setup the necessary dependencies and configurations.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
            BBLogger.debug(TAG, "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }
        startKoinIfNotStarted()
        initializeBackbase()
        initializeAuthClient(CompositeSessionListener)

        setupDependencies()

        initAuthenticationJourney()
    }

    private fun setupAccountsJourneyConfiguration(): AccountsJourneyConfiguration {
        return AccountsJourneyConfiguration {
            this.accountListScreenConfiguration = AccountListScreenConfiguration {
                this.screenTitle = R.string.accounts_screen_title
            }
        }
    }

    private fun setupDependencies() {
        loadKoinModules(
            listOf(
                securityModule(this@MainApplication),
                servicesModule(this@MainApplication),
                userModule(),
                featureFilterModule,
                appModule(),
                presentationModule(context = this@MainApplication),
                initIdentityAuthModule {
                    retailAuthModule()
                }
//                identityAuthModule(CompositeSessionListener),
//                workspacesModule(),
//                WorkspacesJourney.create(),
//                accountsModule,
//                AccountsJourney.create(configuration = setupAccountsJourneyConfiguration()),
            )
        )
    }

    override fun onTerminate() {
        stopAuthenticationJourney()
        super.onTerminate()
    }
}
