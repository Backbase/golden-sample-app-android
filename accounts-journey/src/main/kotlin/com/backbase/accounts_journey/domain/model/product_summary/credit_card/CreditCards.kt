package com.backbase.accounts_journey.domain.model.product_summary.credit_card

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.product_summary.common.AggregatedBalance
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param products List of credit cards
 * @param name The label/name of the Credit card
 * @param aggregatedBalance The aggregated balance of this group of products.
 * @param additions Extra parameters
 */
@Poko
@Parcelize
class CreditCards private constructor(
    val products: List<CreditCard>,
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
         * See [CreditCards.products]
         */
        var products: List<CreditCard>? = null

        /**
         * See [CreditCards.name]
         */
        var name: String? = null

        /**
         * See [CreditCards.aggregatedBalance]
         */
        var aggregatedBalance: AggregatedBalance? = null

        /**
         * See [CreditCards.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [CreditCards.products]
         */
        fun setProducts(products: List<CreditCard>) = apply {
            this.products = products
        }

        /**
         * See [CreditCards.name]
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [CreditCards.aggregatedBalance]
         */
        fun setAggregatedBalance(aggregatedBalance: AggregatedBalance?) = apply {
            this.aggregatedBalance = aggregatedBalance
        }

        /**
         * See [CreditCards.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [CreditCards]
         */
        fun build() = CreditCards(
            requireNotNull(products),
            name,
            aggregatedBalance,
            additions
        )
    }
}

/**
 * DSL to create [CreditCards]
 */
fun CreditCards(block: CreditCards.Builder.() -> Unit) = CreditCards.Builder().apply(block).build()

internal fun CreditCards?.allProducts(): List<CreditCard> = this?.products ?: emptyList()
