package com.backbase.accounts_journey.domain.model.account_summary.current_accounts

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.common.AggregatedBalance
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param products List of current accounts.
 * @param name The label/name that is used for the respective product kind.
 * @param aggregatedBalance The aggregated balance of this group of products.
 * @param additions Extra parameters
 */
@Poko
@Parcelize
class CurrentAccounts private constructor(
    val products: List<CurrentAccount>,
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
         * See [CurrentAccounts.products]
         */
        var products: List<CurrentAccount>? = null

        /**
         * See [CurrentAccounts.name]
         */
        var name: String? = null

        /**
         * See [CurrentAccounts.aggregatedBalance]
         */
        var aggregatedBalance: AggregatedBalance? = null

        /**
         * See [CurrentAccounts.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [CurrentAccounts.products]
         */
        fun setProducts(products: List<CurrentAccount>) = apply {
            this.products = products
        }

        /**
         * See [CurrentAccounts.name]
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [CurrentAccounts.aggregatedBalance]
         */
        fun setAggregatedBalance(aggregatedBalance: AggregatedBalance?) = apply {
            this.aggregatedBalance = aggregatedBalance
        }

        /**
         * See [CurrentAccounts.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [CurrentAccounts]
         */
        fun build() = CurrentAccounts(
            requireNotNull(products),
            name,
            aggregatedBalance,
            additions
        )
    }
}

/**
 * DSL to create [CurrentAccounts]
 */
fun CurrentAccounts(block: CurrentAccounts.Builder.() -> Unit) = CurrentAccounts.Builder().apply(block).build()

internal fun CurrentAccounts?.allProducts(): List<CurrentAccount> = this?.products ?: emptyList()
