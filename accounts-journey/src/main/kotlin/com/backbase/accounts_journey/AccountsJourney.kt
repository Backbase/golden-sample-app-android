package com.backbase.accounts_journey

import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.koin.commonModule
import com.backbase.accounts_journey.koin.viewModelModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AccountsJourney {

    @JvmOverloads
    fun create(
        routerName: String = ACCOUNTS_JOURNEY,
        configuration: AccountsJourneyConfiguration = AccountsJourneyConfiguration { },
        override: Boolean = false
    ) = module(override = override) {
        // TODO: Setup dependencies
        // config module
        // router module
        // viewmodel module
        // usecase module
        // mapper module
        loadKoinModules(
            listOf(
                commonModule,
                viewModelModule,
            )
        )
    }

    /**
     * Default router name of the journey
     */
    const val ACCOUNTS_JOURNEY = "Default_Accounts_Journey"
}
