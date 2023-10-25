package com.backbase.accounts_journey

import com.backbase.accounts_use_case.koin.dataModule
import com.backbase.configuration.AccountsJourneyConfiguration
import com.backbase.koin.presentationModule
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
        override: Boolean = false
    ) = module(override = override) {
        factory { configuration }

        loadKoinModules(
            listOf(
                dataModule,
                presentationModule,
            )
        )
    }
}
