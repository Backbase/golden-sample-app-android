package com.backbase.golden_sample_app

import com.backbase.android.configurations.Configuration
import com.backbase.android.configurations.IdentityConfiguration
import com.backbase.app_common.sdk.ConfigurationProvider

class AppConfigurationProvider(
    override val configuration: Configuration = Configuration {
        serverUrl = "https://app.dev.sdbxaz.azure.backbaseservices.com"
        version = "6.1.5"
        identityConfiguration = IdentityConfiguration {
            baseUrl = "https://identity.dev.sdbxaz.azure.backbaseservices.com"
            realm = "retail"
            clientId = "mobile-client"
            applicationKey = "retail"
        }
        allowedDomains = listOf("*")
        bankTimeZone = "Europe/Amsterdam"
    },

    override val headers: Map<String, String> = mapOf("X-SDBXAZ-API-KEY" to "bb-306fc262-lfil-0017-d9un-1111d5a694b0")
) : ConfigurationProvider