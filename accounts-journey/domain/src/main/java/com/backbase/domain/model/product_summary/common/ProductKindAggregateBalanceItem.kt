package com.backbase.domain.model.product_summary.common

import dev.drewhamilton.poko.Poko
import java.math.BigDecimal

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 * [ProductKindAggregateBalanceItem]  The aggregated balance of each product kind.
 *
 * @property amount  The amount in the specified currency, defaults to "null"
 * @property numberOfAccounts  Number of accounts that are aggregated for this currency, defaults to "null"
 * @property productKindName Name of the product kind, defaults to "null"
 */
@Poko
class ProductKindAggregateBalanceItem private constructor(
    val amount: BigDecimal?,
    val numberOfAccounts: Long?,
    val productKindName: String?,
) {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {

        /**
         * @see [ProductKindAggregateBalanceItem.amount]
         */
        var amount: BigDecimal? = null

        /**
         * @see [ProductKindAggregateBalanceItem.numberOfAccounts]
         */
        var numberOfAccounts: Long? = null

        /**
         * @see [ProductKindAggregateBalanceItem.productKindName]
         */
        var productKindName: String? = null

        /**
         * Builds an instance of [ProductKindAggregateBalanceItem]
         */
        fun build() = ProductKindAggregateBalanceItem(
            amount = amount,
            numberOfAccounts = numberOfAccounts,
            productKindName = productKindName
        )
    }
}

@Suppress("FunctionName") // DSL initializer
fun ProductKindAggregateBalanceItem(
    initializer: ProductKindAggregateBalanceItem.Builder.() -> Unit
) = ProductKindAggregateBalanceItem.Builder()
    .apply(initializer).build()
