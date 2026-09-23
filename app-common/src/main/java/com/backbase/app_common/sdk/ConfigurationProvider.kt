package com.backbase.app_common.sdk

import com.backbase.android.configurations.BBConfiguration

interface ConfigurationProvider {
    val configuration: BBConfiguration
    val headers: Map<String, String>
}
