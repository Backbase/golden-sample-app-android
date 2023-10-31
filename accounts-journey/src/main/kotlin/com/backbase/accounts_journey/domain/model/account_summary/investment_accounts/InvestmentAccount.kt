package com.backbase.accounts_journey.domain.model.account_summary.investment_accounts

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.account_summary.MaskableAttribute
import com.backbase.accounts_journey.domain.model.account_summary.UserPreferences
import com.backbase.accounts_journey.domain.model.common.BaseProduct
import com.backbase.accounts_journey.domain.model.common.CardDetails
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
 * @param currentInvestmentValue
 * @param currency
 * @param urgentTransferAllowed Defines if urgent transfer is allowed.
 * @param productNumber The number identifying the contract.
 * @param IBAN The International Bank Account Number. If specified, it must be a valid IBAN, otherwise an invalid value error could be raised.
 * @param BBAN Specifying the BBAN of the account - BBAN is short for Basic Bank Account Number. It represents a country-specific bank account number.
 * @param id Reference to the product of which the arrangement is an instantiation.
 * @param name
 * @param externalTransferAllowed Defines if transfer to another party is allowed.
 * @param crossCurrencyAllowed Defines if cross currency transfer is allowed
 * @param productKindName The label/name that is used for the respective product kind
 * @param productTypeName The label/name that is used to label a specific product type
 * @param bankAlias The name that can be assigned by the bank to label the arrangement.
 * @param sourceId Indicate if the account is regular or external
 * @param accountOpeningDate The date of activation of the account in the bank's system. Defaults to null
 * @param lastUpdateDate Last date of parameter update for the arrangement.
 * @param userPreferences The preferences configured by the user.
 * @param state
 * @param parentId Reference to the parent of the arrangement.
 * @param financialInstitutionId Financial institution ID
 * @param lastSyncDate Last synchronization datetime
 * @param subArrangements A list of arrangements whose parent is this product.
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
 * @param accruedInterest The interest that is earned (credit interest) or due (debit interest) but not settled yet. Defaults to null
 * @param creditLimitExpiryDate The date after which overdraft will no longer be available to the account (renewed automatically or cancelled).
 * @param valueDateBalance The balance of the account on a specific date that needs to be used for the calculation of interest. NB! If no date is specified (like for the book date balance) the current date can be assumed.
 * @param accountHolderNames Party(s) with a relationship to the account.
 */
@Suppress("LongParameterList", "MaxLineLength")
@Poko
@Parcelize
class InvestmentAccount internal constructor(
    val currentInvestmentValue: String?,
    val currency: String?,
    val urgentTransferAllowed: Boolean?,
    val productNumber: String?,
    @SuppressWarnings("ConstructorParameterNaming") val IBAN: String?,
    @SuppressWarnings("ConstructorParameterNaming") val BBAN: String?,
    val unMaskableAttributes: Set<MaskableAttribute>?,
    val id: String?,
    val name: String?,
    val displayName: String?,
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
    val cardDetails: CardDetails?,
    val interestDetails: InterestDetails?,
    val reservedAmount: BigDecimal?,
    val remainingPeriodicTransfers: BigDecimal?,
    val nextClosingDate: LocalDate?,
    val overdueSince: LocalDate?,
    val externalAccountStatus: String?,
    val additions: Map<String, String>?,
    val accruedInterest: BigDecimal?,
    val creditLimitExpiryDate: OffsetDateTime?,
    val valueDateBalance: BigDecimal?,
    val accountHolderNames: String?,
) : Parcelable {

    /**
     * A builder for [InvestmentAccount].
     */
    class Builder {

        /**
         * See [InvestmentAccount.currentInvestmentValue].
         */
        var currentInvestmentValue: String? = null

        /**
         * See [InvestmentAccount.currency].
         */
        var currency: String? = null

        /**
         * See [InvestmentAccount.urgentTransferAllowed].
         */
        var urgentTransferAllowed: Boolean? = null

        /**
         * See [InvestmentAccount.productNumber].
         */
        var productNumber: String? = null

        /**
         * See [InvestmentAccount.IBAN].
         */
        @SuppressWarnings("VariableNaming")
        var IBAN: String? = null

        /**
         * See [InvestmentAccount.BBAN].
         */
        @SuppressWarnings("VariableNaming")
        var BBAN: String? = null

        /**
         * See [InvestmentAccount.id].
         */
        var id: String? = null

        /**
         * See [InvestmentAccount.name].
         */
        var name: String? = null

        /**
         * See [InvestmentAccount.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [InvestmentAccount.crossCurrencyAllowed].
         */
        var crossCurrencyAllowed: Boolean? = null

        /**
         * See [InvestmentAccount.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [InvestmentAccount.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [InvestmentAccount.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [InvestmentAccount.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [InvestmentAccount.accountOpeningDate].
         */
        var accountOpeningDate: OffsetDateTime? = null

        /**
         * See [InvestmentAccount.lastUpdateDate].
         */
        var lastUpdateDate: OffsetDateTime? = null

        /**
         * See [InvestmentAccount.userPreferences].
         */
        var userPreferences: UserPreferences? = null

        /**
         * See [InvestmentAccount.state].
         */
        var state: ProductState? = null

        /**
         * See [InvestmentAccount.parentId].
         */
        var parentId: String? = null

        /**
         * See [InvestmentAccount.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [InvestmentAccount.lastSyncDate].
         */
        var lastSyncDate: OffsetDateTime? = null

        /**
         * See [InvestmentAccount.subArrangements].
         */
        var subArrangements: List<BaseProduct>? = null

        /**
         * See [com.backbase.android.retail.journey.accounts_and_transactions.accounts.product_summary_dtos.investment_accounts.InvestmentAccount.unMaskableAttributes].
         */
        var unMaskableAttributes: Set<MaskableAttribute>? = null

        /**
         * See [InvestmentAccount.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [InvestmentAccount.displayName].
         */
        var displayName: String? = null

        /**
         * See [InvestmentAccount.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [InvestmentAccount.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [InvestmentAccount.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [InvestmentAccount.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [InvestmentAccount.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [InvestmentAccount.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [InvestmentAccount.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * See [InvestmentAccount.accruedInterest].
         */
        var accruedInterest: BigDecimal? = null

        /**
         * See [InvestmentAccount.creditLimitExpiryDate].
         */
        var creditLimitExpiryDate: OffsetDateTime? = null

        /**
         * See [InvestmentAccount.valueDateBalance].
         */
        var valueDateBalance: BigDecimal? = null

        /**
         * See [InvestmentAccount.accountHolderNames].
         */
        var accountHolderNames: String? = null

        /**
         * See [InvestmentAccount.currentInvestmentValue].
         */
        fun setCurrentInvestmentValue(currentInvestmentValue: String?) =
            apply { this.currentInvestmentValue = currentInvestmentValue }

        /**
         * See [InvestmentAccount.currency].
         */
        fun setCurrency(currency: String?) = apply { this.currency = currency }

        /**
         * See [InvestmentAccount.urgentTransferAllowed].
         */
        fun setUrgentTransferAllowed(urgentTransferAllowed: Boolean?) =
            apply { this.urgentTransferAllowed = urgentTransferAllowed }

        /**
         * See [InvestmentAccount.productNumber].
         */
        fun setProductNumber(productNumber: String?) = apply { this.productNumber = productNumber }

        /**
         * See [InvestmentAccount.IBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setIBAN(IBAN: String?) = apply { this.IBAN = IBAN }

        /**
         * See [InvestmentAccount.BBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setBBAN(BBAN: String?) = apply { this.BBAN = BBAN }

        /**
         * See [InvestmentAccount.id].
         */
        fun setId(id: String?) = apply { this.id = id }

        /**
         * See [InvestmentAccount.name].
         */
        fun setName(name: String?) = apply { this.name = name }

        /**
         * See [InvestmentAccount.externalTransferAllowed].
         */
        fun setExternalTransferAllowed(externalTransferAllowed: Boolean?) =
            apply { this.externalTransferAllowed = externalTransferAllowed }

        /**
         * See [InvestmentAccount.crossCurrencyAllowed].
         */
        fun setCrossCurrencyAllowed(crossCurrencyAllowed: Boolean?) =
            apply { this.crossCurrencyAllowed = crossCurrencyAllowed }

        /**
         * See [InvestmentAccount.productKindName].
         */
        fun setProductKindName(productKindName: String?) = apply { this.productKindName = productKindName }

        /**
         * See [InvestmentAccount.productTypeName].
         */
        fun setProductTypeName(productTypeName: String?) = apply { this.productTypeName = productTypeName }

        /**
         * See [InvestmentAccount.bankAlias].
         */
        fun setBankAlias(bankAlias: String?) = apply { this.bankAlias = bankAlias }

        /**
         * See [InvestmentAccount.sourceId].
         */
        fun setSourceId(sourceId: String?) = apply { this.sourceId = sourceId }

        /**
         * See [InvestmentAccount.accountOpeningDate].
         */
        fun setAccountOpeningDate(accountOpeningDate: OffsetDateTime?) =
            apply { this.accountOpeningDate = accountOpeningDate }

        /**
         * See [InvestmentAccount.lastUpdateDate].
         */
        fun setLastUpdateDate(lastUpdateDate: OffsetDateTime?) =
            apply { this.lastUpdateDate = lastUpdateDate }

        /**
         * See [InvestmentAccount.userPreferences].
         */
        fun setUserPreferences(userPreferences: UserPreferences?) = apply { this.userPreferences = userPreferences }

        /**
         * See [InvestmentAccount.state].
         */
        fun setState(state: ProductState?) = apply { this.state = state }

        /**
         * See [InvestmentAccount.parentId].
         */
        fun setParentId(parentId: String?) = apply { this.parentId = parentId }

        /**
         * See [InvestmentAccount.financialInstitutionId].
         */
        fun setFinancialInstitutionId(financialInstitutionId: Long?) =
            apply { this.financialInstitutionId = financialInstitutionId }

        /**
         * See [InvestmentAccount.lastSyncDate].
         */
        fun setLastSyncDate(lastSyncDate: OffsetDateTime?) = apply { this.lastSyncDate = lastSyncDate }

        /**
         * See [InvestmentAccount.subArrangements].
         */
        fun setSubArrangements(subArrangements: List<BaseProduct>?) = apply {
            this.subArrangements = subArrangements
        }

        /**
         * See [InvestmentAccount.unMaskableAttributes].
         */
        fun setUnMaskableAttributes(unMaskableAttributes: Set<MaskableAttribute>?) = apply {
            this.unMaskableAttributes = unMaskableAttributes
        }

        /**
         * See [InvestmentAccount.additions].
         */
        fun setAdditions(additions: Map<String, String>?) =
            apply { this.additions = additions }

        /**
         * See [InvestmentAccount.displayName].
         */
        fun setDisplayName(displayName: String?) =
            apply { this.displayName = displayName }

        /**
         * See [InvestmentAccount.cardDetails].
         */
        fun setCardDetails(cardDetails: CardDetails?) =
            apply { this.cardDetails = cardDetails }

        /**
         * See [InvestmentAccount.reservedAmount].
         */
        fun setReservedAmount(reservedAmount: BigDecimal?) =
            apply { this.reservedAmount = reservedAmount }

        /**
         * See [InvestmentAccount.remainingPeriodicTransfers].
         */
        fun setRemainingPeriodicTransfers(remainingPeriodicTransfers: BigDecimal?) =
            apply { this.remainingPeriodicTransfers = remainingPeriodicTransfers }

        /**
         * See [InvestmentAccount.nextClosingDate].
         */
        fun setNextClosingDate(nextClosingDate: LocalDate?) =
            apply { this.nextClosingDate = nextClosingDate }

        /**
         * See [InvestmentAccount.overdueSince].
         */
        fun setOverdueSince(overdueSince: LocalDate?) =
            apply { this.overdueSince = overdueSince }

        /**
         * See [InvestmentAccount.externalAccountStatus].
         */
        fun setExternalAccountStatus(externalAccountStatus: String?) =
            apply { this.externalAccountStatus = externalAccountStatus }

        /**
         * See [InvestmentAccount.interestDetails].
         */
        fun setInterestDetails(interestDetails: InterestDetails?) =
            apply { this.interestDetails = interestDetails }

        /**
         * See [InvestmentAccount.accruedInterest].
         */
        fun setAccruedInterest(accruedInterest: BigDecimal?) = apply {
            this.accruedInterest = accruedInterest
        }

        /**
         * See [InvestmentAccount.creditLimitExpiryDate].
         */
        fun setCreditLimitExpiryDate(creditLimitExpiryDate: OffsetDateTime?) = apply {
            this.creditLimitExpiryDate = creditLimitExpiryDate
        }

        /**
         * See [InvestmentAccount.valueDateBalance].
         */
        fun setValueDateBalance(valueDateBalance: BigDecimal?) = apply {
            this.valueDateBalance = valueDateBalance
        }

        /**
         * See [InvestmentAccount.accountHolderNames].
         */
        fun setAccountHolderNames(accountHolderNames: String?) = apply {
            this.accountHolderNames = accountHolderNames
        }

        /**
         * Builds an instance of [InvestmentAccount].
         */
        fun build() = InvestmentAccount(
            currentInvestmentValue = currentInvestmentValue,
            currency = currency,
            urgentTransferAllowed = urgentTransferAllowed,
            productNumber = productNumber,
            IBAN = IBAN,
            BBAN = BBAN,
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
            financialInstitutionId = financialInstitutionId,
            lastSyncDate = lastSyncDate,
            subArrangements = subArrangements,
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
            accruedInterest = accruedInterest,
            creditLimitExpiryDate = creditLimitExpiryDate,
            valueDateBalance = valueDateBalance,
            accountHolderNames = accountHolderNames,
        )
    }
}

/**
 * Builds an instance of [InvestmentAccount] with the [initializer] parameters.
 */
@Suppress("FunctionName") // DSL initializer
fun InvestmentAccount(initializer: InvestmentAccount.Builder.() -> Unit): InvestmentAccount {
    return InvestmentAccount.Builder().apply(initializer).build()
}
