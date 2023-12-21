package com.backbase.accounts_journey.domain.model.account_detail

import dev.drewhamilton.poko.Poko

/**
 * Created by Backbase R&D B.V. on 20/05/2021.
 *
 * @param externalKindId unique external identifier for the product kind.
 * @param kindName unique product kind name.
 * @param kindUri unique product kind uri.
 * @param additions extra information.
 */
@Poko
class ExternalProductKindItem private constructor(
    val externalKindId: String,
    val kindName: String,
    val kindUri: String,
    val additions: Map<String, String>?
) {
    /**
     * A builder for this configuration class
     */
    class Builder {

        /**
         * See [ExternalProductKindItem.externalKindId].
         */
        var externalKindId: String? = null

        /**
         * See [ExternalProductKindItem.kindName].
         */
        var kindName: String? = null

        /**
         * See [ExternalProductKindItem.kindUri].
         */
        var kindUri: String? = null

        /**
         * See [ExternalProductKindItem.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * Build an instance of [ExternalProductKindItem]
         */
        fun build() = ExternalProductKindItem(
            externalKindId = requireNotNull(externalKindId),
            kindName = requireNotNull(kindName),
            kindUri = requireNotNull(kindUri),
            additions = additions
        )
    }
}

/**
 * DSL to create [ExternalProductKindItem]
 */
@JvmSynthetic
fun ExternalProductKindItem(block: ExternalProductKindItem.Builder.() -> Unit) =
    ExternalProductKindItem.Builder().apply(block).build()
