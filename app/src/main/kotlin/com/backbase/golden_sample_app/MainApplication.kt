package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney
import com.backbase.android.identity.journey.authentication.stopAuthenticationJourney
import com.backbase.app_common.auth.CompositeSessionListener
import com.backbase.app_common.sdk.initializeAuthClient
import com.backbase.app_common.sdk.initializeBackbase
import com.backbase.app_common.sdk.startKoinIfNotStarted
import com.backbase.golden_sample_app.common.TAG
import com.backbase.golden_sample_app.journey.accounts.injectAccountsJourney
import com.backbase.golden_sample_app.journey.workspaces.injectWorkspacesJourney
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
