package com.backbase.accounts_journey.presentation.mapper

import com.backbase.accounts_journey.domain.model.product_summary.AccountSummary
import com.backbase.accounts_journey.domain.model.product_summary.credit_card.CreditCards
import com.backbase.accounts_journey.domain.model.product_summary.current_accounts.CurrentAccounts
import com.backbase.accounts_journey.domain.model.product_summary.custom_products.CustomProducts
import com.backbase.accounts_journey.domain.model.product_summary.debit_card.DebitCards
import com.backbase.accounts_journey.domain.model.product_summary.investment_accounts.InvestmentAccounts
import com.backbase.accounts_journey.domain.model.product_summary.loan.Loans
import com.backbase.accounts_journey.domain.model.product_summary.savings_accounts.SavingsAccounts
import com.backbase.accounts_journey.domain.model.product_summary.term_deposits.TermDeposits
import com.backbase.accounts_journey.presentation.model.AccountSummaryUiModel
import com.backbase.accounts_journey.presentation.model.AccountUiModel
import com.backbase.accounts_journey.presentation.model.AccountsUiModel

internal fun AccountSummary.mapToUi(): AccountSummaryUiModel {
    return AccountSummaryUiModel(
        customProducts = this.customProducts.mapToUi(),
        currentAccounts = this.currentAccounts?.mapToUi(),
        savingAccounts = this.savingsAccounts?.mapToUi(),
        termDeposits = this.termDeposits?.mapToUi(),
        loans = this.loans?.mapToUi(),
        creditCards = this.creditCards?.mapToUi(),
        debitCards = this.debitCards?.mapToUi(),
        investmentAccounts = this.investmentAccounts?.mapToUi()
    )
}

internal fun List<CustomProducts>.mapToUi(): List<AccountsUiModel> {
    if (this.isEmpty()) return emptyList()

    return this.map { domain ->
        AccountsUiModel(
            name = domain.name!!,
            id = domain.id,
            products = domain.products.map { generalAccount ->
                AccountUiModel(
                    id = generalAccount.id!!,
                    name = generalAccount.displayName!!,
                    currency = generalAccount.currency!!,
                    balance = generalAccount.availableBalance!!,
                    state = generalAccount.state?.state,
                    isVisible = generalAccount.userPreferences!!.visible!!
                )
            }
        )
    }
}

internal fun CurrentAccounts.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        name = this.name!!,
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                currency = domain.currency!!,
                balance = domain.availableBalance!!,
                state = domain.state?.state,
                isVisible = domain.userPreferences!!.visible!!
            )
        }
    )
}

internal fun SavingsAccounts.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        name = this.name!!,
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                currency = domain.currency!!,
                balance = domain.availableBalance!!,
                state = domain.state?.state,
                isVisible = domain.userPreferences!!.visible!!
            )
        }
    )
}


internal fun TermDeposits.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        name = this.name!!,
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                currency = domain.currency!!,
                balance = domain.availableBalance!!,
                state = domain.state?.state,
                isVisible = domain.userPreferences!!.visible!!
            )
        }
    )
}

internal fun Loans.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        name = this.name!!,
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                currency = domain.currency!!,
                balance = domain.availableBalance!!,
                state = domain.state?.state,
                isVisible = domain.userPreferences!!.visible!!
            )
        }
    )
}

internal fun CreditCards.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        name = this.name!!,
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                currency = domain.currency!!,
                balance = domain.availableBalance!!,
                state = domain.state?.state,
                isVisible = domain.userPreferences!!.visible!!
            )
        }
    )
}

internal fun DebitCards.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        name = this.name!!,
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                currency = "",
                balance = "",
                state = domain.state?.state,
                isVisible = domain.userPreferences!!.visible!!
            )
        }
    )
}

internal fun InvestmentAccounts.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        name = this.name!!,
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                currency = domain.currency!!,
                balance = domain.currentInvestmentValue!!,
                state = domain.state?.state,
                isVisible = domain.userPreferences!!.visible!!
            )
        }
    )
}
