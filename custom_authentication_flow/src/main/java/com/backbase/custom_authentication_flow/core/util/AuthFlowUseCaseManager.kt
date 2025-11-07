package com.backbase.custom_authentication_flow.core.util

import com.backbase.custom_authentication_flow.data.CustomIdentityApi
import com.backbase.custom_authentication_flow.otp_verify.use_case.OtpInputRouterUseCase
import com.backbase.custom_authentication_flow.terms_and_conditions.use_case.TermsAndConditionUseCase

internal class AuthFlowUseCaseManager(
    private val customNavigationEmitter: CustomNavigationEmitter,
    private val customIdentityApi: CustomIdentityApi
) {

    val tncDpaRouterUseCase: TermsAndConditionUseCase by lazy {
        TermsAndConditionUseCase(customIdentityApi, customNavigationEmitter)
    }

    val otpInputRouterUseCase: OtpInputRouterUseCase by lazy {
        OtpInputRouterUseCase(customIdentityApi, customNavigationEmitter)
    }
}