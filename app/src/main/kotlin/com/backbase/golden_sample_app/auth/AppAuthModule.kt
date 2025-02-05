package com.backbase.golden_sample_app.auth

import com.backbase.android.identity.journey.authentication.AuthenticationConfiguration
import com.backbase.android.identity.journey.authentication.routing.api.AuthenticationRouter
import com.backbase.golden_sample_app.router.AuthenticationRouterImpl
import org.koin.core.definition.Definition
import org.koin.core.module.Module

internal fun Module.appAuthModule(block: AppAuthDependenciesScope.() -> Unit) {
    val dependencies = AppAuthDependenciesScope().apply(block)
    single(definition = dependencies.authenticationConfiguration)
    factory<AuthenticationRouter>(definition = dependencies.authenticationRouter)
//    factory<AuthenticationJourneyRouter>(definition = dependencies.authenticationJourneyRouter)
//    factory<SessionEndedRouter>(definition = dependencies.sessionEndedRouter)
//    single<List<ErrorResponseResolver>>(definition = dependencies.errorResponseResolvers)
//    factory<ChangePasscodeRouter>(definition = dependencies.changePasscodeRouter)
//    factory<ChangePasswordRouter>(definition = dependencies.changePasswordRouter)
//    factory<FidoRegistrationRouter>(definition = dependencies.fidoRegistrationRouter)
//    factory<DeviceAuthorizationApprovalRouter>(definition = dependencies.deviceAuthorizationApprovalRouter)
//    factory<OutOfBandAuthenticationRouter>(definition = dependencies.outOfBandAuthenticationRouter)
//    factory<OutOfBandTransactionSigningRouter>(definition = dependencies.outOfBandTransactionSigningRouter)
}

internal fun Module.appAuthModule() {
    appAuthModule {
        authenticationConfiguration = {
            AppIdentityAuthenticationConfiguration()
        }
        authenticationRouter = {
//            RetailAuthenticationRouter(get())
            AuthenticationRouterImpl(get(), get(), get())
        }
//        authenticationJourneyRouter = {
//            RetailAppAuthenticationJourneyRouter(get())
//        }
//        sessionEndedRouter = {
//            RetailSessionEndedRouter(get(), get())
//        }
//        errorResponseResolvers = {
//            listOf(
//                BBIdentityChallengesResolver(
//                    get<BBIdentityAuthClient>(),
//                    get(),
//                    BBIdentityPostChallengesActions()
//                )
//            )
//        }
//        changePasscodeRouter = {
//            RetailIdentityChangePasscodeRouter(get())
//        }
//        changePasswordRouter = {
//            RetailIdentityChangePasswordRouter(get())
//        }
//        fidoRegistrationRouter = {
//            RetailFidoRegistrationRouter(get())
//        }
//        deviceAuthorizationApprovalRouter = {
//            RetailDeviceAuthorizationApprovalRouter(get())
//        }
//        outOfBandAuthenticationRouter = {
//            RetailOutOfBandAuthenticationRouter(get())
//        }
//        outOfBandTransactionSigningRouter = {
//            RetailOutOfBandTransactionSigningRouter(get())
//        }
    }
}

internal class AppAuthDependenciesScope {
    lateinit var authenticationConfiguration: Definition<AuthenticationConfiguration>
    lateinit var authenticationRouter: Definition<AuthenticationRouter>
//    lateinit var authenticationJourneyRouter: Definition<AuthenticationJourneyRouter>
//    lateinit var sessionEndedRouter: Definition<SessionEndedRouter>
//    lateinit var errorResponseResolvers: Definition<List<ErrorResponseResolver>>
//    lateinit var changePasscodeRouter: Definition<ChangePasscodeRouter>
//    lateinit var changePasswordRouter: Definition<ChangePasswordRouter>
//    lateinit var fidoRegistrationRouter: Definition<RetailFidoRegistrationRouter>
//    lateinit var deviceAuthorizationApprovalRouter: Definition<RetailDeviceAuthorizationApprovalRouter>
//    lateinit var outOfBandAuthenticationRouter: Definition<OutOfBandAuthenticationRouter>
//    lateinit var outOfBandTransactionSigningRouter: Definition<OutOfBandTransactionSigningRouter>
}

@Suppress("FunctionName") // factory function
@JvmSynthetic
internal fun AppIdentityAuthenticationConfiguration(): AuthenticationConfiguration =
    AuthenticationConfiguration.Builder().apply {
//        nguxConfiguration = NGUXConfiguration.Builder().apply {
//            // Uncomment to demo the next-gen UX
//            isWelcomeBackScreenEnabled = DeferredBoolean.Constant(true)
//        }.build()
//        welcomeBackScreenConfiguration = WelcomeBackScreenConfiguration.Builder().apply {
//            carouselViewProvider = CarouselBannerAuthenticationViewProvider()
//        }.build()
//        loginScreenConfiguration = LoginScreenConfiguration {
//            showUsersName = DeferredBoolean.Constant(true)
//        }
//        useSingleActivityApproach = DeferredBoolean.Constant(false)
//        enableQRCodeRegistration = DeferredBoolean.Constant(true)
    }.build()