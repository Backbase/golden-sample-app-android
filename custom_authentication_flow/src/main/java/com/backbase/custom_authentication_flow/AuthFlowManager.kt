package com.backbase.custom_authentication_flow

import android.app.Application
import com.backbase.android.Backbase
import com.backbase.android.core.networking.error.BBChainErrorResponseResolver
import com.backbase.android.identity.client.BBIdentityAuthClient
import com.backbase.android.identity.journey.authentication.use_case.api.AuthenticationFlowsUseCase
import com.backbase.custom_authentication_flow.core.util.AuthFlowUseCaseManager
import com.backbase.custom_authentication_flow.otp_verify.challenge.OtpInputResolver
import com.backbase.custom_authentication_flow.terms_and_conditions.challenge.TermsAndConditionsResolver
import org.koin.core.context.GlobalContext
import java.net.HttpURLConnection

internal var isCustomRouterInitialized = false
    private set

fun initCustomAuthenticator() {
    if (!isCustomRouterInitialized) {
        isCustomRouterInitialized = true

        val authUseCase =
            GlobalContext.get().get<AuthenticationFlowsUseCase>().authenticationUseCase
        val allRoutersUseCase = GlobalContext.get().get<AuthFlowUseCaseManager>()

        runCatching {
            with(authUseCase) {
                removeRouter(allRoutersUseCase.tncDpaRouterUseCase.javaClass)
                addRouter(allRoutersUseCase.tncDpaRouterUseCase)

                removeRouter(allRoutersUseCase.otpInputRouterUseCase.javaClass)
                addRouter(allRoutersUseCase.otpInputRouterUseCase)
            }
        }

        initErrorResolvers()
    }
}

private fun initErrorResolvers() {
    val backbase = GlobalContext.get().get<Backbase>()
    val routerProvider = GlobalContext.get().get<BBIdentityAuthClient>()
    val context = GlobalContext.get().get<Application>()

    val authErrorResponseResolvers =
        backbase.getResolverByCode(HttpURLConnection.HTTP_UNAUTHORIZED)
    backbase.registerErrorResponseResolver(
        HttpURLConnection.HTTP_UNAUTHORIZED,
        BBChainErrorResponseResolver(
            OtpInputResolver(context, routerProvider),
            TermsAndConditionsResolver(context, routerProvider),
            authErrorResponseResolvers
        )
    )
}