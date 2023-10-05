package com.backbase.accounts_journey.domain.model.product_summary.custom_products

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.product_summary.common.AggregatedBalance
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param products A list of custom products.
 * @param name The label/name that is used for this particular group of custom products.
 * @param id The id of this group of custom products.
 * @param aggregatedBalance The aggregated balance of this group of products.
 * @param additions Extra information.
 */
@Poko
@Parcelize
class CustomProducts private constructor(
    val products: List<GeneralAccount>,
    val name: String?,
    val id: Int?,
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
         * See [CustomProducts.products]
         */
        var products: List<GeneralAccount>? = null

        /**
         * See [CustomProducts.name]
         */
        var name: String? = null

        /**
         * See [CustomProducts.id]
         */
        var id: Int? = null

        /**
         * See [CustomProducts.aggregatedBalance]
         */
        var aggregatedBalance: AggregatedBalance? = null

        /**
         * See [CustomProducts.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [CustomProducts.products]
         */
        fun setProducts(products: List<GeneralAccount>) = apply {
            this.products = products
        }

        /**
         * See [CustomProducts.name]
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [CustomProducts.id]
         */
        fun setId(id: Int?) = apply {
            this.id = id
        }

        /**
         * See [CustomProducts.aggregatedBalance]
         */
        fun setAggregatedBalance(aggregatedBalance: AggregatedBalance?) = apply {
            this.aggregatedBalance = aggregatedBalance
        }

        /**
         * See [CustomProducts.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [CustomProducts]
         */
        fun build() = CustomProducts(
            requireNotNull(products),
            name,
            id,
            aggregatedBalance,
            additions
        )
    }
}

/**
 * DSL to create [CustomProducts]
 */
fun CustomProducts(block: CustomProducts.Builder.() -> Unit) =
    CustomProducts.Builder().apply(block).build()
