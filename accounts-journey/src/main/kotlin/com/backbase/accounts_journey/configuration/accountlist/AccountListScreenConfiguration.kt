package com.backbase.accounts_journey.configuration.accountlist

import com.backbase.accounts_journey.R
import com.backbase.deferredresources.DeferredText
import dev.drewhamilton.poko.Poko

/**
 * @param screenTitle Text to display on top of the account list screen. Defaults to "My Accounts".
 * @param currentAccountTitle Section title to show on top of current accounts.
 * @param savingsAccountTitle Section title to show on top of saving accounts.
 */
@Poko
class AccountListScreenConfiguration private constructor(
    val screenTitle: DeferredText,
    val currentAccountTitle: DeferredText,
    val savingsAccountTitle: DeferredText,
) {
    /**
     * A builder for this configuration class.
     *
     * Should be used directly by Java callers. Kotlin callers should use the DSL function instead.
     */
    class Builder {

        @set:JvmSynthetic
        var screenTitle: DeferredText =
            DeferredText.Resource(R.string.accountsAndTransactions_accounts_labels_title)

        @set:JvmSynthetic
        var currentAccountTitle: DeferredText =
            DeferredText.Resource(R.string.accountsAndTransactions_accounts_labels_section_title_currentAccounts)

        @set:JvmSynthetic
        var savingsAccountTitle: DeferredText =
            DeferredText.Resource(R.string.accountsAndTransactions_accounts_labels_section_title_savingsAccounts)

        fun build() = AccountListScreenConfiguration(
            screenTitle = screenTitle,
            currentAccountTitle = currentAccountTitle,
            savingsAccountTitle = savingsAccountTitle,
        )
    }
}

/**
 * DSL function to create a [AccountListScreenConfiguration] in Kotlin.
 */
@JvmSynthetic
fun AccountListScreenConfiguration(
    initializer: AccountListScreenConfiguration.Builder.() -> Unit
): AccountListScreenConfiguration =
    AccountListScreenConfiguration.Builder().apply(initializer).build()
