package com.backbase.accounts_journey.configuration.accountlist

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.backbase.accounts_journey.R
import dev.drewhamilton.poko.Poko

/**
 * Configuration for account list screen.
 *
 * @param screenTitle Text to display on top of the account list screen. Defaults to "My Accounts".
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
@Suppress("LongParameterList")
@Poko
class AccountListScreenConfiguration private constructor(
    @StringRes val screenTitle: Int,
) {
    /**
     * A builder for this configuration class.
     */
    class Builder {

        @StringRes
        var screenTitle: Int = R.string.accounts_screen_title

        fun build() = AccountListScreenConfiguration(
            screenTitle = screenTitle,
        )
    }
}

/**
 * DSL function to create a [AccountListScreenConfiguration] in Kotlin.
 */
fun AccountListScreenConfiguration(
    initializer: AccountListScreenConfiguration.Builder.() -> Unit
): AccountListScreenConfiguration =
    AccountListScreenConfiguration.Builder().apply(initializer).build()
