package com.backbase.golden_sample_app.koin

import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_use_case.AccountSummaryUseCaseImpl
import com.backbase.android.Backbase
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import org.koin.dsl.module

val accountsModule = module {
    single { Backbase.requireInstance().getClient(ProductSummaryApi::class.java) }
    factory<AccountsUseCase> { AccountSummaryUseCaseImpl(
        productSummaryApi = get(),
        dispatchers = get(),
    ) }
}
