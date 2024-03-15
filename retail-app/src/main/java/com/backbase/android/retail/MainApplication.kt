package com.backbase.android.retail

import android.app.Application
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.android.identity.journey.authentication.initAuthenticationJourney

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
            BBLogger.debug("network", "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }
        initializeBackbase()
        setupAuthClient()
        setupHttpHeaders()
        startKoinForApplication()
        initAuthenticationJourney()
    }
}
