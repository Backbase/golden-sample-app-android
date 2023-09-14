package com.backbase.accounts_journey.domain.model.account.current

import dev.drewhamilton.poko.Poko

/**
 *
 *
 * @param products List of current accounts.
 * @param name The label/name that is used for the respective product kind.
 * @param aggregatedBalance The aggregated balance of this group of products.
 * @param additions Extra parameters
 */
@Poko
class CurrentAccount private constructor(
    val products: List<CurrentAccount>,
    val name: String?,
//    val aggregatedBalance: AggregatedBalance?,
    val additions: Map<String, String>?
)
