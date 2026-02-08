package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.accounts_journey.presentation.onekosmos.AppConstant
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
import com.onekosmos.blockid.sdk.BlockIDSDK
import org.koin.core.context.loadKoinModules
import java.security.Security

/**
 * Setup the necessary dependencies and configurations.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Security.getProviders().forEach {
            println(it)
        }

        BlockIDSDK.initialize(this)
        // To set any proxy uncomment below line
        //  BlockIDSDK.getInstance().setProxy("45.95.99.20", 7580, "vautvdmg", "ag2idbos8oo6");
        BlockIDSDK.getInstance().setLicenseKey(AppConstant.licenseKey)

        Security.getProviders().forEach {
            println(it)
        }

        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
            BBLogger.debug(TAG, "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }
        startKoinIfNotStarted()
        initializeBackbase(AppConfigurationProvider())
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
