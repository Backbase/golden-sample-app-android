package com.backbase.golden_sample_app

import com.backbase.android.configurations.BBConfiguration
import com.backbase.android.configurations.IdentityConfiguration
import com.backbase.app_common.sdk.ConfigurationProvider

class AppConfigurationProvider(
    override val configuration: BBConfiguration = BBConfiguration(serverUrl = "https://app.prd.sdbxaz.azure.backbaseservices.com") {
        version = "6.1.5"
        identityConfiguration = IdentityConfiguration(
            baseUrl = "https://identity.prd.sdbxaz.azure.backbaseservices.com",
            realm = "retail",
            clientId = "mobile-client"
        ) {
            applicationKey = "retail"
        }
        allowedDomains = listOf("*")
        persistentHeaders =
            mapOf("X-User-Context" to listOf("app.prd.sdbxaz.azure.backbaseservices.com"))
        bankTimeZone = "Europe/Amsterdam"
    },

    override val headers: Map<String, String> = mapOf("X-SDBXAZ-API-KEY" to "ADD YOUR API KEY HERE")
) : ConfigurationProvider
