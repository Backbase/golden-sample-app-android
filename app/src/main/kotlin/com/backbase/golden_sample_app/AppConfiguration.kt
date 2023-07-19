package com.backbase.golden_sample_app

import com.backbase.android.clients.auth.AuthClient

/**
 * Configuration for Journey-based application. Includes Journey configuration, Backbase instance configuration, and
 * other centralized configuration options.
 *
 * @param authClient The authentication client to use by the app. This is a required parameter.
 */
class AppConfiguration private constructor(
    val authClient: AuthClient
) {

    /**
     * A builder for this configuration class.
     *
     * Should be used directly by Java callers. Kotlin callers should use the DSL initializer instead.
     */
    class Builder {

        @set:JvmSynthetic
        var authClient: AuthClient? = null

        fun build() = AppConfiguration(
            checkNotNull(authClient) { "AuthClient must be supplied" }
        )
    }
}

@Suppress("FunctionName") // DSL initializer
@JvmSynthetic
fun AppConfiguration(initializer: AppConfiguration.Builder.() -> Unit) =
    AppConfiguration.Builder().apply(initializer).build()
