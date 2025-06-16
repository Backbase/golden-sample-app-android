package com.backbase.accounts_demo

import android.app.Application
import com.backbase.accounts_demo.journey.accounts.injectAccountsJourney
import com.backbase.accounts_demo.journey.workspaces.injectWorkspacesJourney
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.backbase.android.identity.journey.authentication.stopAuthenticationJourney
import com.backbase.app_common.auth.CompositeSessionListener
import com.backbase.app_common.sdk.initializeAuthClient
import com.backbase.app_common.sdk.initializeBackbase
import com.backbase.app_common.sdk.startKoinIfNotStarted
import org.koin.core.context.loadKoinModules

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
//            BBLogger.debug(TAG, "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }
        startKoinIfNotStarted()
        initializeBackbase()
        initializeAuthClient(CompositeSessionListener)

        setupDependencies()

        initAuthenticationJourney()
    }

    private fun setupDependencies() {
        loadKoinModules(
            getDependenciesDeclaration()
        )
        injectWorkspacesJourney()
        injectAccountsJourney()
    }

    override fun onTerminate() {
        stopAuthenticationJourney()
        super.onTerminate()
    }
}
