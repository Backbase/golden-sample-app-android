package com.backbase.golden_sample_app.journey.accounts

import android.app.Application
import com.backbase.accounts_journey.data.usecase.AccountDetailUseCase
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_use_case.AccountDetailUseCaseImpl
import com.backbase.accounts_use_case.AccountSummaryUseCaseImpl
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.app_common.apiRoot
import org.koin.dsl.module
import java.net.URI

/**
 * Dependency setup for accounts API.
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
internal fun accountsModule() = module {
    single {
        ProductSummaryApi(
            context = get<Application>(),
            moshi = get(),
            parser = get(),
            serverUri = URI("${apiRoot()}/arrangement-manager"),
            backbase = get()
        )
    }

    single {
        ArrangementsApi(
            context = get<Application>(),
            moshi = get(),
            parser = get(),
            serverUri = URI("${apiRoot()}/arrangement-manager"),
            backbase = get()
        )
    }

    factory<AccountsUseCase> {
        AccountSummaryUseCaseImpl(productSummaryApi = get())
    }
    factory<AccountDetailUseCase> {
        AccountDetailUseCaseImpl(arrangementsApi = get())
    }
}
