package com.backbase.accounts_journey.domain.model.account_summary.savings_accounts

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.common.AggregatedBalance
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param products List of savings accounts
 * @param name the label/name of the saving account
 * @param aggregatedBalance The aggregated balance of this group of products.
 * @param additions Extra parameters
 */
@Poko
@Parcelize
class SavingsAccounts private constructor(
    val products: List<SavingsAccount>,
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
         * See [SavingsAccounts.products]
         */
        var products: List<SavingsAccount>? = null

        /**
         * See [SavingsAccounts.name]
         */
        var name: String? = null

        /**
         * See [SavingsAccounts.aggregatedBalance]
         */
        var aggregatedBalance: AggregatedBalance? = null

        /**
         * See [SavingsAccounts.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [SavingsAccounts.products]
         */
        fun setProducts(products: List<SavingsAccount>) = apply {
            this.products = products
        }

        /**
         * See [SavingsAccounts.name]
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [SavingsAccounts.aggregatedBalance]
         */
        fun setAggregatedBalance(aggregatedBalance: AggregatedBalance?) = apply {
            this.aggregatedBalance = aggregatedBalance
        }

        /**
         * See [SavingsAccounts.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [SavingsAccounts]
         */
        fun build() = SavingsAccounts(
            requireNotNull(products),
            name,
            aggregatedBalance,
            additions
        )
    }
}

/**
 * DSL to create [SavingsAccounts]
 */
fun SavingsAccounts(block: SavingsAccounts.Builder.() -> Unit) = SavingsAccounts.Builder().apply(block).build()

internal fun SavingsAccounts?.allProducts(): List<SavingsAccount> = this?.products ?: emptyList()
