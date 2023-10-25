package com.backbase.golden_sample_app.koin

import com.backbase.accounts_use_case.AccountSummaryUseCaseImpl
import com.backbase.android.Backbase
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.domain.data.AccountsUseCase
import org.koin.dsl.module

/**
 * Dependency setup for accounts API.
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
val accountsModule = module {
    single { Backbase.requireInstance().getClient(ProductSummaryApi::class.java) }
    factory<AccountsUseCase> {
        AccountSummaryUseCaseImpl(
            productSummaryApi = get()
        )
    }
}
