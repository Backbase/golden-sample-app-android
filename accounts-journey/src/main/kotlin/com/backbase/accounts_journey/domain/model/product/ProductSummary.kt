package com.backbase.accounts_journey.domain.model.product

import com.backbase.accounts_journey.domain.model.account.current.CurrentAccounts
import com.backbase.accounts_journey.domain.model.account.savings.SavingsAccounts

class ProductSummary private constructor(
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