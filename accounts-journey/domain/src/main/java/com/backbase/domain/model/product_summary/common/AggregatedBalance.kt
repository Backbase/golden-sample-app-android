package com.backbase.domain.model.product_summary.common

import dev.drewhamilton.poko.Poko

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * Defines a balance for groups of products. This field is not implemented OOTB, and it should be implemented by project/customer.
 *
 * @property currency A code/text that represents a currency, defaults to "null"
 * @property value The absolute value of the balance, defaults to "null".
 * @property productKindAggregateBalanceItem The aggregated balance of each product kind, defaults to "null".
 * @property additions Extra information, defaults to "null".
 */
@Poko
class AggregatedBalance internal constructor(
    val currency: String?,
    val value: String?,
    val productKindAggregateBalanceItem: List<ProductKindAggregateBalanceItem>?,
    val additions: Map<String, String>?
)  {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {
        /**
         * See [AggregatedBalance.currency]
         */
        var currency: String? = null

        /**
         * See [AggregatedBalance.value]
         */
        var value: String? = null

        /**
         * See [AggregatedBalance.productKindAggregateBalanceItem]
         */
        var productKindAggregateBalanceItem: List<ProductKindAggregateBalanceItem>? = null

        /**
         * See [AggregatedBalance.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [AggregatedBalance.currency]
         */
        fun setCurrency(currency: String?) = apply {
            this.currency = currency
        }

        /**
         * See [AggregatedBalance.value]
         */
        fun setValue(value: String?) = apply {
            this.value = value
        }

        /**
         * See [AggregatedBalance.productKindAggregateBalanceItem]
         */
        fun setProductKindAggregateBalanceItem(
            productKindAggregateBalanceItem: List<ProductKindAggregateBalanceItem>?
        ) =
            apply {
                this.productKindAggregateBalanceItem = productKindAggregateBalanceItem
            }

        /**
         * See [AggregatedBalance.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [AggregatedBalance]
         */
        fun build() = AggregatedBalance(
            currency,
            value,
            productKindAggregateBalanceItem,
            additions
        )
    }
}

/**
 * DSL to create [AggregatedBalance]
 */
fun AggregatedBalance(block: AggregatedBalance.Builder.() -> Unit) = AggregatedBalance.Builder().apply(block).build()
