package com.backbase.accounts_journey.domain.model.product_summary.loan

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.product_summary.common.AggregatedBalance
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param products The list of loans.
 * @param displayName The label/name of a Loan used to display on screen.
 * @param aggregatedBalance The aggregated balance of this group of products.
 * @param additions Extra parameters
 */
@Poko
@Parcelize
class Loans private constructor(
    val products: List<Loan>,
    val name: String?,
    val displayName: String?,
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
         * See [Loans.products]
         */
        @set:JvmSynthetic
        var products: List<Loan>? = null

        /**
         * See [Loans.name]
         */
        @set:JvmSynthetic
        var name: String? = null

        /**
         * See [Loans.displayName]
         */
        @set:JvmSynthetic
        var displayName: String? = null

        /**
         * See [Loans.aggregatedBalance]
         */
        @set:JvmSynthetic
        var aggregatedBalance: AggregatedBalance? = null

        /**
         * See [Loans.additions]
         */
        @set:JvmSynthetic
        var additions: Map<String, String>? = null

        /**
         * See [Loans.products]
         */
        fun setProducts(products: List<Loan>) = apply {
            this.products = products
        }

        /**
         * See [Loans.name]
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [Loans.displayName]
         */
        fun setDisplayName(displayName: String?) = apply {
            this.displayName = displayName
        }

        /**
         * See [Loans.aggregatedBalance]
         */
        fun setAggregatedBalance(aggregatedBalance: AggregatedBalance?) = apply {
            this.aggregatedBalance = aggregatedBalance
        }

        /**
         * See [Loans.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [Loans]
         */
        fun build() = Loans(
            requireNotNull(products),
            name,
            displayName,
            aggregatedBalance,
            additions
        )
    }
}

/**
 * DSL to create [Loans]
 */
@JvmSynthetic
fun Loans(block: Loans.Builder.() -> Unit) = Loans.Builder().apply(block).build()

internal fun Loans?.allProducts(): List<Loan> = this?.products ?: emptyList()
