package com.backbase.accounts_journey.domain.model.account.current

import dev.drewhamilton.poko.Poko

@Poko
class CurrentAccounts private constructor(
    val products: List<CurrentAccount>,
    val name: String?,
//    val aggregatedBalance: AggregatedBalance?,
    val additions: Map<String, String>?
) {
}