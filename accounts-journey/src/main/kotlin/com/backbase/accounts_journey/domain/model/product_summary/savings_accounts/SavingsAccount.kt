package com.backbase.accounts_journey.domain.model.product_summary.savings_accounts

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.product_summary.MaskableAttribute
import com.backbase.accounts_journey.domain.model.product_summary.UserPreferences
import com.backbase.accounts_journey.domain.model.product_summary.common.BaseProduct
import com.backbase.accounts_journey.domain.model.product_summary.common.CardDetails
import com.backbase.accounts_journey.domain.model.product_summary.common.InterestDetails
import com.backbase.accounts_journey.domain.model.product_summary.common.ProductState
import com.backbase.accounts_journey.domain.model.product_summary.common.TimeUnit
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.time.LocalDate

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param bookedBalance
 * @param availableBalance
 * @param accruedInterest
 * @param IBAN The International Bank Account Number. If specified, it must be a valid IBAN, otherwise an invalid value error could be raised.
 * @param BBAN Specifying the BBAN of the account - BBAN is short for Basic Bank Account Number. It represents a country-specific bank account number.
 * @param BIC Bank Identifier Code - international bank code that identifies particular banks worldwide
 * @param unMaskableAttributes An optional list of the maskable attributes that can be unmasked.
 * @param currency The alpha-3 code (complying with ISO 4217) of the currency that qualifies the amount.
 * @param urgentTransferAllowed Defines if urgent transfer is allowed.
 * @param bankBranchCode (This is to accomodate additional country specific fields like Sort Code in UK)
 * @param bankBranchCode2 (This is to accomodate additional country specific fields fedwire Routing Nmber)
 * @param accountInterestRate The annualized cost of credit or debt-capital computed as the percentage ratio of interest to the principal.
 * @param minimumRequiredBalance Minimum amount that a customer must have in an account in order to receive some sort of service, such as keeping the account open or receive interest.
 * @param startDate
 * @param termUnit
 * @param termNumber The number of times interest rate is paid on the settlement account.
 * @param maturityDate End term of a holding period.
 * @param maturityAmount Amount payable at the end of a holding period of a product (maturity date). For deposit all of the interest is usualy paid at maturity date (IF the term is shorter then one year).
 * @param autoRenewalIndicator Indicates whether or not an arrangement is to be continued after maturity automatically. Usually the product is renewed using the same principal and term unless renegotiation has taken place prior to expiration.
 * @param interestPaymentFrequencyUnit
 * @param interestPaymentFrequencyNumber
 * @param principalAmount
 * @param interestSettlementAccount Account that provides quick access to accumulated cash to facilitate daily settlements with other businesses.
 * @param accountHolderNames Party(s) with a relationship to the account.
 * @param valueDateBalance The balance of the account on a specific date that needs to be used for the calculation of interest. NB! If no date is specified (like for the book date balance) the current date can be assumed.
 * @param accountHolderAddressLine1 Address of the Payer/Payee - Alternate address line for the account.
 * @param accountHolderAddressLine2 Address of the Payer/Payee - Alternate address line for the account.
 * @param accountHolderStreetName Street name of the Payer/Payee - Alternate street name for the account.
 * @param town Town of the Payer/Payee - Alternate town for the account.
 * @param postCode
 * @param countrySubDivision
 * @param accountHolderCountry Country of the account holder
 * @param creditAccount Indicator whether or not the arrangement can be used in payment orders as credit account.
 * @param debitAccount Indicator whether or not the arrangement can be used in payment orders as debit account.
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
class SavingsAccount internal constructor(
    val bookedBalance: String?,
    val availableBalance: String?,
    val accruedInterest: BigDecimal?,
    @SuppressWarnings("ConstructorParameterNaming") val IBAN: String?,
    @SuppressWarnings("ConstructorParameterNaming") val BBAN: String?,
    @SuppressWarnings("ConstructorParameterNaming") val BIC: String?,
    val unMaskableAttributes: Set<MaskableAttribute>?,
    val currency: String?,
    val urgentTransferAllowed: Boolean?,
    val bankBranchCode: String?,
    val bankBranchCode2: String?,
    val accountInterestRate: BigDecimal?,
    val minimumRequiredBalance: BigDecimal?,
    val startDate: java.time.OffsetDateTime?,
    val termUnit: TimeUnit?,
    val termNumber: BigDecimal?,
    val maturityDate: java.time.OffsetDateTime?,
    val maturityAmount: BigDecimal?,
    val autoRenewalIndicator: Boolean?,
    val interestPaymentFrequencyUnit: TimeUnit?,
    val interestPaymentFrequencyNumber: BigDecimal?,
    val principalAmount: BigDecimal?,
    val interestSettlementAccount: String?,
    val accountHolderNames: String?,
    val valueDateBalance: BigDecimal?,
    val accountHolderAddressLine1: String?,
    val accountHolderAddressLine2: String?,
    val accountHolderStreetName: String?,
    val town: String?,
    val postCode: String?,
    val countrySubDivision: String?,
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
    val accountOpeningDate: java.time.OffsetDateTime?,
    val lastUpdateDate: java.time.OffsetDateTime?,
    val userPreferences: UserPreferences?,
    val state: ProductState?,
    val parentId: String?,
    val subArrangements: List<BaseProduct>?,
    val financialInstitutionId: Long?,
    val lastSyncDate: java.time.OffsetDateTime?,
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
     * A builder for [SavingsAccount].
     */
    class Builder {

        /**
         * See [SavingsAccount.bookedBalance].
         */
        var bookedBalance: String? = null

        /**
         * See [SavingsAccount.availableBalance].
         */
        var availableBalance: String? = null

        /**
         * See [SavingsAccount.accruedInterest].
         */
        var accruedInterest: BigDecimal? = null

        /**
         * See [SavingsAccount.IBAN].
         */
        @SuppressWarnings("VariableNaming")
        var IBAN: String? = null

        /**
         * See [SavingsAccount.BBAN].
         */
        @SuppressWarnings("VariableNaming")
        var BBAN: String? = null

        /**
         * See [SavingsAccount.BIC].
         */
        @SuppressWarnings("VariableNaming")
        var BIC: String? = null

        /**
         * See [SavingsAccount.unMaskableAttributes].
         */
        var unMaskableAttributes: Set<MaskableAttribute>? = null

        /**
         * See [SavingsAccount.currency].
         */
        var currency: String? = null

        /**
         * See [SavingsAccount.urgentTransferAllowed].
         */
        var urgentTransferAllowed: Boolean? = null

        /**
         * See [SavingsAccount.bankBranchCode].
         */
        var bankBranchCode: String? = null

        /**
         * See [SavingsAccount.bankBranchCode2].
         */
        var bankBranchCode2: String? = null

        /**
         * See [SavingsAccount.accountInterestRate].
         */
        var accountInterestRate: BigDecimal? = null

        /**
         * See [SavingsAccount.minimumRequiredBalance].
         */
        var minimumRequiredBalance: BigDecimal? = null

        /**
         * See [SavingsAccount.startDate].
         */
        var startDate: java.time.OffsetDateTime? = null

        /**
         * See [SavingsAccount.termUnit].
         */
        var termUnit: TimeUnit? = null

        /**
         * See [SavingsAccount.termNumber].
         */
        var termNumber: BigDecimal? = null

        /**
         * See [SavingsAccount.maturityDate].
         */
        var maturityDate: java.time.OffsetDateTime? = null

        /**
         * See [SavingsAccount.maturityAmount].
         */
        var maturityAmount: BigDecimal? = null

        /**
         * See [SavingsAccount.autoRenewalIndicator].
         */
        var autoRenewalIndicator: Boolean? = null

        /**
         * See [SavingsAccount.interestPaymentFrequencyUnit].
         */
        var interestPaymentFrequencyUnit: TimeUnit? = null

        /**
         * See [SavingsAccount.interestPaymentFrequencyNumber].
         */
        var interestPaymentFrequencyNumber: BigDecimal? = null

        /**
         * See [SavingsAccount.principalAmount].
         */
        var principalAmount: BigDecimal? = null

        /**
         * See [SavingsAccount.interestSettlementAccount].
         */
        var interestSettlementAccount: String? = null

        /**
         * See [SavingsAccount.accountHolderNames].
         */
        var accountHolderNames: String? = null

        /**
         * See [SavingsAccount.valueDateBalance].
         */
        var valueDateBalance: BigDecimal? = null

        /**
         * See [SavingsAccount.accountHolderAddressLine1].
         */
        var accountHolderAddressLine1: String? = null

        /**
         * See [SavingsAccount.accountHolderAddressLine2].
         */
        var accountHolderAddressLine2: String? = null

        /**
         * See [SavingsAccount.accountHolderStreetName].
         */
        var accountHolderStreetName: String? = null

        /**
         * See [SavingsAccount.town].
         */
        var town: String? = null

        /**
         * See [SavingsAccount.postCode].
         */
        var postCode: String? = null

        /**
         * See [SavingsAccount.countrySubDivision].
         */
        var countrySubDivision: String? = null

        /**
         * See [SavingsAccount.accountHolderCountry].
         */
        var accountHolderCountry: String? = null

        /**
         * See [SavingsAccount.creditAccount].
         */
        var creditAccount: Boolean? = null

        /**
         * See [SavingsAccount.debitAccount].
         */
        var debitAccount: Boolean? = null

        /**
         * See [SavingsAccount.id].
         */
        var id: String? = null

        /**
         * See [SavingsAccount.name].
         */
        var name: String? = null

        /**
         * See [SavingsAccount.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [SavingsAccount.crossCurrencyAllowed].
         */
        var crossCurrencyAllowed: Boolean? = null

        /**
         * See [SavingsAccount.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [SavingsAccount.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [SavingsAccount.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [SavingsAccount.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [SavingsAccount.accountOpeningDate].
         */
        var accountOpeningDate: java.time.OffsetDateTime? = null

        /**
         * See [SavingsAccount.lastUpdateDate].
         */
        var lastUpdateDate: java.time.OffsetDateTime? = null

        /**
         * See [SavingsAccount.userPreferences].
         */
        var userPreferences: UserPreferences? = null

        /**
         * See [SavingsAccount.state].
         */
        var state: ProductState? = null

        /**
         * See [SavingsAccount.parentId].
         */
        var parentId: String? = null

        /**
         * See [SavingsAccount.subArrangements].
         */
        var subArrangements: List<BaseProduct>? = null

        /**
         * See [SavingsAccount.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [SavingsAccount.lastSyncDate].
         */
        var lastSyncDate: java.time.OffsetDateTime? = null

        /**
         * See [SavingsAccount.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [SavingsAccount.displayName].
         */
        var displayName: String? = null

        /**
         * See [SavingsAccount.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [SavingsAccount.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [SavingsAccount.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [SavingsAccount.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [SavingsAccount.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [SavingsAccount.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [SavingsAccount.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * See [SavingsAccount.bookedBalance].
         */
        fun setBookedBalance(bookedBalance: String?) = apply { this.bookedBalance = bookedBalance }

        /**
         * See [SavingsAccount.availableBalance].
         */
        fun setAvailableBalance(availableBalance: String?) = apply { this.availableBalance = availableBalance }

        /**
         * See [SavingsAccount.accruedInterest].
         */
        fun setAccruedInterest(accruedInterest: BigDecimal?) =
            apply { this.accruedInterest = accruedInterest }

        /**
         * See [SavingsAccount.IBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setIBAN(IBAN: String?) = apply { this.IBAN = IBAN }

        /**
         * See [SavingsAccount.BBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setBBAN(BBAN: String?) = apply { this.BBAN = BBAN }

        /**
         * See [SavingsAccount.BIC].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setBIC(BIC: String?) = apply { this.BIC = BIC }

        /**
         * See [SavingsAccount.unMaskableAttributes].
         */
        fun setUnMaskableAttributes(unMaskableAttributes: Set<MaskableAttribute>?) =
            apply { this.unMaskableAttributes = unMaskableAttributes }

        /**
         * See [SavingsAccount.currency].
         */
        fun setCurrency(currency: String?) = apply { this.currency = currency }

        /**
         * See [SavingsAccount.urgentTransferAllowed].
         */
        fun setUrgentTransferAllowed(urgentTransferAllowed: Boolean?) =
            apply { this.urgentTransferAllowed = urgentTransferAllowed }

        /**
         * See [SavingsAccount.bankBranchCode].
         */
        fun setBankBranchCode(bankBranchCode: String?) = apply { this.bankBranchCode = bankBranchCode }

        /**
         * See [SavingsAccount.bankBranchCode2].
         */
        fun setBankBranchCode2(bankBranchCode2: String?) = apply { this.bankBranchCode2 = bankBranchCode2 }

        /**
         * See [SavingsAccount.accountInterestRate].
         */
        fun setAccountInterestRate(accountInterestRate: BigDecimal?) =
            apply { this.accountInterestRate = accountInterestRate }

        /**
         * See [SavingsAccount.minimumRequiredBalance].
         */
        fun setMinimumRequiredBalance(minimumRequiredBalance: BigDecimal?) =
            apply { this.minimumRequiredBalance = minimumRequiredBalance }

        /**
         * See [SavingsAccount.startDate].
         */
        fun setStartDate(startDate: java.time.OffsetDateTime?) = apply { this.startDate = startDate }

        /**
         * See [SavingsAccount.termUnit].
         */
        fun setTermUnit(termUnit: TimeUnit?) = apply { this.termUnit = termUnit }

        /**
         * See [SavingsAccount.termNumber].
         */
        fun setTermNumber(termNumber: BigDecimal?) = apply { this.termNumber = termNumber }

        /**
         * See [SavingsAccount.maturityDate].
         */
        fun setMaturityDate(maturityDate: java.time.OffsetDateTime?) = apply { this.maturityDate = maturityDate }

        /**
         * See [SavingsAccount.maturityAmount].
         */
        fun setMaturityAmount(maturityAmount: BigDecimal?) = apply { this.maturityAmount = maturityAmount }

        /**
         * See [SavingsAccount.autoRenewalIndicator].
         */
        fun setAutoRenewalIndicator(autoRenewalIndicator: Boolean?) =
            apply { this.autoRenewalIndicator = autoRenewalIndicator }

        /**
         * See [SavingsAccount.interestPaymentFrequencyUnit].
         */
        fun setInterestPaymentFrequencyUnit(interestPaymentFrequencyUnit: TimeUnit?) =
            apply { this.interestPaymentFrequencyUnit = interestPaymentFrequencyUnit }

        /**
         * See [SavingsAccount.interestPaymentFrequencyNumber].
         */
        fun setInterestPaymentFrequencyNumber(interestPaymentFrequencyNumber: BigDecimal?) =
            apply { this.interestPaymentFrequencyNumber = interestPaymentFrequencyNumber }

        /**
         * See [SavingsAccount.principalAmount].
         */
        fun setPrincipalAmount(principalAmount: BigDecimal?) =
            apply { this.principalAmount = principalAmount }

        /**
         * See [SavingsAccount.interestSettlementAccount].
         */
        fun setInterestSettlementAccount(interestSettlementAccount: String?) =
            apply { this.interestSettlementAccount = interestSettlementAccount }

        /**
         * See [SavingsAccount.accountHolderNames].
         */
        fun setAccountHolderNames(accountHolderNames: String?) =
            apply { this.accountHolderNames = accountHolderNames }

        /**
         * See [SavingsAccount.valueDateBalance].
         */
        fun setValueDateBalance(valueDateBalance: BigDecimal?) =
            apply { this.valueDateBalance = valueDateBalance }

        /**
         * See [SavingsAccount.accountHolderAddressLine1].
         */
        fun setAccountHolderAddressLine1(accountHolderAddressLine1: String?) =
            apply { this.accountHolderAddressLine1 = accountHolderAddressLine1 }

        /**
         * See [SavingsAccount.accountHolderAddressLine2].
         */
        fun setAccountHolderAddressLine2(accountHolderAddressLine2: String?) =
            apply { this.accountHolderAddressLine2 = accountHolderAddressLine2 }

        /**
         * See [SavingsAccount.accountHolderStreetName].
         */
        fun setAccountHolderStreetName(accountHolderStreetName: String?) =
            apply { this.accountHolderStreetName = accountHolderStreetName }

        /**
         * See [SavingsAccount.town].
         */
        fun setTown(town: String?) = apply { this.town = town }

        /**
         * See [SavingsAccount.postCode].
         */
        fun setPostCode(postCode: String?) = apply { this.postCode = postCode }

        /**
         * See [SavingsAccount.countrySubDivision].
         */
        fun setCountrySubDivision(countrySubDivision: String?) =
            apply { this.countrySubDivision = countrySubDivision }

        /**
         * See [SavingsAccount.accountHolderCountry].
         */
        fun setAccountHolderCountry(accountHolderCountry: String?) =
            apply { this.accountHolderCountry = accountHolderCountry }

        /**
         * See [SavingsAccount.creditAccount].
         */
        fun setCreditAccount(creditAccount: Boolean?) = apply { this.creditAccount = creditAccount }

        /**
         * See [SavingsAccount.debitAccount].
         */
        fun setDebitAccount(debitAccount: Boolean?) = apply { this.debitAccount = debitAccount }

        /**
         * See [SavingsAccount.id].
         */
        fun setId(id: String?) = apply { this.id = id }

        /**
         * See [SavingsAccount.name].
         */
        fun setName(name: String?) = apply { this.name = name }

        /**
         * See [SavingsAccount.externalTransferAllowed].
         */
        fun setExternalTransferAllowed(externalTransferAllowed: Boolean?) =
            apply { this.externalTransferAllowed = externalTransferAllowed }

        /**
         * See [SavingsAccount.crossCurrencyAllowed].
         */
        fun setCrossCurrencyAllowed(crossCurrencyAllowed: Boolean?) =
            apply { this.crossCurrencyAllowed = crossCurrencyAllowed }

        /**
         * See [SavingsAccount.productKindName].
         */
        fun setProductKindName(productKindName: String?) = apply { this.productKindName = productKindName }

        /**
         * See [SavingsAccount.productTypeName].
         */
        fun setProductTypeName(productTypeName: String?) = apply { this.productTypeName = productTypeName }

        /**
         * See [SavingsAccount.bankAlias].
         */
        fun setBankAlias(bankAlias: String?) = apply { this.bankAlias = bankAlias }

        /**
         * See [SavingsAccount.sourceId].
         */
        fun setSourceId(sourceId: String?) = apply { this.sourceId = sourceId }

        /**
         * See [SavingsAccount.accountOpeningDate].
         */
        fun setAccountOpeningDate(accountOpeningDate: java.time.OffsetDateTime?) =
            apply { this.accountOpeningDate = accountOpeningDate }

        /**
         * See [SavingsAccount.lastUpdateDate].
         */
        fun setLastUpdateDate(lastUpdateDate: java.time.OffsetDateTime?) =
            apply { this.lastUpdateDate = lastUpdateDate }

        /**
         * See [SavingsAccount.userPreferences].
         */
        fun setUserPreferences(userPreferences: UserPreferences?) = apply { this.userPreferences = userPreferences }

        /**
         * See [SavingsAccount.state].
         */
        fun setState(state: ProductState?) = apply { this.state = state }

        /**
         * See [SavingsAccount.parentId].
         */
        fun setParentId(parentId: String?) = apply { this.parentId = parentId }

        /**
         * See [SavingsAccount.subArrangements].
         */
        fun setSubArrangements(subArrangements: List<BaseProduct>?) = apply {
            this.subArrangements = subArrangements
        }

        /**
         * See [SavingsAccount.financialInstitutionId].
         */
        fun setFinancialInstitutionId(financialInstitutionId: Long?) =
            apply { this.financialInstitutionId = financialInstitutionId }

        /**
         * See [SavingsAccount.lastSyncDate].
         */
        fun setLastSyncDate(lastSyncDate: java.time.OffsetDateTime?) = apply { this.lastSyncDate = lastSyncDate }

        /**
         * See [SavingsAccount.additions].
         */
        fun setAdditions(additions: Map<String, String>?) =
            apply { this.additions = additions }

        /**
         * See [SavingsAccount.displayName].
         */
        fun setDisplayName(displayName: String?) =
            apply { this.displayName = displayName }

        /**
         * See [SavingsAccount.cardDetails].
         */
        fun setCardDetails(cardDetails: CardDetails?) =
            apply { this.cardDetails = cardDetails }

        /**
         * See [SavingsAccount.reservedAmount].
         */
        fun setReservedAmount(reservedAmount: BigDecimal?) =
            apply { this.reservedAmount = reservedAmount }

        /**
         * See [SavingsAccount.remainingPeriodicTransfers].
         */
        fun setRemainingPeriodicTransfers(remainingPeriodicTransfers: BigDecimal?) =
            apply { this.remainingPeriodicTransfers = remainingPeriodicTransfers }

        /**
         * See [SavingsAccount.nextClosingDate].
         */
        fun setNextClosingDate(nextClosingDate: LocalDate?) =
            apply { this.nextClosingDate = nextClosingDate }

        /**
         * See [SavingsAccount.overdueSince].
         */
        fun setOverdueSince(overdueSince: LocalDate?) =
            apply { this.overdueSince = overdueSince }

        /**
         * See [SavingsAccount.externalAccountStatus].
         */
        fun setExternalAccountStatus(externalAccountStatus: String?) =
            apply { this.externalAccountStatus = externalAccountStatus }

        /**
         * See [SavingsAccount.interestDetails].
         */
        fun setInterestDetails(interestDetails: InterestDetails?) =
            apply { this.interestDetails = interestDetails }

        /**
         * Builds an instance of [SavingsAccount].
         */
        @Suppress("LongMethod")
        fun build() = SavingsAccount(
            bookedBalance = bookedBalance,
            availableBalance = availableBalance,
            accruedInterest = accruedInterest,
            IBAN = IBAN,
            BBAN = BBAN,
            BIC = BIC,
            unMaskableAttributes = unMaskableAttributes,
            currency = currency,
            urgentTransferAllowed = urgentTransferAllowed,
            bankBranchCode = bankBranchCode,
            bankBranchCode2 = bankBranchCode2,
            accountInterestRate = accountInterestRate,
            minimumRequiredBalance = minimumRequiredBalance,
            startDate = startDate,
            termUnit = termUnit,
            termNumber = termNumber,
            maturityDate = maturityDate,
            maturityAmount = maturityAmount,
            autoRenewalIndicator = autoRenewalIndicator,
            interestPaymentFrequencyUnit = interestPaymentFrequencyUnit,
            interestPaymentFrequencyNumber = interestPaymentFrequencyNumber,
            principalAmount = principalAmount,
            interestSettlementAccount = interestSettlementAccount,
            accountHolderNames = accountHolderNames,
            valueDateBalance = valueDateBalance,
            accountHolderAddressLine1 = accountHolderAddressLine1,
            accountHolderAddressLine2 = accountHolderAddressLine2,
            accountHolderStreetName = accountHolderStreetName,
            town = town,
            postCode = postCode,
            countrySubDivision = countrySubDivision,
            accountHolderCountry = accountHolderCountry,
            creditAccount = creditAccount,
            debitAccount = debitAccount,
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
 * Builds an instance of [SavingsAccount] with the [initializer] parameters.
 */
@Suppress("FunctionName") // DSL initializer
fun SavingsAccount(initializer: SavingsAccount.Builder.() -> Unit): SavingsAccount {
    return SavingsAccount.Builder().apply(initializer).build()
}
