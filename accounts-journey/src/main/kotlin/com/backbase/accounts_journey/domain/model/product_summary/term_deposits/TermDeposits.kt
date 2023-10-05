package com.backbase.accounts_journey.domain.model.product_summary.term_deposits

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.product_summary.common.AggregatedBalance
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param products The list of term deposit.
 * @param name The label/name for term deposits.
 * @param aggregatedBalance The aggregated balance of this group of products.
 * @param additions Extra parameters
 */
@Poko
@Parcelize
class TermDeposits private constructor(
    val products: List<TermDeposit>,
    val name: String?,
    val aggregatedBalance: AggregatedBalance?,
    val additions: Map<String, String>?
) : Parcelable {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {
        /**
         * See [TermDeposits.products]
         */
        var products: List<TermDeposit>? = null

        /**
         * See [TermDeposits.products]
         */
        var name: String? = null

        /**
         * See [TermDeposits.aggregatedBalance]
         */
        var aggregatedBalance: AggregatedBalance? = null

        /**
         * See [TermDeposits.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [TermDeposits.products]
         */
        fun setProducts(products: List<TermDeposit>) = apply {
            this.products = products
        }

        /**
         * See [TermDeposits.products]
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [TermDeposits.aggregatedBalance]
         */
        fun setAggregatedBalance(aggregatedBalance: AggregatedBalance?) = apply {
            this.aggregatedBalance = aggregatedBalance
        }

        /**
         * See [TermDeposits.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Build an instance of [TermDeposits]
         */
        fun build() = TermDeposits(
            requireNotNull(products),
            name,
            aggregatedBalance,
            additions
        )
    }
}

/**
 * DSL to create [TermDeposits]
 */
fun TermDeposits(block: TermDeposits.Builder.() -> Unit) = TermDeposits.Builder().apply(block).build()

internal fun TermDeposits?.allProducts(): List<TermDeposit> = this?.products ?: emptyList()
