package com.backbase.golden_sample_app.auth

import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.routing.api.AuthenticationRouter
import com.backbase.deferredresources.DeferredBoolean
import org.koin.core.definition.Definition
import org.koin.core.module.Module

internal fun Module.appAuthModule(block: AppAuthDependenciesScope.() -> Unit) {
    val dependencies = AppAuthDependenciesScope().apply(block)
    single(definition = dependencies.authenticationConfiguration)
    factory<AuthenticationRouter>(definition = dependencies.authenticationRouter)
}

internal fun Module.appAuthModule() {
    appAuthModule {
        authenticationConfiguration = {
            AppIdentityAuthenticationConfiguration()
        }
        authenticationRouter = {
            AuthenticationRouterImpl(get(), get(), get())
        }
    }
}

internal class AppAuthDependenciesScope {
    lateinit var authenticationConfiguration: Definition<AuthenticationConfiguration>
    lateinit var authenticationRouter: Definition<AuthenticationRouter>
}

@Suppress("FunctionName") // factory function
@JvmSynthetic
internal fun AppIdentityAuthenticationConfiguration(): AuthenticationConfiguration =
    AuthenticationConfiguration.Builder().apply {
        enableStatefulAuthentication = DeferredBoolean.Constant(true)
    }.build()
