package com.backbase.golden_sample_app

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.core.utils.BBLogger
import com.backbase.android.identity.fido.FidoUafFacetUtils
import com.backbase.golden_sample_app.common.TAG

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            BBLogger.setLogLevel(BBLogger.LogLevel.DEBUG)
            BBLogger.debug(TAG, "Facet ID: <${FidoUafFacetUtils.getFacetID(this)}>")
        }

        Backbase.initialize(applicationContext, "backbase/config.json", false)
        val backbase = checkNotNull(Backbase.getInstance()) {
            "Backbase must be initialized before initializeJourneyApplication is called"
        }
    }
}

