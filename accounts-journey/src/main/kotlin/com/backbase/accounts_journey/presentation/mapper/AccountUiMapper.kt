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
import com.backbase.accounts_journey.presentation.model.AccountHeaderUiModel
import com.backbase.accounts_journey.presentation.model.AccountSummaryUiModel
import com.backbase.accounts_journey.presentation.model.AccountUiModel
import com.backbase.accounts_journey.presentation.model.AccountsUiModel
import com.backbase.android.design.amount.AmountFormat

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

    return this.map { customProducts ->
        AccountsUiModel(
            AccountHeaderUiModel(name = customProducts.name!!),
            products = customProducts.products.map { domain ->
                AccountUiModel(
                    id = domain.id!!,
                    name = domain.displayName!!,
                    balance = formatCurrency(domain.currency!!, domain.availableBalance!!),
                    state = formatState(domain.state?.state, domain.BBAN),
                    isVisible = domain.userPreferences?.visible
                )
            }
        )
    }
}

internal fun CurrentAccounts.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        AccountHeaderUiModel(name = this.name!!),
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                balance = formatCurrency(domain.currency!!, domain.availableBalance!!),
                state = formatState(domain.state?.state, domain.BBAN),
                isVisible = domain.userPreferences?.visible
            )
        }
    )
}

internal fun SavingsAccounts.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        AccountHeaderUiModel(name = this.name!!),
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                balance = formatCurrency(domain.currency!!, domain.availableBalance!!),
                state = formatState(domain.state?.state, domain.BBAN),
                isVisible = domain.userPreferences?.visible
            )
        }
    )
}


internal fun TermDeposits.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        AccountHeaderUiModel(name = this.name!!),
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                balance = formatCurrency(domain.currency!!, domain.availableBalance!!),
                state = formatState(domain.state?.state, domain.BBAN),
                isVisible = domain.userPreferences?.visible
            )
        }
    )
}

internal fun Loans.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        AccountHeaderUiModel(name = this.name!!),
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                balance = formatCurrency(domain.currency!!, domain.availableBalance!!),
                state = formatState(domain.state?.state, domain.BBAN),
                isVisible = domain.userPreferences?.visible
            )
        }
    )
}

internal fun CreditCards.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        AccountHeaderUiModel(name = this.name!!),
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                balance = formatCurrency(domain.currency!!, domain.availableBalance!!),
                state = formatState(domain.state?.state, domain.creditCardAccountNumber),
                isVisible = domain.userPreferences?.visible
            )
        }
    )
}

internal fun DebitCards.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        AccountHeaderUiModel(name = this.name!!),
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                balance = "",
                state = formatState(domain.state?.state, domain.cardNumber.toString()),
                isVisible = domain.userPreferences?.visible
            )
        }
    )
}

internal fun InvestmentAccounts.mapToUi(): AccountsUiModel? {
    if (this.products.isEmpty()) return null

    return AccountsUiModel(
        AccountHeaderUiModel(name = this.name!!),
        products = this.products.map { domain ->
            AccountUiModel(
                id = domain.id!!,
                name = domain.displayName!!,
                balance = formatCurrency(domain.currency!!, domain.currentInvestmentValue!!),
                state = formatState(domain.state?.state, domain.BBAN),
                isVisible = domain.userPreferences?.visible
            )
        }
    )
}

private fun formatCurrency(currency: String, amount: String): String {
    return AmountFormat().apply {
        enableAbbreviation = true
        currencyCode = currency
    }.format(amount.toBigDecimal())
}

private fun formatState(state: String?, number: String?): String? {
    if (state == null || number == null) return null
    return if (state == "Active") number
    else state
}
