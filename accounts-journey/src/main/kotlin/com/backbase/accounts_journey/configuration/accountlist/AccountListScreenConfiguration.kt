package com.backbase.accounts_journey.configuration.accountlist

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.backbase.accounts_journey.R
import dev.drewhamilton.poko.Poko

/**
 * Configuration for account list screen.
 *
 * @param screenTitle Text to display on top of the account list screen. Defaults to "My Accounts".
 * @param iconCustomProduct Icon to display on custom products.
 * @param iconCurrentAccount Icon to display on current accounts.
 * @param iconSavingsAccount Icon to display on savings accounts.
 * @param iconTermDeposit Icon to display on term deposits.
 * @param iconLoan Icon to display on loans.
 * @param iconCreditCard Icon to display on credit cards.
 * @param iconDebitCard Icon to display on debit cards.
 * @param iconInvestmentAccount Icon to display on investment accounts.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
@Suppress("LongParameterList")
@Poko
class AccountListScreenConfiguration private constructor(
    @StringRes val screenTitle: Int,
    @DrawableRes val iconCustomProduct: Int,
    @DrawableRes val iconCurrentAccount: Int,
    @DrawableRes val iconSavingsAccount: Int,
    @DrawableRes val iconTermDeposit: Int,
    @DrawableRes val iconLoan: Int,
    @DrawableRes val iconCreditCard: Int,
    @DrawableRes val iconDebitCard: Int,
    @DrawableRes val iconInvestmentAccount: Int,
) {
    /**
     * A builder for this configuration class.
     */
    class Builder {

        @StringRes
        var screenTitle: Int = R.string.accounts_screen_title

        @DrawableRes
        var iconCustomProduct: Int = R.drawable.ic_account_type_card_v2

        @DrawableRes
        var iconCurrentAccount: Int = R.drawable.ic_account_type_current_v2

        @DrawableRes
        var iconSavingsAccount: Int = R.drawable.ic_account_type_savings_term_deposits_v2

        @DrawableRes
        var iconTermDeposit: Int = R.drawable.ic_account_type_savings_term_deposits_v2

        @DrawableRes
        var iconLoan: Int = R.drawable.ic_account_type_loan_v2

        @DrawableRes
        var iconCreditCard: Int = R.drawable.ic_account_type_credit_debit_card_v2

        @DrawableRes
        var iconDebitCard: Int = R.drawable.ic_account_type_credit_debit_card_v2

        @DrawableRes
        var iconInvestmentAccount: Int = R.drawable.ic_account_type_investment_v2

        fun build() = AccountListScreenConfiguration(
            screenTitle = screenTitle,
            iconCustomProduct = iconCustomProduct,
            iconCurrentAccount = iconCurrentAccount,
            iconSavingsAccount = iconSavingsAccount,
            iconTermDeposit = iconTermDeposit,
            iconLoan = iconLoan,
            iconCreditCard = iconCreditCard,
            iconDebitCard = iconDebitCard,
            iconInvestmentAccount = iconInvestmentAccount
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
