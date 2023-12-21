package com.backbase.accounts_journey.configuration.icon

import androidx.annotation.DrawableRes
import com.backbase.accounts_journey.R
import dev.drewhamilton.poko.Poko

/**
 * Configuration for icons.
 *
 * @param iconCustomProduct Icon to display on custom products.
 * @param iconCurrentAccount Icon to display on current accounts.
 * @param iconSavingsAccount Icon to display on savings accounts.
 * @param iconTermDeposit Icon to display on term deposits.
 * @param iconLoan Icon to display on loans.
 * @param iconCreditCard Icon to display on credit cards.
 * @param iconDebitCard Icon to display on debit cards.
 * @param iconInvestmentAccount Icon to display on investment accounts.
 *
 * Created by Backbase R&D B.V on 16/11/2023.
 */
@Suppress("LongParameterList")
@Poko
class IconsConfiguration private constructor(
    @DrawableRes val iconCustomProduct: Int,
    @DrawableRes val iconCurrentAccount: Int,
    @DrawableRes val iconSavingsAccount: Int,
    @DrawableRes val iconTermDeposit: Int,
    @DrawableRes val iconLoan: Int,
    @DrawableRes val iconCreditCard: Int,
    @DrawableRes val iconDebitCard: Int,
    @DrawableRes val iconInvestmentAccount: Int,
) {
    class Builder {
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

        fun build() = IconsConfiguration(
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
 * DSL function to create a [IconsConfiguration] in Kotlin.
 */
fun IconsConfiguration(
    initializer: IconsConfiguration.Builder.() -> Unit
): IconsConfiguration = IconsConfiguration.Builder().apply(initializer).build()
