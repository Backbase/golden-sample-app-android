package com.backbase.golden_sample_app.koin

import com.backbase.accounts_journey.data.usecase.AccountDetailUseCase
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_use_case.AccountDetailUseCaseImpl
import com.backbase.accounts_use_case.AccountSummaryUseCaseImpl
import com.backbase.android.Backbase
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import org.koin.dsl.module

/**
 * Dependency setup for accounts API.
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
val accountsModule = module {
    val backbase = Backbase.requireInstance()
    single { backbase.getClient(ProductSummaryApi::class.java) }
    single { backbase.getClient(ArrangementsApi::class.java) }
    factory<AccountsUseCase> {
        AccountSummaryUseCaseImpl(productSummaryApi = get())
    }
    factory<AccountDetailUseCase> {
        AccountDetailUseCaseImpl(arrangementsApi = get())
    }
}
