package com.backbase.accounts_journey.domain.model.account

import com.backbase.accounts_journey.domain.model.account.current.CurrentAccounts
import com.backbase.accounts_journey.domain.model.account.savings.SavingsAccounts

class AccountSummary private constructor(
    //val customProducts: List<CustomProducts>,
    //val aggregatedBalance: AggregatedBalance?,
    val currentAccounts: CurrentAccounts?,
    val savingsAccounts: SavingsAccounts?,
    //val termDeposits: TermDeposits?,
    //val loans: Loans?,
    //val creditCards: CreditCards?,
    //val debitCards: DebitCards?,
    //val investmentAccounts: InvestmentAccounts?,
    //val additions: Map<String, String>?
) {

}