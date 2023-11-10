package com.backbase.domain.model.product_summary.debit_card

import com.backbase.domain.model.product_summary.common.AggregatedBalance
import dev.drewhamilton.poko.Poko

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param products The list of debit cards.
 * @param name The label/name of the debit card.
 * @param displayName The label/name of the debit card used to display on the screen.
 * @param aggregatedBalance The aggregated balance of this group of products.
 * @param additions Extra parameters
 */
@Poko
class DebitCards private constructor(
    val products: List<DebitCard>,
    val name: String?,
    val displayName: String?,
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
         * See [DebitCards.products]
         */
        var products: List<DebitCard>? = null

        /**
         * See [DebitCards.name]
         */
        var name: String? = null

        /**
         * See [DebitCards.displayName]
         */
        var displayName: String? = null

        /**
         * See [DebitCards.aggregatedBalance]
         */
        var aggregatedBalance: AggregatedBalance? = null

        /**
         * See [DebitCards.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [DebitCards.products]
         */
        fun setProducts(products: List<DebitCard>) = apply {
            this.products = products
        }

        /**
         * See [DebitCards.name]
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [DebitCards.displayName]
         */
        fun setDisplayName(displayName: String?) = apply {
            this.displayName = displayName
        }

        /**
         * See [DebitCards.aggregatedBalance]
         */
        fun setAggregatedBalance(aggregatedBalance: AggregatedBalance?) = apply {
            this.aggregatedBalance = aggregatedBalance
        }

        /**
         * See [DebitCards.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [DebitCards]
         */
        fun build() = DebitCards(
            requireNotNull(products),
            name,
            displayName,
            aggregatedBalance,
            additions
        )
    }
}

/**
 * DSL to create [DebitCards]
 */
fun DebitCards(block: DebitCards.Builder.() -> Unit) = DebitCards.Builder().apply(block).build()

internal fun DebitCards?.allProducts(): List<DebitCard> = this?.products ?: emptyList()
