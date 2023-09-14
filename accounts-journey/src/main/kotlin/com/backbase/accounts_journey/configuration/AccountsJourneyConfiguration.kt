package com.backbase.accounts_journey.configuration

import com.backbase.accounts_journey.configuration.accountlist.AccountListScreenConfiguration
import dev.drewhamilton.poko.Poko

/**
 * A builder for this configuration class
 *
 * Should be directly used by Java consumers. Kotlin consumers should use DSL function
 */
@Poko
class AccountsJourneyConfiguration internal constructor(
    val accountListScreenConfiguration: AccountListScreenConfiguration,
) {
    class Builder {

        @set:JvmSynthetic
        var accountListScreenConfiguration = AccountListScreenConfiguration { }

        fun build() = AccountsJourneyConfiguration(
            accountListScreenConfiguration
        )
    }
}

fun AccountsJourneyConfiguration(
    block: AccountsJourneyConfiguration.Builder.() -> Unit
): AccountsJourneyConfiguration = AccountsJourneyConfiguration.Builder().apply(block).build()
