package com.backbase.accounts_journey.domain.model.account

import com.backbase.accounts_journey.domain.model.account.current.CurrentAccounts
import com.backbase.accounts_journey.domain.model.account.savings.SavingsAccounts
import dev.drewhamilton.poko.Poko

@Poko
class AccountSummary private constructor(
    // val customProducts: List<CustomProducts>,
    // val aggregatedBalance: AggregatedBalance?,
    val currentAccounts: CurrentAccounts?,
    val savingsAccounts: SavingsAccounts?,
    // val termDeposits: TermDeposits?,
    // val loans: Loans?,
    // val creditCards: CreditCards?,
    // val debitCards: DebitCards?,
    // val investmentAccounts: InvestmentAccounts?,
    // val additions: Map<String, String>?
) {
    class Builder {

        @set:JvmSynthetic
        var currentAccounts: CurrentAccounts? = null

        @set:JvmSynthetic
        var savingsAccounts: SavingsAccounts? = null

        fun build() = AccountSummary(
            currentAccounts = currentAccounts,
            savingsAccounts = savingsAccounts
        )
    }
}

@JvmSynthetic
fun AccountSummary(block: AccountSummary.Builder.() -> Unit) =
    AccountSummary.Builder().apply(block).build()
