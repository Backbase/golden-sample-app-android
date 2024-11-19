package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.accounts_journey.AccountsJourney
import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.configuration.accountlist.AccountListScreenConfiguration
import com.backbase.android.Backbase
import com.backbase.android.business.journey.workspaces.WorkspacesJourney
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.backbase.android.identity.journey.authentication.stopAuthenticationJourney
import com.backbase.android.utils.net.NetworkConnectorBuilder
import com.backbase.cards_journey.impl.cardsJourneyModule
import com.backbase.cards_journey.impl.koin.cardModule
import com.backbase.golden_sample_app.authentication.CompositeSessionListener
import com.backbase.golden_sample_app.common.TAG
import com.backbase.golden_sample_app.koin.accountsModule
import com.backbase.golden_sample_app.koin.appModule
import com.backbase.golden_sample_app.koin.featureFilterModule
import com.backbase.golden_sample_app.koin.identityAuthModule
import com.backbase.golden_sample_app.koin.navigationModule
import com.backbase.golden_sample_app.koin.presentationModule
import com.backbase.golden_sample_app.koin.securityModule
import com.backbase.golden_sample_app.koin.servicesModule
import com.backbase.golden_sample_app.koin.userModule
import com.backbase.golden_sample_app.koin.workspacesModule
import com.google.gson.internal.LinkedTreeMap
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 * Setup the necessary dependencies and configurations.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
class MainApplication : Application() {

    private val sessionEmitter = CompositeSessionListener()
    private val authClient: BBIdentityAuthClient by lazy {
        BBIdentityAuthClient(this, "")
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
            BBLogger.debug(TAG, "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }
        initializeBackbase()
        setupAuthClient()
        setupHttpHeaders()
        setupDependencies()
        initAuthenticationJourney()
    }

    private fun initializeBackbase(
        backbaseConfigAssetPath: String = "backbase/config.json",
        encrypted: Boolean = false
    ) {
        Backbase.initialize(applicationContext, backbaseConfigAssetPath, encrypted)
    }

    private fun setupAuthClient() {
        val backbase = Backbase.requireInstance()
        backbase.registerAuthClient(authClient)
        backbase.authClient.startSessionObserver(sessionEmitter)
    }

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
    private fun setupHttpHeaders() {
        val headers = Backbase.requireInstance().configuration.custom["default-http-headers"]
        val hashMap: HashMap<String, String> = HashMap()
        try {
            val map = headers as LinkedTreeMap<*, *>
            for ((k, v) in map) {
                val stringK = k as String
                val stringV = v as String
                hashMap[stringK] = stringV
            }
        } catch (e: Exception) {
            BBLogger.error(TAG, "Failed to cast header values")
        }
        NetworkConnectorBuilder.Configurations.appendHeaders(hashMap)
    }

    private fun setupAccountsJourneyConfiguration(): AccountsJourneyConfiguration {
        return AccountsJourneyConfiguration {
            this.accountListScreenConfiguration = AccountListScreenConfiguration {
                this.screenTitle = R.string.accounts_screen_title
            }
        }
    }

    private fun setupDependencies() = startKoin {
        androidContext(this@MainApplication)
        loadKoinModules(
            listOf(
                navigationModule,
                securityModule(this@MainApplication),
                servicesModule(this@MainApplication),
                userModule(),
                featureFilterModule,
                appModule(),
                presentationModule(context = this@MainApplication),
                identityAuthModule(sessionEmitter),
                workspacesModule,
                WorkspacesJourney.create(),
                accountsModule,
                AccountsJourney.create(configuration = setupAccountsJourneyConfiguration()),
                cardModule(this@MainApplication),
                cardsJourneyModule
            )
        )
    }

    override fun onTerminate() {
        stopAuthenticationJourney()
        super.onTerminate()
    }
}
