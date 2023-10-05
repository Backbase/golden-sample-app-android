package com.backbase.accounts_journey.domain.model.product_summary.credit_card

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.product_summary.MaskableAttribute
import com.backbase.accounts_journey.domain.model.product_summary.UserPreferences
import com.backbase.accounts_journey.domain.model.product_summary.common.BaseProduct
import com.backbase.accounts_journey.domain.model.product_summary.common.CardDetails
import com.backbase.accounts_journey.domain.model.product_summary.common.InterestDetails
import com.backbase.accounts_journey.domain.model.product_summary.common.ProductState
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param bookedBalance
 * @param availableBalance
 * @param creditLimit
 * @param number First 6 and/or last 4 digits of a Payment card. All other digits will/to be masked. Be aware that using card number differently is potential PCI risk.
 * @param unMaskableAttributes An optional list of the maskable attributes that can be unmasked.
 * @param currency
 * @param bankBranchCode2 (This is to accomodate additional country specific fields fedwire Routing Nmber)
 * @param urgentTransferAllowed Defines if urgent transfer is allowed.
 * @param cardNumber
 * @param creditCardAccountNumber The number of the account the credit card transactions settle on (so actually the reference to the settlement account of the card)?
 * @param validThru Expiration date of a credit card, after which is no longer valid.
 * @param applicableInterestRate The interest rate or rates which would be used for a particular arrangement.
 * @param remainingCredit
 * @param outstandingPayment
 * @param minimumPayment The minimum payment set a percentage of balance, or a fixed cash amount.
 * @param minimumPaymentDueDate Minimum Payment Due Date shown on your monthly statement to remain in good standing.
 * @param accountInterestRate The annualized cost of credit or debt-capital computed as the percentage ratio of interest to the principal.
 * @param accountHolderNames Party(s) with a relationship to the account.
 * @param creditLimitUsage Monetary amount of the used overdraft.
 * @param creditLimitInterestRate Overdraft Interest is an interest applied to the account for any time throughout the month when the account is overdrawn.
 * @param accruedInterest
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
 * @param state
 * @param parentId Reference to the parent of the arrangement.
 * @param subArrangements A list of arrangements whose parent is this product.
 * @param financialInstitutionId Financial institution ID
 * @param lastSyncDate Last synchronization datetime
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
class CreditCard internal constructor(
    val bookedBalance: String?,
    val availableBalance: String?,
    val creditLimit: String?,
    val number: String?,
    val unMaskableAttributes: Set<MaskableAttribute>?,
    val currency: String?,
    val bankBranchCode2: String?,
    val urgentTransferAllowed: Boolean?,
    val cardNumber: BigDecimal?,
    val creditCardAccountNumber: String?,
    val validThru: OffsetDateTime?,
    val applicableInterestRate: BigDecimal?,
    val remainingCredit: BigDecimal?,
    val outstandingPayment: BigDecimal?,
    val minimumPayment: BigDecimal?,
    val minimumPaymentDueDate: OffsetDateTime?,
    val accountInterestRate: BigDecimal?,
    val accountHolderNames: String?,
    val creditLimitUsage: BigDecimal?,
    val creditLimitInterestRate: BigDecimal?,
    val accruedInterest: BigDecimal?,
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
    val state: ProductState?,
    val parentId: String?,
    val subArrangements: List<BaseProduct>?,
    val financialInstitutionId: Long?,
    val lastSyncDate: OffsetDateTime?,
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
     * A builder for [CreditCard].
     */
    class Builder {

        /**
         * See [CreditCard.bookedBalance].
         */
        var bookedBalance: String? = null

        /**
         * See [CreditCard.availableBalance].
         */
        var availableBalance: String? = null

        /**
         * See [CreditCard.creditLimit].
         */
        var creditLimit: String? = null

        /**
         * See [CreditCard.number].
         */
        var number: String? = null

        /**
         * See [CreditCard.unMaskableAttributes].
         */
        var unMaskableAttributes: Set<MaskableAttribute>? = null

        /**
         * See [CreditCard.currency].
         */
        var currency: String? = null

        /**
         * See [CreditCard.bankBranchCode2].
         */
        var bankBranchCode2: String? = null

        /**
         * See [CreditCard.urgentTransferAllowed].
         */
        var urgentTransferAllowed: Boolean? = null

        /**
         * See [CreditCard.cardNumber].
         */
        var cardNumber: BigDecimal? = null

        /**
         * See [CreditCard.creditCardAccountNumber].
         */
        var creditCardAccountNumber: String? = null

        /**
         * See [CreditCard.validThru].
         */
        var validThru: OffsetDateTime? = null

        /**
         * See [CreditCard.applicableInterestRate].
         */
        var applicableInterestRate: BigDecimal? = null

        /**
         * See [CreditCard.remainingCredit].
         */
        var remainingCredit: BigDecimal? = null

        /**
         * See [CreditCard.outstandingPayment].
         */
        var outstandingPayment: BigDecimal? = null

        /**
         * See [CreditCard.minimumPayment].
         */
        var minimumPayment: BigDecimal? = null

        /**
         * See [CreditCard.minimumPaymentDueDate].
         */
        var minimumPaymentDueDate: OffsetDateTime? = null

        /**
         * See [CreditCard.accountInterestRate].
         */
        var accountInterestRate: BigDecimal? = null

        /**
         * See [CreditCard.accountHolderNames].
         */
        var accountHolderNames: String? = null

        /**
         * See [CreditCard.creditLimitUsage].
         */
        var creditLimitUsage: BigDecimal? = null

        /**
         * See [CreditCard.creditLimitInterestRate].
         */
        var creditLimitInterestRate: BigDecimal? = null

        /**
         * See [CreditCard.accruedInterest].
         */
        var accruedInterest: BigDecimal? = null

        /**
         * See [CreditCard.id].
         */
        var id: String? = null

        /**
         * See [CreditCard.name].
         */
        var name: String? = null

        /**
         * See [CreditCard.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [CreditCard.crossCurrencyAllowed].
         */
        var crossCurrencyAllowed: Boolean? = null

        /**
         * See [CreditCard.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [CreditCard.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [CreditCard.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [CreditCard.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [CreditCard.accountOpeningDate].
         */
        var accountOpeningDate: OffsetDateTime? = null

        /**
         * See [CreditCard.lastUpdateDate].
         */
        var lastUpdateDate: OffsetDateTime? = null

        /**
         * See [CreditCard.userPreferences].
         */
        var userPreferences: UserPreferences? = null

        /**
         * See [CreditCard.state].
         */
        var state: ProductState? = null

        /**
         * See [CreditCard.parentId].
         */
        var parentId: String? = null

        /**
         * See [CreditCard.subArrangements].
         */
        var subArrangements: List<BaseProduct>? = null

        /**
         * See [CreditCard.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [CreditCard.lastSyncDate].
         */
        var lastSyncDate: OffsetDateTime? = null

        /**
         * See [CreditCard.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [CreditCard.displayName].
         */
        var displayName: String? = null

        /**
         * See [CreditCard.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [CreditCard.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [CreditCard.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [CreditCard.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [CreditCard.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [CreditCard.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [CreditCard.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * See [CreditCard.bookedBalance].
         */
        fun setBookedBalance(bookedBalance: String?) = apply { this.bookedBalance = bookedBalance }

        /**
         * See [CreditCard.availableBalance].
         */
        fun setAvailableBalance(availableBalance: String?) = apply { this.availableBalance = availableBalance }

        /**
         * See [CreditCard.creditLimit].
         */
        fun setCreditLimit(creditLimit: String?) = apply { this.creditLimit = creditLimit }

        /**
         * See [CreditCard.number].
         */
        fun setNumber(number: String?) = apply { this.number = number }

        /**
         * See [CreditCard.unMaskableAttributes].
         */
        fun setUnMaskableAttributes(unMaskableAttributes: Set<MaskableAttribute>?) =
            apply { this.unMaskableAttributes = unMaskableAttributes }

        /**
         * See [CreditCard.currency].
         */
        fun setCurrency(currency: String?) = apply { this.currency = currency }

        /**
         * See [CreditCard.bankBranchCode2].
         */
        fun setBankBranchCode2(bankBranchCode2: String?) = apply { this.bankBranchCode2 = bankBranchCode2 }

        /**
         * See [CreditCard.urgentTransferAllowed].
         */
        fun setUrgentTransferAllowed(urgentTransferAllowed: Boolean?) =
            apply { this.urgentTransferAllowed = urgentTransferAllowed }

        /**
         * See [CreditCard.cardNumber].
         */
        fun setCardNumber(cardNumber: BigDecimal?) = apply { this.cardNumber = cardNumber }

        /**
         * See [CreditCard.creditCardAccountNumber].
         */
        fun setCreditCardAccountNumber(creditCardAccountNumber: String?) =
            apply { this.creditCardAccountNumber = creditCardAccountNumber }

        /**
         * See [CreditCard.validThru].
         */
        fun setValidThru(validThru: OffsetDateTime?) = apply { this.validThru = validThru }

        /**
         * See [CreditCard.applicableInterestRate].
         */
        fun setApplicableInterestRate(applicableInterestRate: BigDecimal?) =
            apply { this.applicableInterestRate = applicableInterestRate }

        /**
         * See [CreditCard.remainingCredit].
         */
        fun setRemainingCredit(remainingCredit: BigDecimal?) =
            apply { this.remainingCredit = remainingCredit }

        /**
         * See [CreditCard.outstandingPayment].
         */
        fun setOutstandingPayment(outstandingPayment: BigDecimal?) =
            apply { this.outstandingPayment = outstandingPayment }

        /**
         * See [CreditCard.minimumPayment].
         */
        fun setMinimumPayment(minimumPayment: BigDecimal?) = apply { this.minimumPayment = minimumPayment }

        /**
         * See [CreditCard.minimumPaymentDueDate].
         */
        fun setMinimumPaymentDueDate(minimumPaymentDueDate: OffsetDateTime?) =
            apply { this.minimumPaymentDueDate = minimumPaymentDueDate }

        /**
         * See [CreditCard.accountInterestRate].
         */
        fun setAccountInterestRate(accountInterestRate: BigDecimal?) =
            apply { this.accountInterestRate = accountInterestRate }

        /**
         * See [CreditCard.accountHolderNames].
         */
        fun setAccountHolderNames(accountHolderNames: String?) =
            apply { this.accountHolderNames = accountHolderNames }

        /**
         * See [CreditCard.creditLimitUsage].
         */
        fun setCreditLimitUsage(creditLimitUsage: BigDecimal?) =
            apply { this.creditLimitUsage = creditLimitUsage }

        /**
         * See [CreditCard.creditLimitInterestRate].
         */
        fun setCreditLimitInterestRate(creditLimitInterestRate: BigDecimal?) =
            apply { this.creditLimitInterestRate = creditLimitInterestRate }

        /**
         * See [CreditCard.accruedInterest].
         */
        fun setAccruedInterest(accruedInterest: BigDecimal?) =
            apply { this.accruedInterest = accruedInterest }

        /**
         * See [CreditCard.id].
         */
        fun setId(id: String?) = apply { this.id = id }

        /**
         * See [CreditCard.name].
         */
        fun setName(name: String?) = apply { this.name = name }

        /**
         * See [CreditCard.externalTransferAllowed].
         */
        fun setExternalTransferAllowed(externalTransferAllowed: Boolean?) =
            apply { this.externalTransferAllowed = externalTransferAllowed }

        /**
         * See [CreditCard.crossCurrencyAllowed].
         */
        fun setCrossCurrencyAllowed(crossCurrencyAllowed: Boolean?) =
            apply { this.crossCurrencyAllowed = crossCurrencyAllowed }

        /**
         * See [CreditCard.productKindName].
         */
        fun setProductKindName(productKindName: String?) = apply { this.productKindName = productKindName }

        /**
         * See [CreditCard.productTypeName].
         */
        fun setProductTypeName(productTypeName: String?) = apply { this.productTypeName = productTypeName }

        /**
         * See [CreditCard.bankAlias].
         */
        fun setBankAlias(bankAlias: String?) = apply { this.bankAlias = bankAlias }

        /**
         * See [CreditCard.sourceId].
         */
        fun setSourceId(sourceId: String?) = apply { this.sourceId = sourceId }

        /**
         * See [CreditCard.accountOpeningDate].
         */
        fun setAccountOpeningDate(accountOpeningDate: OffsetDateTime?) =
            apply { this.accountOpeningDate = accountOpeningDate }

        /**
         * See [CreditCard.lastUpdateDate].
         */
        fun setLastUpdateDate(lastUpdateDate: OffsetDateTime?) =
            apply { this.lastUpdateDate = lastUpdateDate }

        /**
         * See [CreditCard.userPreferences].
         */
        fun setUserPreferences(userPreferences: UserPreferences?) = apply { this.userPreferences = userPreferences }

        /**
         * See [CreditCard.state].
         */
        fun setState(state: ProductState?) = apply { this.state = state }

        /**
         * See [CreditCard.parentId].
         */
        fun setParentId(parentId: String?) = apply { this.parentId = parentId }

        /**
         * See [CreditCard.subArrangements].
         */
        fun setSubArrangements(subArrangements: List<BaseProduct>?) = apply {
            this.subArrangements = subArrangements
        }

        /**
         * See [CreditCard.financialInstitutionId].
         */
        fun setFinancialInstitutionId(financialInstitutionId: Long?) =
            apply { this.financialInstitutionId = financialInstitutionId }

        /**
         * See [CreditCard.lastSyncDate].
         */
        fun setLastSyncDate(lastSyncDate: OffsetDateTime?) = apply { this.lastSyncDate = lastSyncDate }

        /**
         * See [CreditCard.additions].
         */
        fun setAdditions(additions: Map<String, String>?) =
            apply { this.additions = additions }

        /**
         * See [CreditCard.displayName].
         */
        fun setDisplayName(displayName: String?) =
            apply { this.displayName = displayName }

        /**
         * See [CreditCard.cardDetails].
         */
        fun setCardDetails(cardDetails: CardDetails?) =
            apply { this.cardDetails = cardDetails }

        /**
         * See [CreditCard.reservedAmount].
         */
        fun setReservedAmount(reservedAmount: BigDecimal?) =
            apply { this.reservedAmount = reservedAmount }

        /**
         * See [CreditCard.remainingPeriodicTransfers].
         */
        fun setRemainingPeriodicTransfers(remainingPeriodicTransfers: BigDecimal?) =
            apply { this.remainingPeriodicTransfers = remainingPeriodicTransfers }

        /**
         * See [CreditCard.nextClosingDate].
         */
        fun setNextClosingDate(nextClosingDate: LocalDate?) =
            apply { this.nextClosingDate = nextClosingDate }

        /**
         * See [CreditCard.overdueSince].
         */
        fun setOverdueSince(overdueSince: LocalDate?) =
            apply { this.overdueSince = overdueSince }

        /**
         * See [CreditCard.externalAccountStatus].
         */
        fun setExternalAccountStatus(externalAccountStatus: String?) =
            apply { this.externalAccountStatus = externalAccountStatus }

        /**
         * See [CreditCard.interestDetails].
         */
        fun setInterestDetails(interestDetails: InterestDetails?) =
            apply { this.interestDetails = interestDetails }

        /**
         * Builds an instance of [CreditCard].
         */
        fun build() = CreditCard(
            bookedBalance = bookedBalance,
            availableBalance = availableBalance,
            creditLimit = creditLimit,
            number = number,
            unMaskableAttributes = unMaskableAttributes,
            currency = currency,
            bankBranchCode2 = bankBranchCode2,
            urgentTransferAllowed = urgentTransferAllowed,
            cardNumber = cardNumber,
            creditCardAccountNumber = creditCardAccountNumber,
            validThru = validThru,
            applicableInterestRate = applicableInterestRate,
            remainingCredit = remainingCredit,
            outstandingPayment = outstandingPayment,
            minimumPayment = minimumPayment,
            minimumPaymentDueDate = minimumPaymentDueDate,
            accountInterestRate = accountInterestRate,
            accountHolderNames = accountHolderNames,
            creditLimitUsage = creditLimitUsage,
            creditLimitInterestRate = creditLimitInterestRate,
            accruedInterest = accruedInterest,
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
            state = state,
            parentId = parentId,
            subArrangements = subArrangements,
            financialInstitutionId = financialInstitutionId,
            lastSyncDate = lastSyncDate,
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
 * Builds an instance of [CreditCard] with the [initializer] parameters.
 */
@Suppress("FunctionName") // DSL initializer
fun CreditCard(initializer: CreditCard.Builder.() -> Unit): CreditCard {
    return CreditCard.Builder().apply(initializer).build()
}
