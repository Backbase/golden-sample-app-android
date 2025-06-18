package com.backbase.app_common.sdk

import com.backbase.android.configurations.Configuration

interface ConfigurationProvider {
    val configuration: Configuration
    val headers: Map<String, String>
}