package com.backbase.app_common.sdk

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.utils.net.NetworkConnectorBuilder

fun Application.initializeBackbase(configurationProvider: ConfigurationProvider) {
    Backbase.initialize(applicationContext, configurationProvider.configuration)
    setupHttpHeaders(configurationProvider.headers)
}

private fun setupHttpHeaders(headers: Map<String, String>) {
    NetworkConnectorBuilder.Configurations.appendHeaders(headers)
}
