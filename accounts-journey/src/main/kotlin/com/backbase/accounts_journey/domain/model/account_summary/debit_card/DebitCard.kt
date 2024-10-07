package com.backbase.accounts_journey.domain.model.account_summary.debit_card

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.account_summary.MaskableAttribute
import com.backbase.accounts_journey.domain.model.account_summary.UserPreferences
import com.backbase.accounts_journey.domain.model.common.BaseProduct
import com.backbase.accounts_journey.domain.model.common.CardDetails
import com.backbase.accounts_journey.domain.model.common.DebitCardItem
import com.backbase.accounts_journey.domain.model.common.InterestDetails
import com.backbase.accounts_journey.domain.model.common.ProductState
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param debitCardsItems
 * @param number First 6 and/or last 4 digits of a Payment card. All other digits will/to be masked. Be aware that using card number differently is potential PCI risk.
 * @param urgentTransferAllowed Defines if urgent transfer is allowed.
 * @param cardNumber
 * @param accountInterestRate The annualized cost of credit or debt-capital computed as the percentage ratio of interest to the principal.
 * @param accountHolderNames Party(s) with a relationship to the account.
 * @param startDate
 * @param validThru Expiration date of a credit card, after which is no longer valid.
 * @param id Reference to the product of which the arrangement is an instantiation.
 * @param name
 * @param externalTransferAllowed Defines if transfer to another party is allowed.
 * @param crossCurrencyAllowed Defines if cross currency transfer is allowed
 * @param productKindName The label/name that is used for the respective product kind
 * @param productTypeName The label/name that is used to label a specific product type
 * @param bankAlias The name that can be assigned by the bank to label the arrangement.
 * @param sourceId Indicate if the account is regular or external
 * @param accountOpeningDate
 * @param lastUpdateDate Last date of parameter update for the arrangement.
 * @param userPreferences The preferences configured by the user.
 * @param subArrangements A list of arrangements whose parent is this product.
 * @param state
 * @param parentId Reference to the parent of the arrangement.
 * @param financialInstitutionId Financial institution ID
 * @param lastSyncDate Last synchronization datetime
 * @param unMaskableAttributes An optional list of the maskable attributes that can be unmasked.
 * @param additions Extra parameters
 * @param displayName name of the particular product
 * @param cardDetails
 * @param interestDetails
 * @param reservedAmount The reservation of a portion of a credit or debit balance for the cost of services not yet rendered.
 * @param remainingPeriodicTransfers The limitation in periodic saving transfers or withdrawals. In the case of the US, Regulation D enables for a maximum of 6 monthly savings transfers or withdrawals.
 * @param nextClosingDate The last day of the forthcoming billing cycle.
 * @param overdueSince The date in which the arrangement has been overdue since.
 * @param externalAccountStatus Synchronization statuses an account can have on the provider side after it has been aggregated.
 */
@Suppress("LongParameterList", "MaxLineLength")
@Poko
@Parcelize
class DebitCard internal constructor(
    val debitCardsItems: Set<DebitCardItem>,
    val number: String?,
    val urgentTransferAllowed: Boolean?,
    val cardNumber: BigDecimal?,
    val accountInterestRate: BigDecimal?,
    val accountHolderNames: String?,
    val startDate: OffsetDateTime?,
    val validThru: OffsetDateTime?,
    val id: String?,
    val name: String?,
    val externalTransferAllowed: Boolean?,
    val crossCurrencyAllowed: Boolean?,
    val productKindName: String?,
    val productTypeName: String?,
    val bankAlias: String?,
    val sourceId: String?,
    val accountOpeningDate: OffsetDateTime?,
    val lastUpdateDate: OffsetDateTime?,
    val userPreferences: UserPreferences?,
    val subArrangements: List<BaseProduct>?,
    val state: ProductState?,
    val parentId: String?,
    val financialInstitutionId: Long?,
    val lastSyncDate: OffsetDateTime?,
    val unMaskableAttributes: Set<MaskableAttribute>?,
    val additions: Map<String, String>?,
    val displayName: String?,
    val cardDetails: CardDetails?,
    val interestDetails: InterestDetails?,
    val reservedAmount: BigDecimal?,
    val remainingPeriodicTransfers: BigDecimal?,
    val nextClosingDate: LocalDate?,
    val overdueSince: LocalDate?,
    val externalAccountStatus: String?,
) : Parcelable {

    /**
     * A builder for [DebitCard].
     */
    class Builder {

        /**
         * See [DebitCard.debitCardsItems].
         */
        var debitCardsItems: Set<DebitCardItem>? = null

        /**
         * See [DebitCard.number].
         */
        var number: String? = null

        /**
         * See [DebitCard.urgentTransferAllowed].
         */
        var urgentTransferAllowed: Boolean? = null

        /**
         * See [DebitCard.cardNumber].
         */
        var cardNumber: BigDecimal? = null

        /**
         * See [DebitCard.accountInterestRate].
         */
        var accountInterestRate: BigDecimal? = null

        /**
         * See [DebitCard.accountHolderNames].
         */
        var accountHolderNames: String? = null

        /**
         * See [DebitCard.startDate].
         */
        var startDate: OffsetDateTime? = null

        /**
         * See [DebitCard.validThru].
         */
        var validThru: OffsetDateTime? = null

        /**
         * See [DebitCard.id].
         */
        var id: String? = null

        /**
         * See [DebitCard.name].
         */
        var name: String? = null

        /**
         * See [DebitCard.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [DebitCard.crossCurrencyAllowed].
         */
        var crossCurrencyAllowed: Boolean? = null

        /**
         * See [DebitCard.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [DebitCard.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [DebitCard.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [DebitCard.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [DebitCard.accountOpeningDate].
         */
        var accountOpeningDate: OffsetDateTime? = null

        /**
         * See [DebitCard.lastUpdateDate].
         */
        var lastUpdateDate: OffsetDateTime? = null

        /**
         * See [DebitCard.userPreferences].
         */
        var userPreferences: UserPreferences? = null

        /**
         * See [DebitCard.subArrangements].
         */
        var subArrangements: List<BaseProduct>? = null

        /**
         * See [DebitCard.state].
         */
        var state: ProductState? = null

        /**
         * See [DebitCard.parentId].
         */
        var parentId: String? = null

        /**
         * See [DebitCard.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [DebitCard.lastSyncDate].
         */
        var lastSyncDate: OffsetDateTime? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.debit_card.DebitCard.unMaskableAttributes].
         */
        var unMaskableAttributes: Set<MaskableAttribute>? = null

        /**
         * See [DebitCard.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [DebitCard.displayName].
         */
        var displayName: String? = null

        /**
         * See [DebitCard.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [DebitCard.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [DebitCard.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [DebitCard.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [DebitCard.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [DebitCard.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [DebitCard.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * See [DebitCard.debitCardsItems].
         */
        fun setDebitCardsItems(debitCardsItems: Set<DebitCardItem>) =
            apply { this.debitCardsItems = debitCardsItems }

        /**
         * See [DebitCard.number].
         */
        fun setNumber(number: String?) = apply { this.number = number }

        /**
         * See [DebitCard.urgentTransferAllowed].
         */
        fun setUrgentTransferAllowed(urgentTransferAllowed: Boolean?) =
            apply { this.urgentTransferAllowed = urgentTransferAllowed }

        /**
         * See [DebitCard.cardNumber].
         */
        fun setCardNumber(cardNumber: BigDecimal?) = apply { this.cardNumber = cardNumber }

        /**
         * See [DebitCard.accountInterestRate].
         */
        fun setAccountInterestRate(accountInterestRate: BigDecimal?) =
            apply { this.accountInterestRate = accountInterestRate }

        /**
         * See [DebitCard.accountHolderNames].
         */
        fun setAccountHolderNames(accountHolderNames: String?) =
            apply { this.accountHolderNames = accountHolderNames }

        /**
         * See [DebitCard.startDate].
         */
        fun setStartDate(startDate: OffsetDateTime?) = apply { this.startDate = startDate }

        /**
         * See [DebitCard.validThru].
         */
        fun setValidThru(validThru: OffsetDateTime?) = apply { this.validThru = validThru }

        /**
         * See [DebitCard.id].
         */
        fun setId(id: String?) = apply { this.id = id }

        /**
         * See [DebitCard.name].
         */
        fun setName(name: String?) = apply { this.name = name }

        /**
         * See [DebitCard.externalTransferAllowed].
         */
        fun setExternalTransferAllowed(externalTransferAllowed: Boolean?) =
            apply { this.externalTransferAllowed = externalTransferAllowed }

        /**
         * See [DebitCard.crossCurrencyAllowed].
         */
        fun setCrossCurrencyAllowed(crossCurrencyAllowed: Boolean?) =
            apply { this.crossCurrencyAllowed = crossCurrencyAllowed }

        /**
         * See [DebitCard.productKindName].
         */
        fun setProductKindName(productKindName: String?) = apply { this.productKindName = productKindName }

        /**
         * See [DebitCard.productTypeName].
         */
        fun setProductTypeName(productTypeName: String?) = apply { this.productTypeName = productTypeName }

        /**
         * See [DebitCard.bankAlias].
         */
        fun setBankAlias(bankAlias: String?) = apply { this.bankAlias = bankAlias }

        /**
         * See [DebitCard.sourceId].
         */
        fun setSourceId(sourceId: String?) = apply { this.sourceId = sourceId }

        /**
         * See [DebitCard.accountOpeningDate].
         */
        fun setAccountOpeningDate(accountOpeningDate: OffsetDateTime?) =
            apply { this.accountOpeningDate = accountOpeningDate }

        /**
         * See [DebitCard.lastUpdateDate].
         */
        fun setLastUpdateDate(lastUpdateDate: OffsetDateTime?) =
            apply { this.lastUpdateDate = lastUpdateDate }

        /**
         * See [DebitCard.userPreferences].
         */
        fun setUserPreferences(userPreferences: UserPreferences?) = apply { this.userPreferences = userPreferences }

        /**
         * See [DebitCard.subArrangements].
         */
        fun setSubArrangements(subArrangements: List<BaseProduct>?) = apply {
            this.subArrangements = subArrangements
        }

        /**
         * See [DebitCard.state].
         */
        fun setState(state: ProductState?) = apply { this.state = state }

        /**
         * See [DebitCard.parentId].
         */
        fun setParentId(parentId: String?) = apply { this.parentId = parentId }

        /**
         * See [DebitCard.financialInstitutionId].
         */
        fun setFinancialInstitutionId(financialInstitutionId: Long?) =
            apply { this.financialInstitutionId = financialInstitutionId }

        /**
         * See [DebitCard.lastSyncDate].
         */
        fun setLastSyncDate(lastSyncDate: OffsetDateTime?) = apply { this.lastSyncDate = lastSyncDate }

        /**
         * See [DebitCard.unMaskableAttributes].
         */
        fun setUnMaskableAttributes(unMaskableAttributes: Set<MaskableAttribute>?) =
            apply { this.unMaskableAttributes = unMaskableAttributes }

        /**
         * See [DebitCard.additions].
         */
        fun setAdditions(additions: Map<String, String>?) =
            apply { this.additions = additions }

        /**
         * See [DebitCard.displayName].
         */
        fun setDisplayName(displayName: String?) =
            apply { this.displayName = displayName }

        /**
         * See [DebitCard.cardDetails].
         */
        fun setCardDetails(cardDetails: CardDetails?) =
            apply { this.cardDetails = cardDetails }

        /**
         * See [DebitCard.reservedAmount].
         */
        fun setReservedAmount(reservedAmount: BigDecimal?) =
            apply { this.reservedAmount = reservedAmount }

        /**
         * See [DebitCard.remainingPeriodicTransfers].
         */
        fun setRemainingPeriodicTransfers(remainingPeriodicTransfers: BigDecimal?) =
            apply { this.remainingPeriodicTransfers = remainingPeriodicTransfers }

        /**
         * See [DebitCard.nextClosingDate].
         */
        fun setNextClosingDate(nextClosingDate: LocalDate?) =
            apply { this.nextClosingDate = nextClosingDate }

        /**
         * See [DebitCard.overdueSince].
         */
        fun setOverdueSince(overdueSince: LocalDate?) =
            apply { this.overdueSince = overdueSince }

        /**
         * See [DebitCard.externalAccountStatus].
         */
        fun setExternalAccountStatus(externalAccountStatus: String?) =
            apply { this.externalAccountStatus = externalAccountStatus }

        /**
         * See [DebitCard.interestDetails].
         */
        fun setInterestDetails(interestDetails: InterestDetails?) =
            apply { this.interestDetails = interestDetails }

        /**
         * Builds an instance of [DebitCard].
         */
        fun build() = DebitCard(
            debitCardsItems = debitCardsItems ?: setOf(),
            number = number,
            urgentTransferAllowed = urgentTransferAllowed,
            cardNumber = cardNumber,
            accountInterestRate = accountInterestRate,
            accountHolderNames = accountHolderNames,
            startDate = startDate,
            validThru = validThru,
            id = id,
            name = name,
            externalTransferAllowed = externalTransferAllowed,
            crossCurrencyAllowed = crossCurrencyAllowed,
            productKindName = productKindName,
            productTypeName = productTypeName,
            bankAlias = bankAlias,
            sourceId = sourceId,
            accountOpeningDate = accountOpeningDate,
            lastUpdateDate = lastUpdateDate,
            userPreferences = userPreferences,
            subArrangements = subArrangements,
            state = state,
            parentId = parentId,
            financialInstitutionId = financialInstitutionId,
            lastSyncDate = lastSyncDate,
            unMaskableAttributes = unMaskableAttributes,
            additions = additions,
            displayName = displayName,
            cardDetails = cardDetails,
            interestDetails = interestDetails,
            reservedAmount = reservedAmount,
            remainingPeriodicTransfers = remainingPeriodicTransfers,
            nextClosingDate = nextClosingDate,
            overdueSince = overdueSince,
            externalAccountStatus = externalAccountStatus,
        )
    }
}

/**
 * Builds an instance of [DebitCard] with the [initializer] parameters.
 */
@Suppress("FunctionName") // DSL initializer
fun DebitCard(initializer: DebitCard.Builder.() -> Unit): DebitCard {
    return DebitCard.Builder().apply(initializer).build()
}
