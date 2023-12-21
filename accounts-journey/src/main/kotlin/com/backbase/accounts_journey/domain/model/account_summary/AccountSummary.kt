package com.backbase.accounts_journey.domain.model.account_summary

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.account_summary.credit_card.CreditCards
import com.backbase.accounts_journey.domain.model.account_summary.current_accounts.CurrentAccounts
import com.backbase.accounts_journey.domain.model.account_summary.custom_products.CustomProducts
import com.backbase.accounts_journey.domain.model.account_summary.debit_card.DebitCards
import com.backbase.accounts_journey.domain.model.account_summary.investment_accounts.InvestmentAccounts
import com.backbase.accounts_journey.domain.model.account_summary.loan.Loans
import com.backbase.accounts_journey.domain.model.account_summary.savings_accounts.SavingsAccounts
import com.backbase.accounts_journey.domain.model.account_summary.term_deposits.TermDeposits
import com.backbase.accounts_journey.domain.model.common.AggregatedBalance
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param customProducts The list of Custom products.
 * @param aggregatedBalance The aggregated balance.
 * @param currentAccounts The current accounts.
 * @param savingsAccounts The saving accounts.
 * @param termDeposits The term deposits.
 * @param loans The loans.
 * @param creditCards The credit cards.
 * @param debitCards The debit cards.
 * @param investmentAccounts The investments accounts.
 * @param additions The additions.
 */
@Suppress("LongParameterList")
@Poko
@Parcelize
class AccountSummary private constructor(
    val customProducts: List<CustomProducts>,
    val aggregatedBalance: AggregatedBalance?,
    val currentAccounts: CurrentAccounts?,
    val savingsAccounts: SavingsAccounts?,
    val termDeposits: TermDeposits?,
    val loans: Loans?,
    val creditCards: CreditCards?,
    val debitCards: DebitCards?,
    val investmentAccounts: InvestmentAccounts?,
    val additions: Map<String, String>?
) : Parcelable {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {
        /**
         * See [AccountSummary.customProducts]
         */
        var customProducts: List<CustomProducts> = emptyList()

        /**
         * See [AccountSummary.aggregatedBalance]
         */
        var aggregatedBalance: AggregatedBalance? = null

        /**
         * See [AccountSummary.currentAccounts]
         */
        var currentAccounts: CurrentAccounts? = null

        /**
         * See [AccountSummary.savingsAccounts]
         */
        var savingsAccounts: SavingsAccounts? = null

        /**
         * See [AccountSummary.termDeposits]
         */
        var termDeposits: TermDeposits? = null

        /**
         * See [AccountSummary.loans]
         */
        var loans: Loans? = null

        /**
         * See [AccountSummary.creditCards]
         */
        var creditCards: CreditCards? = null

        /**
         * See [AccountSummary.debitCards]
         */
        var debitCards: DebitCards? = null

        /**
         * See [AccountSummary.investmentAccounts]
         */
        var investmentAccounts: InvestmentAccounts? = null

        /**
         * See [AccountSummary.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [AccountSummary.customProducts]
         */
        fun setCustoms(customs: List<CustomProducts>) = apply {
            this.customProducts = customs
        }

        /**
         * See [AccountSummary.aggregatedBalance]
         */
        fun setAggregatedBalance(aggregatedBalance: AggregatedBalance?) = apply {
            this.aggregatedBalance = aggregatedBalance
        }

        /**
         * See [AccountSummary.currentAccounts]
         */
        fun setCurrentAccounts(currentAccounts: CurrentAccounts?) = apply {
            this.currentAccounts = currentAccounts
        }

        /**
         * See [AccountSummary.savingsAccounts]
         */
        fun setSavingsAccounts(savingsAccounts: SavingsAccounts?) = apply {
            this.savingsAccounts = savingsAccounts
        }

        /**
         * See [AccountSummary.termDeposits]
         */
        fun setTermDeposits(termDeposits: TermDeposits?) = apply {
            this.termDeposits = termDeposits
        }

        /**
         * See [AccountSummary.loans]
         */
        fun setLoans(loans: Loans?) = apply {
            this.loans = loans
        }

        /**
         * See [AccountSummary.creditCards]
         */
        fun setCreditCards(creditCards: CreditCards?) = apply {
            this.creditCards = creditCards
        }

        /**
         * See [AccountSummary.debitCards]
         */
        fun setDebitCards(debitCards: DebitCards?) = apply {
            this.debitCards = debitCards
        }

        /**
         * See [AccountSummary.investmentAccounts]
         */
        fun setInvestmentAccounts(investmentAccounts: InvestmentAccounts?) = apply {
            this.investmentAccounts = investmentAccounts
        }

        /**
         * See [AccountSummary.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Build an instance of [AccountSummary]
         */
        fun build() = AccountSummary(
            customProducts,
            aggregatedBalance,
            currentAccounts,
            savingsAccounts,
            termDeposits,
            loans,
            creditCards,
            debitCards,
            investmentAccounts,
            additions
        )
    }
}

/**
 * DSL to create [AccountSummary]
 */
fun AccountSummary(block: AccountSummary.Builder.() -> Unit) = AccountSummary.Builder().apply(block).build()
