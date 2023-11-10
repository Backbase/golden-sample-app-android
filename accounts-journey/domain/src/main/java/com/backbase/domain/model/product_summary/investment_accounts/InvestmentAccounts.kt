package com.backbase.domain.model.product_summary.investment_accounts

import com.backbase.domain.model.product_summary.common.AggregatedBalance
import dev.drewhamilton.poko.Poko

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param products The list of investments.
 * @param name The label/name of an investment account.
 * @param aggregatedBalance The aggregated balance of this group of products.
 * @param additions Extra parameters
 */
@Poko
class InvestmentAccounts private constructor(
    val products: List<InvestmentAccount>,
    val name: String?,
    val aggregatedBalance: AggregatedBalance?,
    val additions: Map<String, String>?
) {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {
        /**
         * See [InvestmentAccounts.products]
         */
        var products: List<InvestmentAccount>? = null

        /**
         * See [InvestmentAccounts.name]
         */
        var name: String? = null

        /**
         * See [InvestmentAccounts.aggregatedBalance]
         */
        var aggregatedBalance: AggregatedBalance? = null

        /**
         * See [InvestmentAccounts.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [InvestmentAccounts.products]
         */
        fun setProducts(products: List<InvestmentAccount>) = apply {
            this.products = products
        }

        /**
         * See [InvestmentAccounts.name]
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [InvestmentAccounts.aggregatedBalance]
         */
        fun setAggregatedBalance(aggregatedBalance: AggregatedBalance?) = apply {
            this.aggregatedBalance = aggregatedBalance
        }

        /**
         * See [InvestmentAccounts.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [InvestmentAccounts]
         */
        fun build() = InvestmentAccounts(
            requireNotNull(products),
            name,
            aggregatedBalance,
            additions
        )
    }
}

/**
 * DSL to create [InvestmentAccounts]
 */
fun InvestmentAccounts(block: InvestmentAccounts.Builder.() -> Unit) = InvestmentAccounts.Builder().apply(block).build()

internal fun InvestmentAccounts?.allProducts(): List<InvestmentAccount> = this?.products ?: emptyList()
