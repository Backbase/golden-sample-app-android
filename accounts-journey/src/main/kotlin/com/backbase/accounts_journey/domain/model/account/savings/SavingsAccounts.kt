package com.backbase.accounts_journey.domain.model.account.savings

import dev.drewhamilton.poko.Poko

@Poko
class SavingsAccounts private constructor(
    val products: List<SavingsAccount>,
    val name: String?,
//    val aggregatedBalance: AggregatedBalance?,
    val additions: Map<String, String>?
) {
}