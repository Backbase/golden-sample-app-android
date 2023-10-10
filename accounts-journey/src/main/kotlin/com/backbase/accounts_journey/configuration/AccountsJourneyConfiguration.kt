package com.backbase.accounts_journey.configuration

import com.backbase.accounts_journey.configuration.accountlist.AccountListScreenConfiguration
import dev.drewhamilton.poko.Poko

/**
 * Configuration for Accounts Journey.
 *
 * @param accountListScreenConfiguration Configuration for account list screen.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
@Poko
class AccountsJourneyConfiguration internal constructor(
    val accountListScreenConfiguration: AccountListScreenConfiguration,
) {
    class Builder {

        var accountListScreenConfiguration = AccountListScreenConfiguration { }

        fun build() = AccountsJourneyConfiguration(
            accountListScreenConfiguration
        )
    }
}

fun AccountsJourneyConfiguration(
    block: AccountsJourneyConfiguration.Builder.() -> Unit
): AccountsJourneyConfiguration = AccountsJourneyConfiguration.Builder().apply(block).build()
