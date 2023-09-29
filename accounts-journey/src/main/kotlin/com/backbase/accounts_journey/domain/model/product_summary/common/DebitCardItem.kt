package com.backbase.accounts_journey.domain.model.product_summary.common

import android.os.Parcelable
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param number First 6 and/or last 4 digits of a Payment card. All other digits will/to be masked. Be aware that using card number differently is potential PCI risk.
 * @param expiryDate Expiration date of a debit card, after which is no longer valid.
 * @param cardId External ID of the card.
 * @param cardHolderName First Name and Last Name of card holder.
 * @param cardType Card type to change card image based on it, ex. Maestro Gold
 * @param cardStatus Status of the card ex. Active, Expired etc.
 * @param additions Extra parameters
 */
@Suppress("LongParameterList", "MaxLineLength")
@Poko
@Parcelize
class DebitCardItem internal constructor(
    val number: String?,
    val expiryDate: String?,
    val cardId: String?,
    val cardHolderName: String?,
    val cardType: String?,
    val cardStatus: String?,
    val additions: Map<String, String>?
) : Parcelable {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {
        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.number]
         */
        @set:JvmSynthetic
        var number: String? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.expiryDate]
         */
        @set:JvmSynthetic
        var expiryDate: String? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.cardId]
         */
        @set:JvmSynthetic
        var cardId: String? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.cardHolderName]
         */
        @set:JvmSynthetic
        var cardHolderName: String? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.cardType]
         */
        @set:JvmSynthetic
        var cardType: String? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.cardStatus]
         */
        @set:JvmSynthetic
        var cardStatus: String? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.additions]
         */
        @set:JvmSynthetic
        var additions: Map<String, String>? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.number]
         */
        fun setNumber(number: String?) = apply {
            this.number = number
        }

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.expiryDate]
         */
        fun setExpiryDate(expiryDate: String?) = apply {
            this.expiryDate = expiryDate
        }

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.cardId]
         */
        fun setCardId(cardId: String?) = apply {
            this.cardId = cardId
        }

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.cardHolderName]
         */
        fun setCardHolderName(cardHolderName: String?) = apply {
            this.cardHolderName = cardHolderName
        }

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.cardType]
         */
        fun setCardType(cardType: String?) = apply {
            this.cardType = cardType
        }

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem]
         */
        fun setCardStatus(cardStatus: String?) = apply {
            this.cardStatus = cardStatus
        }

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.common.DebitCardItem.additions]
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * Builds an instance of [DebitCardItem]
         */
        fun build() = DebitCardItem(
            number,
            expiryDate,
            cardId,
            cardHolderName,
            cardType,
            cardStatus,
            additions
        )
    }
}

/**
 * DSL to create [DebitCardItem]
 */
@JvmSynthetic
fun DebitCardItem(block: DebitCardItem.Builder.() -> Unit) = DebitCardItem.Builder().apply(block).build()
