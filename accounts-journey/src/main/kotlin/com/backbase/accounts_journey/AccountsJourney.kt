package com.backbase.accounts_journey

import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.koin.mapperModule
import com.backbase.accounts_journey.koin.viewModelModule
import com.backbase.accounts_journey.routing.AccountsRouting
import com.backbase.accounts_journey.routing.AccountsRoutingImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Wire up dependencies for Accounts Journey.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
object AccountsJourney {

    fun create(
        configuration: AccountsJourneyConfiguration = AccountsJourneyConfiguration { },
        accountsRouting: AccountsRouting = AccountsRoutingImpl()
    ) = module {
        factory { configuration }
        single<AccountsRouting> { accountsRouting }
        loadKoinModules(
            listOf(
                viewModelModule,
                mapperModule,
            )
        )
    }
}
