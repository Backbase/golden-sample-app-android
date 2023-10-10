package com.backbase.accounts_journey.domain.model.product_summary.common

import android.os.Parcelable
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param externalStateId An external unique identifier for the arrangement state object.
 * @param state Name that describes the specific arrangement state.
 * @param additions Any extra information
 */
@Poko
@Parcelize
class ProductState internal constructor(
    val externalStateId: String?,
    val state: String?,
    val additions: Map<String, String>?
) : Parcelable {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {
        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.ProductState.externalStateId]
         */
        var externalStateId: String? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.ProductState.state]
         */
        var state: String? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.ProductState.additions]
         */
        var additions: Map<String, String>? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.ProductState.externalStateId]
         */
        fun setExternalStateId(externalStateId: String?) = apply {
            this.externalStateId = externalStateId
        }

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.ProductState.state]
         */
        fun setState(state: String?) = apply {
            this.state = state
        }

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.ProductState.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [ProductState]
         */
        fun build() = ProductState(
            externalStateId,
            state,
            additions
        )
    }
}

/**
 * DSL to create [ProductState]
 */
fun ProductState(block: ProductState.Builder.() -> Unit) = ProductState.Builder().apply(block).build()
