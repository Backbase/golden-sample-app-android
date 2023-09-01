package com.backbase.accounts_journey

import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import org.koin.dsl.module

object AccountsJourney {

    @JvmOverloads
    fun create(
        routerName: String = ACCOUNTS_JOURNEY,
        configuration: AccountsJourneyConfiguration = AccountsJourneyConfiguration { }
    ) = module {
        // TODO: Setup dependencies
        // config module
        // router module
        // viewmodel module
        // usecase module
        // mapper module
    }

    /**
     * Default router name of the journey
     */
    const val ACCOUNTS_JOURNEY = "Default_Accounts_Journey"
}