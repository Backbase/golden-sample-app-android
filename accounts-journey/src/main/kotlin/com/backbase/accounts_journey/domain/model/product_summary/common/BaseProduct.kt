package com.backbase.accounts_journey.domain.model.product_summary.common

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.product_summary.UserPreferences
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * This class contains fields that are common to all products, but be aware no product directly extends this class.
 *
 * @param id Reference to the product of which the arrangement is an instantiation.
 * @param name The name of the product.
 * @param externalTransferAllowed Defines if transfer to another party is allowed.
 * @param crossCurrencyAllowed Defines if cross currency transfer is allowed
 * @param productKindName The label/name that is used for the respective product kind
 * @param productTypeName The label/name that is used to label a specific product type
 * @param bankAlias The name that can be assigned by the bank to label the arrangement.
 * @param sourceId Indicate if the account is regular or external
 * @param visible indicator whether to show or hide the arrangement on the widget
 * @param accountOpeningDate The date this account was opened.
 * @param lastUpdateDate Last date of parameter update for the arrangement.
 * @param userPreferences The preferences configured by the user.
 * @param state the state of the product. See [ProductState].
 * @param parentId Reference to the parent of the arrangement.
 * @param subArrangements A list of arrangements whose parent is this product.
 * @param financialInstitutionId Financial institution ID
 * @param lastSyncDate Last synchronization datetime
 * @param additions Additional name-value pairs.
 * @param cardDetails
 * @param interestDetails
 * @param reservedAmount The reservation of a portion of a credit or debit balance for the cost of services not yet rendered.
 * @param remainingPeriodicTransfers The limitation in periodic saving transfers or withdrawals. In the case of the US, Regulation D enables for a maximum of 6 monthly savings transfers or withdrawals.
 * @param overdueSince The date in which the arrangement has been overdue since.
 * @param externalAccountStatus Synchronization statuses an account can have on the provider side after it has been aggregated.
 * @param bankBranchCode2 (This is to accomodate additional country specific fields fedwire Routing Nmber)
 * @param nextClosingDate The last day of the forthcoming billing cycle.
 */
@Suppress("LongParameterList", "MaxLineLength")
@Poko
@Parcelize
class BaseProduct internal constructor(
    val id: String?,
    val name: String?,
    val externalTransferAllowed: Boolean?,
    val crossCurrencyAllowed: Boolean?,
    val productKindName: String?,
    val productTypeName: String?,
    val bankAlias: String?,
    val sourceId: String?,
    val visible: Boolean?,
    val accountOpeningDate: OffsetDateTime?,
    val lastUpdateDate: OffsetDateTime?,
    val userPreferences: UserPreferences?,
    val state: ProductState?,
    val parentId: String?,
    val subArrangements: List<BaseProduct>?,
    val financialInstitutionId: Long?,
    val lastSyncDate: OffsetDateTime?,
    val additions: Map<String, String>?,
    val cardDetails: CardDetails?,
    val interestDetails: InterestDetails?,
    val reservedAmount: BigDecimal?,
    val remainingPeriodicTransfers: BigDecimal?,
    val nextClosingDate: LocalDate?,
    val overdueSince: LocalDate?,
    val externalAccountStatus: String?,
    val bankBranchCode2: String?,
) : Parcelable {

    /**
     * A builder for [BaseProduct].
     */
    class Builder {

        /**
         * See [BaseProduct.id].
         */
        var id: String? = null

        /**
         * See [BaseProduct.name].
         */
        var name: String? = null

        /**
         * See [BaseProduct.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [BaseProduct.crossCurrencyAllowed].
         */
        var crossCurrencyAllowed: Boolean? = null

        /**
         * See [BaseProduct.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [BaseProduct.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [BaseProduct.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [BaseProduct.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [BaseProduct.visible].
         */
        var visible: Boolean? = null

        /**
         * See [BaseProduct.accountOpeningDate].
         */
        var accountOpeningDate: OffsetDateTime? = null

        /**
         * See [BaseProduct.lastUpdateDate].
         */
        var lastUpdateDate: OffsetDateTime? = null

        /**
         * See [BaseProduct.userPreferences].
         */
        var userPreferences: UserPreferences? = null

        /**
         * See [BaseProduct.state].
         */
        var state: ProductState? = null

        /**
         * See [BaseProduct.parentId].
         */
        var parentId: String? = null

        /**
         * See [BaseProduct.subArrangements].
         */
        var subArrangements: List<BaseProduct>? = null

        /**
         * See [BaseProduct.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [BaseProduct.lastSyncDate].
         */
        var lastSyncDate: OffsetDateTime? = null

        /**
         * See [BaseProduct.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [BaseProduct.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [BaseProduct.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [BaseProduct.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [BaseProduct.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [BaseProduct.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [BaseProduct.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [BaseProduct.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * See [BaseProduct.bankBranchCode2].
         */
        var bankBranchCode2: String? = null

        /**
         * Builds an instance of [BaseProduct].
         */
        fun build() = BaseProduct(
            id = id,
            name = name,
            externalTransferAllowed = externalTransferAllowed,
            crossCurrencyAllowed = crossCurrencyAllowed,
            productKindName = productKindName,
            productTypeName = productTypeName,
            bankAlias = bankAlias,
            sourceId = sourceId,
            visible = visible,
            accountOpeningDate = accountOpeningDate,
            lastUpdateDate = lastUpdateDate,
            userPreferences = userPreferences,
            state = state,
            parentId = parentId,
            subArrangements = subArrangements,
            financialInstitutionId = financialInstitutionId,
            lastSyncDate = lastSyncDate,
            additions = additions,
            cardDetails = cardDetails,
            interestDetails = interestDetails,
            reservedAmount = reservedAmount,
            remainingPeriodicTransfers = remainingPeriodicTransfers,
            nextClosingDate = nextClosingDate,
            overdueSince = overdueSince,
            externalAccountStatus = externalAccountStatus,
            bankBranchCode2 = bankBranchCode2,
        )
    }
}

/**
 * Builds an instance of [BaseProduct] with the [initializer] parameters.
 */
@Suppress("FunctionName") // DSL initializer
@JvmSynthetic // Hide from Java callers who should use Builder
fun BaseProduct(initializer: BaseProduct.Builder.() -> Unit): BaseProduct {
    return BaseProduct.Builder().apply(initializer).build()
}
