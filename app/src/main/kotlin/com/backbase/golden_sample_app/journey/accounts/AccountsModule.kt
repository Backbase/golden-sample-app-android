package com.backbase.golden_sample_app.journey.accounts

import android.app.Application
import com.backbase.accounts_journey.AccountsJourney
import com.backbase.accounts_journey.routing.AccountsRoutingImpl
import com.backbase.accounts_journey.data.usecase.AccountDetailUseCaseImpl
import com.backbase.accounts_journey.data.usecase.AccountSummaryUseCaseImpl
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.app_common.accounts.accountsModule
import com.backbase.app_common.apiRoot
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin
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

    accountsModule {
        accountsRouting = {
            AccountsRoutingImpl()
        }
        accountSummaryUseCase = {
            AccountSummaryUseCaseImpl(productSummaryApi = get())
        }
        accountDetailUseCase = {
            AccountDetailUseCaseImpl(arrangementsApi = get())
        }
    }
}

internal fun injectAccountsJourney() {
    loadKoinModules(
        AccountsJourney.create(getKoin().get())
    )
}
