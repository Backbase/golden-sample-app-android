package com.backbase.accounts_journey.configuration

import com.backbase.accounts_journey.configuration.accountlist.AccountListScreenConfiguration
import com.backbase.accounts_journey.configuration.icon.IconsConfiguration
import dev.drewhamilton.poko.Poko

/**
 * Configuration for Accounts Journey.
 *
 * @param accountListScreenConfiguration Configuration for account list screen.
 * @param iconsConfiguration Configuration for icons.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
@Poko
class AccountsJourneyConfiguration internal constructor(
    val accountListScreenConfiguration: AccountListScreenConfiguration,
    val iconsConfiguration: IconsConfiguration,
) {
    class Builder {

        var accountListScreenConfiguration = AccountListScreenConfiguration { }
        var iconsConfiguration = IconsConfiguration { }

        fun build() = AccountsJourneyConfiguration(
            accountListScreenConfiguration,
            iconsConfiguration
        )
    }
}

fun AccountsJourneyConfiguration(
    block: AccountsJourneyConfiguration.Builder.() -> Unit
): AccountsJourneyConfiguration = AccountsJourneyConfiguration.Builder().apply(block).build()
