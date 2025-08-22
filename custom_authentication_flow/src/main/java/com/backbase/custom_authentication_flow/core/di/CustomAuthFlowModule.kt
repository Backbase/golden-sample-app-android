package com.backbase.custom_authentication_flow.core.di

import com.backbase.android.Backbase
import com.backbase.custom_authentication_flow.core.util.CustomNavigationEmitter
import com.backbase.custom_authentication_flow.core.util.AuthFlowUseCaseManager
import com.backbase.custom_authentication_flow.data.CustomIdentityApi
import com.backbase.custom_authentication_flow.terms_and_conditions.presentation.TermsAndConditionsViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.net.URI

fun customAuthFlowModule() = module {
    single {
        val identityUrl =
            Backbase.requireInstance().configuration.experienceConfiguration.identityConfig.baseURL
        CustomIdentityApi(
            gson = Gson(),
            parser = get(),
            serverUri = URI(identityUrl),
            backbase = get(),
            provider = get()
        )
    }

    single { CustomNavigationEmitter() }
    single { AuthFlowUseCaseManager(get(), get()) }

    viewModel { TermsAndConditionsViewModel(get<AuthFlowUseCaseManager>().tncDpaRouterUseCase, get(), get()) }
}