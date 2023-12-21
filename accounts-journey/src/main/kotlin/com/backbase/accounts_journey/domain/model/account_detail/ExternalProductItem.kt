package com.backbase.accounts_journey.domain.model.account_detail

import dev.drewhamilton.poko.Poko

/**
 * Created by Backbase R&D B.V. on 19/05/2021.
 *
 * @param externalId A unique identifier for the product that the arrangement applies to.
 * @param externalTypeId The ID that is used by the Bank to identify the specific Product type - External reference to the product Type.
 * @param typeName The label/name that is used to label a specific product type.
 * @param productKind A string that identifies the product kind.
 * @param additions Extra information.
 */
@Poko
class ExternalProductItem private constructor(
    val externalId: String?,
    val externalTypeId: String?,
    val typeName: String?,
    val productKind: ExternalProductKindItem?,
    val additions: Map<String, String>?
) {

    /**
     * A builder for this configuration class
     */
    class Builder {

        /**
         * See [ExternalProductItem.externalId].
         */
        var externalId: String? = null

        /**
         * See [ExternalProductItem.externalTypeId].
         */
        var externalTypeId: String? = null

        /**
         * See [ExternalProductItem.typeName].
         */
        var typeName: String? = null

        /**
         * See [ExternalProductItem.productKind].
         */
        var productKind: ExternalProductKindItem? = null

        /**
         * See [ExternalProductItem.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * Build an instance of [ExternalProductItem]
         */
        fun build() = ExternalProductItem(
            externalId = externalId,
            externalTypeId = externalTypeId,
            typeName = typeName,
            productKind = productKind,
            additions = additions
        )
    }
}

/**
 * DSL to create [ExternalProductItem]
 */
@JvmSynthetic
fun ExternalProductItem(block: ExternalProductItem.Builder.() -> Unit) =
    ExternalProductItem.Builder().apply(block).build()
