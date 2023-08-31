package com.backbase.accounts_journey

import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration

object AccountsJourney {

    @JvmOverloads
    fun create(
        routerName: String = ACCOUNTS_JOURNEY,
        configuration: AccountsJourneyConfiguration = AccountsJourneyConfiguration { }
    ) {
        // TODO: Setup dependencies
    }

    /**
     * Default router name of the journey
     */
    const val ACCOUNTS_JOURNEY = "Default_Accounts_Journey"
}