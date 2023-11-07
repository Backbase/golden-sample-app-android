package com.backbase.accounts_use_case.koin

import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_use_case.AccountSummaryUseCaseImpl
import com.backbase.accounts_use_case.service.ArrangementManagerService
import com.backbase.network.HttpClientFactoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val accountsUseCaseModule = module {
    single {
        HttpClientFactoryImpl().createRetrofit(
            baseUrl = "https://app.dev.sdbxaz.azure.backbaseservices.com",
            okHttpClient = get(),
            converter = get()
        )
    }
    factory<AccountsUseCase> {
        AccountSummaryUseCaseImpl(
            arrangementManagerService = provideArrangementManagerService(get())
        )
    }
}

fun provideArrangementManagerService(retrofit: Retrofit): ArrangementManagerService {
    return retrofit.create(ArrangementManagerService::class.java)
}
