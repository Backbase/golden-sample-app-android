package com.backbase.domain.model.product_summary.current_accounts

import com.backbase.domain.model.product_summary.MaskableAttribute
import com.backbase.domain.model.product_summary.UserPreferences
import com.backbase.domain.model.product_summary.common.BaseProduct
import com.backbase.domain.model.product_summary.common.CardDetails
import com.backbase.domain.model.product_summary.common.DebitCardItem
import com.backbase.domain.model.product_summary.common.InterestDetails
import com.backbase.domain.model.product_summary.common.ProductState
import dev.drewhamilton.poko.Poko
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param debitCardItems A list of debit cards associated with this product.
 * @param bookedBalance
 * @param availableBalance
 * @param creditLimit
 * @param IBAN The International Bank Account Number. If specified, it must be a valid IBAN, otherwise an invalid value error could be raised.
 * @param BBAN Specifying the BBAN of the account - BBAN is short for Basic Bank Account Number. It represents a country-specific bank account number.
 * @param BIC Bank Identifier Code - international bank code that identifies particular banks worldwide
 * @param unMaskableAttributes An optional list of the maskable attributes that can be unmasked.
 * @param currency The alpha-3 code (complying with ISO 4217) of the currency that qualifies the amount.
 * @param urgentTransferAllowed Defines if urgent transfer is allowed.
 * @param bankBranchCode (This is to accomodate additional country specific fields like Sort Code in UK)
 * @param bankBranchCode2 (This is to accomodate additional country specific fields fedwire Routing Nmber)
 * @param accountInterestRate The annualized cost of credit or debt-capital computed as the percentage ratio of interest to the principal.
 * @param valueDateBalance The balance of the account on a specific date that needs to be used for the calculation of interest. NB! If no date is specified (like for the book date balance) the current date can be assumed.
 * @param creditLimitUsage Monetary amount of the used overdraft.
 * @param creditLimitInterestRate Overdraft Interest is an interest applied to the account for any time throughout the month when the account is overdrawn.
 * @param creditLimitExpiryDate The date after which overdraft will no longer be available to the account (renewed automatically or cancelled).
 * @param accruedInterest
 * @param accountHolderNames Party(s) with a relationship to the account.
 * @param startDate
 * @param minimumRequiredBalance Minimum amount that a customer must have in an account in order to receive some sort of service, such as keeping the account open or receive interest.
 * @param accountHolderAddressLine1 Address of the Payer/Payee - Alternate address line for the account.
 * @param accountHolderAddressLine2 Address of the Payer/Payee - Alternate address line for the account.
 * @param accountHolderStreetName Street name of the Payer/Payee - Alternate street name for the account.
 * @param town Town of the Payer/Payee - Alternate town for the account.
 * @param postCode The postcode of the account holder address.
 * @param countrySubDivision The country sub division, if any.
 * @param creditAccount Indicator whether or not the arrangement can be used in payment orders as credit account.
 * @param debitAccount Indicator whether or not the arrangement can be used in payment orders as debit account.
 * @param accountHolderCountry Country of the account holder
 * @param id Reference to the product of which the arrangement is an instantiation.
 * @param name The name of this particular product.
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
 * @param additions Extra information.
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
class CurrentAccount internal constructor(
    val debitCardItems: Set<DebitCardItem>,
    val bookedBalance: String?,
    val availableBalance: String?,
    val creditLimit: String?,
    @SuppressWarnings("ConstructorParameterNaming") val IBAN: String?,
    @SuppressWarnings("ConstructorParameterNaming") val BBAN: String?,
    @SuppressWarnings("ConstructorParameterNaming") val BIC: String?,
    val unMaskableAttributes: Set<MaskableAttribute>?,
    val currency: String?,
    val urgentTransferAllowed: Boolean?,
    val bankBranchCode: String?,
    val bankBranchCode2: String?,
    val accountInterestRate: BigDecimal?,
    val valueDateBalance: BigDecimal?,
    val creditLimitUsage: BigDecimal?,
    val creditLimitInterestRate: BigDecimal?,
    val creditLimitExpiryDate: OffsetDateTime?,
    val accruedInterest: BigDecimal?,
    val startDate: OffsetDateTime?,
    val minimumRequiredBalance: BigDecimal?,
    val accountHolderAddressLine1: String?,
    val accountHolderAddressLine2: String?,
    val accountHolderStreetName: String?,
    val town: String?,
    val postCode: String?,
    val countrySubDivision: String?,
    val accountHolderNames: String?,
    val accountHolderCountry: String?,
    val creditAccount: Boolean?,
    val debitAccount: Boolean?,
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
) {

    /**
     * A builder for this configuration class.
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function.
     */
    class Builder {

        /**
         * See [CurrentAccount.debitCardItems].
         */
        var debitCardItems: Set<DebitCardItem>? = null

        /**
         * See [CurrentAccount.bookedBalance].
         */
        var bookedBalance: String? = null

        /**
         * See [CurrentAccount.availableBalance].
         */
        var availableBalance: String? = null

        /**
         * See [CurrentAccount.creditLimit].
         */
        var creditLimit: String? = null

        /**
         * See [CurrentAccount.IBAN].
         */
        @SuppressWarnings("VariableNaming")
        var IBAN: String? = null

        /**
         * See [CurrentAccount.BBAN].
         */
        @SuppressWarnings("VariableNaming")
        var BBAN: String? = null

        /**
         * See [CurrentAccount.BIC].
         */
        @SuppressWarnings("VariableNaming")
        var BIC: String? = null

        /**
         * See [CurrentAccount.unMaskableAttributes].
         */
        var unMaskableAttributes: Set<MaskableAttribute>? = null

        /**
         * See [CurrentAccount.currency].
         */
        var currency: String? = null

        /**
         * See [CurrentAccount.urgentTransferAllowed].
         */
        var urgentTransferAllowed: Boolean? = null

        /**
         * See [CurrentAccount.bankBranchCode].
         */
        var bankBranchCode: String? = null

        /**
         * See [CurrentAccount.bankBranchCode2].
         */
        var bankBranchCode2: String? = null

        /**
         * See [CurrentAccount.accountInterestRate].
         */
        var accountInterestRate: BigDecimal? = null

        /**
         * See [CurrentAccount.valueDateBalance].
         */
        var valueDateBalance: BigDecimal? = null

        /**
         * See [CurrentAccount.creditLimitUsage].
         */
        var creditLimitUsage: BigDecimal? = null

        /**
         * See [CurrentAccount.creditLimitInterestRate].
         */
        var creditLimitInterestRate: BigDecimal? = null

        /**
         * See [CurrentAccount.creditLimitExpiryDate].
         */
        var creditLimitExpiryDate: OffsetDateTime? = null

        /**
         * See [CurrentAccount.accruedInterest].
         */
        var accruedInterest: BigDecimal? = null

        /**
         * See [CurrentAccount.startDate].
         */
        var startDate: OffsetDateTime? = null

        /**
         * See [CurrentAccount.minimumRequiredBalance].
         */
        var minimumRequiredBalance: BigDecimal? = null

        /**
         * See [CurrentAccount.accountHolderAddressLine1].
         */
        var accountHolderAddressLine1: String? = null

        /**
         * See [CurrentAccount.accountHolderAddressLine2].
         */
        var accountHolderAddressLine2: String? = null

        /**
         * See [CurrentAccount.accountHolderStreetName].
         */
        var accountHolderStreetName: String? = null

        /**
         * See [CurrentAccount.town].
         */
        var town: String? = null

        /**
         * See [CurrentAccount.postCode].
         */
        var postCode: String? = null

        /**
         * See [CurrentAccount.countrySubDivision].
         */
        var countrySubDivision: String? = null

        /**
         * See [CurrentAccount.accountHolderNames].
         */
        var accountHolderNames: String? = null

        /**
         * See [CurrentAccount.accountHolderCountry].
         */
        var accountHolderCountry: String? = null

        /**
         * See [CurrentAccount.creditAccount].
         */
        var creditAccount: Boolean? = null

        /**
         * See [CurrentAccount.debitAccount].
         */
        var debitAccount: Boolean? = null

        /**
         * See [CurrentAccount.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [CurrentAccount.crossCurrencyAllowed].
         */
        var crossCurrencyAllowed: Boolean? = null

        /**
         * See [CurrentAccount.id].
         */
        var id: String? = null

        /**
         * See [CurrentAccount.name].
         */
        var name: String? = null

        /**
         * See [CurrentAccount.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [CurrentAccount.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [CurrentAccount.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [CurrentAccount.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [CurrentAccount.parentId].
         */
        var parentId: String? = null

        /**
         * See [CurrentAccount.accountOpeningDate].
         */
        var accountOpeningDate: OffsetDateTime? = null

        /**
         * See [CurrentAccount.lastUpdateDate].
         */
        var lastUpdateDate: OffsetDateTime? = null

        /**
         * See [CurrentAccount.lastSyncDate].
         */
        var lastSyncDate: OffsetDateTime? = null

        /**
         * See [CurrentAccount.userPreferences].
         */
        var userPreferences: UserPreferences? = null

        /**
         * See [CurrentAccount.state].
         */
        var state: ProductState? = null

        /**
         * See [CurrentAccount.subArrangements].
         */
        var subArrangements: List<BaseProduct>? = null

        /**
         * See [CurrentAccount.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [CurrentAccount.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [CurrentAccount.displayName].
         */
        var displayName: String? = null

        /**
         * See [CurrentAccount.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [CurrentAccount.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [CurrentAccount.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [CurrentAccount.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [CurrentAccount.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [CurrentAccount.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [CurrentAccount.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * See [CurrentAccount.debitCardItems].
         */
        fun setDebitCards(debitCardItems: Set<DebitCardItem>) = apply {
            this.debitCardItems = debitCardItems
        }

        /**
         * See [CurrentAccount.bookedBalance].
         */
        fun setBookedBalance(bookedBalance: String?) = apply {
            this.bookedBalance = bookedBalance
        }

        /**
         * See [CurrentAccount.availableBalance].
         */
        fun setAvailableBalance(availableBalance: String?) = apply {
            this.availableBalance = availableBalance
        }

        /**
         * See [CurrentAccount.creditLimit].
         */
        fun setCreditLimit(creditLimit: String?) = apply {
            this.creditLimit = creditLimit
        }

        /**
         * See [CurrentAccount.IBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setIBAN(IBAN: String?) = apply {
            this.IBAN = IBAN
        }

        /**
         * See [CurrentAccount.BBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setBBAN(BBAN: String?) = apply {
            this.BBAN = BBAN
        }

        /**
         * See [CurrentAccount.BIC].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setBIC(BIC: String?) = apply {
            this.BIC = BIC
        }

        /**
         * See [CurrentAccount.unMaskableAttributes].
         */
        fun setUnMaskableAttributes(unMaskableAttributes: Set<MaskableAttribute>?) = apply {
            this.unMaskableAttributes = unMaskableAttributes
        }

        /**
         * See [CurrentAccount.currency].
         */
        fun setCurrency(currency: String?) = apply {
            this.currency = currency
        }

        /**
         * See [CurrentAccount.urgentTransferAllowed].
         */
        fun setUrgentTransferAllowed(urgentTransferAllowed: Boolean?) = apply {
            this.urgentTransferAllowed = urgentTransferAllowed
        }

        /**
         * See [CurrentAccount.bankBranchCode].
         */
        fun setBankBranchCode(bankBranchCode: String?) = apply {
            this.bankBranchCode = bankBranchCode
        }

        /**
         * See [CurrentAccount.bankBranchCode2].
         */
        fun setBankBranchCode2(bankBranchCode2: String?) = apply {
            this.bankBranchCode2 = bankBranchCode2
        }

        /**
         * See [CurrentAccount.accountInterestRate].
         */
        fun setAccountInterestRate(accountInterestRate: BigDecimal?) = apply {
            this.accountInterestRate = accountInterestRate
        }

        /**
         * See [CurrentAccount.valueDateBalance].
         */
        fun setValueDateBalance(valueDateBalance: BigDecimal?) = apply {
            this.valueDateBalance = valueDateBalance
        }

        /**
         * See [CurrentAccount.creditLimitUsage].
         */
        fun setCreditLimitUsage(creditLimitUsage: BigDecimal?) = apply {
            this.creditLimitUsage = creditLimitUsage
        }

        /**
         * See [CurrentAccount.creditLimitInterestRate].
         */
        fun setCreditLimitInterestRate(creditLimitInterestRate: BigDecimal?) = apply {
            this.creditLimitInterestRate = creditLimitInterestRate
        }

        /**
         * See [CurrentAccount.creditLimitExpiryDate].
         */
        fun setCreditLimitExpiryDate(creditLimitExpiryDate: OffsetDateTime?) = apply {
            this.creditLimitExpiryDate = creditLimitExpiryDate
        }

        /**
         * See [CurrentAccount.accruedInterest].
         */
        fun setAccruedInterest(accruedInterest: BigDecimal?) = apply {
            this.accruedInterest = accruedInterest
        }

        /**
         * See [CurrentAccount.startDate].
         */
        fun setStartDate(startDate: OffsetDateTime?) = apply {
            this.startDate = startDate
        }

        /**
         * See [CurrentAccount.minimumRequiredBalance].
         */
        fun setMinimumRequiredBalance(minimumRequiredBalance: BigDecimal?) = apply {
            this.minimumRequiredBalance = minimumRequiredBalance
        }

        /**
         * See [CurrentAccount.accountHolderAddressLine1].
         */
        fun setAccountHolderAddressLine1(accountHolderAddressLine1: String?) = apply {
            this.accountHolderAddressLine1 = accountHolderAddressLine1
        }

        /**
         * See [CurrentAccount.accountHolderAddressLine2].
         */
        fun setAccountHolderAddressLine2(accountHolderAddressLine2: String?) = apply {
            this.accountHolderAddressLine2 = accountHolderAddressLine2
        }

        /**
         * See [CurrentAccount.accountHolderStreetName].
         */
        fun setAccountHolderStreetName(accountHolderStreetName: String?) = apply {
            this.accountHolderStreetName = accountHolderStreetName
        }

        /**
         * See [CurrentAccount.town].
         */
        fun setTown(town: String?) = apply {
            this.town = town
        }

        /**
         * See [CurrentAccount.postCode].
         */
        fun setPostCode(postCode: String?) = apply {
            this.postCode = postCode
        }

        /**
         * See [CurrentAccount.countrySubDivision].
         */
        fun setCountrySubDivision(countrySubDivision: String?) = apply {
            this.countrySubDivision = countrySubDivision
        }

        /**
         * See [CurrentAccount.creditAccount].
         */
        fun setCreditAccount(creditAccount: Boolean?) = apply {
            this.creditAccount = creditAccount
        }

        /**
         * See [CurrentAccount.debitAccount].
         */
        fun setDebitAccount(debitAccount: Boolean?) = apply {
            this.debitAccount = debitAccount
        }

        /**
         * See [CurrentAccount.accountHolderNames].
         */
        fun setAccountHolderNames(accountHolderNames: String?) = apply {
            this.accountHolderNames = accountHolderNames
        }

        /**
         * See [CurrentAccount.accountHolderCountry].
         */
        fun setAccountHolderCountry(accountHolderCountry: String?) = apply {
            this.accountHolderCountry = accountHolderCountry
        }

        /**
         * See [CurrentAccount.id].
         */
        fun setId(id: String?) = apply {
            this.id = id
        }

        /**
         * See [CurrentAccount.name].
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [CurrentAccount.externalTransferAllowed].
         */
        fun setExternalTransferAllowed(externalTransferAllowed: Boolean?) = apply {
            this.externalTransferAllowed = externalTransferAllowed
        }

        /**
         * See [CurrentAccount.crossCurrencyAllowed].
         */
        fun setCrossCurrencyAllowed(crossCurrencyAllowed: Boolean?) = apply {
            this.crossCurrencyAllowed = crossCurrencyAllowed
        }

        /**
         * See [CurrentAccount.productKindName].
         */
        fun setProductKindName(productKindName: String?) = apply {
            this.productKindName = productKindName
        }

        /**
         * See [CurrentAccount.productTypeName].
         */
        fun setProductTypeName(productTypeName: String?) = apply {
            this.productTypeName = productTypeName
        }

        /**
         * See [CurrentAccount.bankAlias].
         */
        fun setBankAlias(bankAlias: String?) = apply {
            this.bankAlias = bankAlias
        }

        /**
         * See [CurrentAccount.sourceId].
         */
        fun setSourceId(sourceId: String?) = apply {
            this.sourceId = sourceId
        }

        /**
         * See [CurrentAccount.accountOpeningDate].
         */
        fun setAccountOpeningDate(accountOpeningDate: OffsetDateTime?) = apply {
            this.accountOpeningDate = accountOpeningDate
        }

        /**
         * See [CurrentAccount.lastUpdateDate].
         */
        fun setLastUpdateDate(lastUpdateDate: OffsetDateTime?) = apply {
            this.lastUpdateDate = lastUpdateDate
        }

        /**
         * See [CurrentAccount.lastSyncDate].
         */
        fun setLastSyncDate(lastSyncDate: OffsetDateTime?) = apply {
            this.lastSyncDate = lastSyncDate
        }

        /**
         * See [CurrentAccount.parentId].
         */
        fun setParentId(parentId: String?) = apply {
            this.parentId = parentId
        }

        /**
         * See [CurrentAccount.userPreferences].
         */
        fun setUserPreferences(userPreferences: UserPreferences?) = apply {
            this.userPreferences = userPreferences
        }

        /**
         * See [CurrentAccount.state].
         */
        fun setState(state: ProductState?) = apply {
            this.state = state
        }

        /**
         * See [CurrentAccount.subArrangements].
         */
        fun setSubArrangements(subArrangements: List<BaseProduct>?) = apply {
            this.subArrangements = subArrangements
        }

        /**
         * See [CurrentAccount.financialInstitutionId].
         */
        fun setFinancialInstitutionId(financialInstitutionId: Long?) = apply {
            this.financialInstitutionId = financialInstitutionId
        }

        /**
         * See [CurrentAccount.additions].
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * See [CurrentAccount.displayName].
         */
        fun setDisplayName(displayName: String?) = apply {
            this.displayName = displayName
        }

        /**
         * See [CurrentAccount.cardDetails].
         */
        fun setCardDetails(cardDetails: CardDetails?) =
            apply { this.cardDetails = cardDetails }

        /**
         * See [CurrentAccount.reservedAmount].
         */
        fun setReservedAmount(reservedAmount: BigDecimal?) =
            apply { this.reservedAmount = reservedAmount }

        /**
         * See [CurrentAccount.remainingPeriodicTransfers].
         */
        fun setRemainingPeriodicTransfers(remainingPeriodicTransfers: BigDecimal?) =
            apply { this.remainingPeriodicTransfers = remainingPeriodicTransfers }

        /**
         * See [CurrentAccount.nextClosingDate].
         */
        fun setNextClosingDate(nextClosingDate: LocalDate?) =
            apply { this.nextClosingDate = nextClosingDate }

        /**
         * See [CurrentAccount.overdueSince].
         */
        fun setOverdueSince(overdueSince: LocalDate?) =
            apply { this.overdueSince = overdueSince }

        /**
         * See [CurrentAccount.externalAccountStatus].
         */
        fun setExternalAccountStatus(externalAccountStatus: String?) =
            apply { this.externalAccountStatus = externalAccountStatus }

        /**
         * See [CurrentAccount.interestDetails].
         */
        fun setInterestDetails(interestDetails: InterestDetails?) =
            apply { this.interestDetails = interestDetails }

        /**
         * Builds an instance of [CurrentAccount]
         */
        fun build() = CurrentAccount(
            debitCardItems = requireNotNull(debitCardItems),
            bookedBalance = bookedBalance,
            availableBalance = availableBalance,
            creditLimit = creditLimit,
            IBAN = IBAN,
            BBAN = BBAN,
            BIC = BIC,
            unMaskableAttributes = unMaskableAttributes,
            currency = currency,
            urgentTransferAllowed = urgentTransferAllowed,
            bankBranchCode = bankBranchCode,
            bankBranchCode2 = bankBranchCode2,
            accountInterestRate = accountInterestRate,
            valueDateBalance = valueDateBalance,
            creditLimitUsage = creditLimitUsage,
            creditLimitInterestRate = creditLimitInterestRate,
            creditLimitExpiryDate = creditLimitExpiryDate,
            accruedInterest = accruedInterest,
            accountHolderNames = accountHolderNames,
            startDate = startDate,
            minimumRequiredBalance = minimumRequiredBalance,
            accountHolderAddressLine1 = accountHolderAddressLine1,
            accountHolderAddressLine2 = accountHolderAddressLine2,
            accountHolderStreetName = accountHolderStreetName,
            town = town,
            postCode = postCode,
            countrySubDivision = countrySubDivision,
            creditAccount = creditAccount,
            debitAccount = debitAccount,
            accountHolderCountry = accountHolderCountry,
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
 * DSL to create [CurrentAccount]
 */
fun CurrentAccount(block: CurrentAccount.Builder.() -> Unit) = CurrentAccount.Builder().apply(block).build()
