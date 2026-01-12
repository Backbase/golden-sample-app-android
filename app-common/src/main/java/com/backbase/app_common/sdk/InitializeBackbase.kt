package com.backbase.app_common.sdk

import android.app.Application
import android.widget.Toast
import com.backbase.android.Backbase
import com.backbase.android.developermode.customactions.domain.CustomAction
import com.backbase.android.developermode.main.enableDeveloperModeWithActivityNavigation
import com.backbase.android.utils.net.NetworkConnectorBuilder

fun Application.initializeBackbase(configurationProvider: ConfigurationProvider) {
    Backbase.initialize(applicationContext, configurationProvider.configuration)
    setupHttpHeaders(configurationProvider.headers)

    enableDeveloperModeWithActivityNavigation()
        .registerCustomAction {
            +CustomAction.Button(
                name = "Clear Demo Cache",
                description = "Clears all cached data from the demo application. This is a sample action for demonstration purposes.",
                handler = { actionContext ->
                    Toast.makeText(
                        this@initializeBackbase,
                        "Demo cache cleared!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
            +CustomAction.Switch(
                name = "Enable Mock API",
                description = "When enabled, the demo app will use mock data instead of real API calls.",
                initialState = false,
                handler = { isEnabled, actionContext ->
                    val message = if (isEnabled) "Mock API enabled" else "Mock API disabled"
                    Toast.makeText(
                        this@initializeBackbase,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
}

private fun setupHttpHeaders(headers: Map<String, String>) {
    NetworkConnectorBuilder.Configurations.appendHeaders(headers)
}
