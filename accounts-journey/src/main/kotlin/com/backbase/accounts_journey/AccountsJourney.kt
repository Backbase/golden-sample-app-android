package com.backbase.accounts_journey

import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.koin.mapperModule
import com.backbase.accounts_journey.koin.viewModelModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Wire up dependencies for Accounts Journey.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
object AccountsJourney {

    fun create(
        configuration: AccountsJourneyConfiguration = AccountsJourneyConfiguration { }
    ) = module {
        factory { configuration }

        loadKoinModules(
            listOf(
                viewModelModule,
                mapperModule,
            )
        )
    }
}
