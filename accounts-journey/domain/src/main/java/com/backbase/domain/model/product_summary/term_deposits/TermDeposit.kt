package com.backbase.domain.model.product_summary.term_deposits

import com.backbase.domain.model.product_summary.MaskableAttribute
import com.backbase.domain.model.product_summary.UserPreferences
import com.backbase.domain.model.product_summary.common.BaseProduct
import com.backbase.domain.model.product_summary.common.CardDetails
import com.backbase.domain.model.product_summary.common.InterestDetails
import com.backbase.domain.model.product_summary.common.ProductState
import com.backbase.domain.model.product_summary.common.TimeUnit
import dev.drewhamilton.poko.Poko
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime

/**
 * Created by Backbase R&D B.V. on 19/09/2023.
 *
 * @param bookedBalance
 * @param principalAmount
 * @param accruedInterest
 * @param IBAN The International Bank Account Number. If specified, it must be a valid IBAN, otherwise an invalid value error could be raised.
 * @param BBAN Specifying the BBAN of the account - BBAN is short for Basic Bank Account Number. It represents a country-specific bank account number.
 * @param unMaskableAttributes An optional list of the maskable attributes that can be unmasked.
 * @param currency The alpha-3 code (complying with ISO 4217) of the currency that qualifies the amount.
 * @param urgentTransferAllowed Defines if urgent transfer is allowed.
 * @param productNumber The number identifying the contract.
 * @param accountInterestRate The annualized cost of credit or debt-capital computed as the percentage ratio of interest to the principal.
 * @param startDate
 * @param termUnit
 * @param termNumber The number of times interest rate is paid on the settlement account.
 * @param maturityDate End term of a holding period.
 * @param maturityAmount Amount payable at the end of a holding period of a product (maturity date). For deposit all of the interest is usualy paid at maturity date (IF the term is shorter then one year).
 * @param autoRenewalIndicator Indicates whether or not an arrangement is to be continued after maturity automatically. Usually the product is renewed using the same principal and term unless renegotiation has taken place prior to expiration.
 * @param interestPaymentFrequencyUnit
 * @param interestPaymentFrequencyNumber
 * @param interestSettlementAccount Account that provides quick access to accumulated cash to facilitate daily settlements with other businesses.
 * @param valueDateBalance The balance of the account on a specific date that needs to be used for the calculation of interest. NB! If no date is specified (like for the book date balance) the current date can be assumed.
 * @param accountHolderNames Party(s) with a relationship to the account.
 * @param outstandingPrincipalAmount This IS the value date balance of the arrangement.
 * @param creditAccount Indicator whether or not the arrangement can be used in payment orders as credit account.
 * @param debitAccount Indicator whether or not the arrangement can be used in payment orders as debit account.
 * @param minimumRequiredBalance Minimum amount that a customer must have in an account in order to receive some sort of service, such as keeping the account open or receive interest.
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
 * @param subArrangements A list of arrangements whose parent is this product.
 * @param parentId Reference to the parent of the arrangement.
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
 * @param bankBranchCode (This is to accomodate additional country specific fields like Sort Code in UK)
 * @param bankBranchCode2 (This is to accomodate additional country specific fields fedwire Routing Nmber)
 * @param availableBalance
 * @param creditLimit
 * @param minimumPayment The minimum payment set a percentage of balance, or a fixed cash amount.
 * @param minimumPaymentDueDate Minimum Payment Due Date shown on your monthly statement to remain in good standing.
 */
@SuppressWarnings("LongParameterList", "MaxLineLength")
@Poko
class TermDeposit internal constructor(
    val bookedBalance: String?,
    val principalAmount: BigDecimal?,
    val accruedInterest: BigDecimal?,
    @SuppressWarnings("ConstructorParameterNaming") val IBAN: String?,
    @SuppressWarnings("ConstructorParameterNaming") val BBAN: String?,
    val unMaskableAttributes: Set<MaskableAttribute>?,
    val currency: String?,
    val urgentTransferAllowed: Boolean?,
    val productNumber: String?,
    val accountInterestRate: BigDecimal?,
    val startDate: OffsetDateTime?,
    val termUnit: TimeUnit?,
    val termNumber: BigDecimal?,
    val maturityDate: OffsetDateTime?,
    val maturityAmount: BigDecimal?,
    val autoRenewalIndicator: Boolean?,
    val interestPaymentFrequencyUnit: TimeUnit?,
    val interestPaymentFrequencyNumber: BigDecimal?,
    val interestSettlementAccount: String?,
    val valueDateBalance: BigDecimal?,
    val accountHolderNames: String?,
    val outstandingPrincipalAmount: BigDecimal?,
    val creditAccount: Boolean?,
    val debitAccount: Boolean?,
    val minimumRequiredBalance: BigDecimal?,
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
    val subArrangements: List<BaseProduct>?,
    val parentId: String?,
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
    val bankBranchCode: String?,
    val bankBranchCode2: String?,
    val availableBalance: String?,
    val creditLimit: String?,
    val minimumPayment: BigDecimal?,
    val minimumPaymentDueDate: OffsetDateTime?,
) {

    /**
     * A builder for [TermDeposit].
     */
    class Builder {

        /**
         * See [TermDeposit.bookedBalance].
         */
        var bookedBalance: String? = null

        /**
         * See [TermDeposit.principalAmount].
         */
        var principalAmount: BigDecimal? = null

        /**
         * See [TermDeposit.accruedInterest].
         */
        var accruedInterest: BigDecimal? = null

        /**
         * See [TermDeposit.IBAN].
         */
        @SuppressWarnings("VariableNaming")
        var IBAN: String? = null

        /**
         * See [TermDeposit.BBAN].
         */
        @SuppressWarnings("VariableNaming")
        var BBAN: String? = null

        /**
         * See [TermDeposit.unMaskableAttributes].
         */
        var unMaskableAttributes: Set<MaskableAttribute>? = null

        /**
         * See [TermDeposit.currency].
         */
        var currency: String? = null

        /**
         * See [TermDeposit.urgentTransferAllowed].
         */
        var urgentTransferAllowed: Boolean? = null

        /**
         * See [TermDeposit.productNumber].
         */
        var productNumber: String? = null

        /**
         * See [TermDeposit.accountInterestRate].
         */
        var accountInterestRate: BigDecimal? = null

        /**
         * See [TermDeposit.startDate].
         */
        var startDate: OffsetDateTime? = null

        /**
         * See [TermDeposit.termUnit].
         */
        var termUnit: TimeUnit? = null

        /**
         * See [TermDeposit.termNumber].
         */
        var termNumber: BigDecimal? = null

        /**
         * See [TermDeposit.maturityDate].
         */
        var maturityDate: OffsetDateTime? = null

        /**
         * See [TermDeposit.maturityAmount].
         */
        var maturityAmount: BigDecimal? = null

        /**
         * See [TermDeposit.autoRenewalIndicator].
         */
        var autoRenewalIndicator: Boolean? = null

        /**
         * See [TermDeposit.interestPaymentFrequencyUnit].
         */
        var interestPaymentFrequencyUnit: TimeUnit? = null

        /**
         * See [TermDeposit.interestPaymentFrequencyNumber].
         */
        var interestPaymentFrequencyNumber: BigDecimal? = null

        /**
         * See [TermDeposit.interestSettlementAccount].
         */
        var interestSettlementAccount: String? = null

        /**
         * See [TermDeposit.valueDateBalance].
         */
        var valueDateBalance: BigDecimal? = null

        /**
         * See [TermDeposit.accountHolderNames].
         */
        var accountHolderNames: String? = null

        /**
         * See [TermDeposit.outstandingPrincipalAmount].
         */
        var outstandingPrincipalAmount: BigDecimal? = null

        /**
         * See [TermDeposit.creditAccount].
         */
        var creditAccount: Boolean? = null

        /**
         * See [TermDeposit.debitAccount].
         */
        var debitAccount: Boolean? = null

        /**
         * See [TermDeposit.minimumRequiredBalance].
         */
        var minimumRequiredBalance: BigDecimal? = null

        /**
         * See [TermDeposit.id].
         */
        var id: String? = null

        /**
         * See [TermDeposit.name].
         */
        var name: String? = null

        /**
         * See [TermDeposit.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [TermDeposit.crossCurrencyAllowed].
         */
        var crossCurrencyAllowed: Boolean? = null

        /**
         * See [TermDeposit.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [TermDeposit.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [TermDeposit.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [TermDeposit.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [TermDeposit.accountOpeningDate].
         */
        var accountOpeningDate: OffsetDateTime? = null

        /**
         * See [TermDeposit.lastUpdateDate].
         */
        var lastUpdateDate: OffsetDateTime? = null

        /**
         * See [TermDeposit.userPreferences].
         */
        var userPreferences: UserPreferences? = null

        /**
         * See [TermDeposit.state].
         */
        var state: ProductState? = null

        /**
         * See [TermDeposit.subArrangements].
         */
        var subArrangements: List<BaseProduct>? = null

        /**
         * See [TermDeposit.parentId].
         */
        var parentId: String? = null

        /**
         * See [TermDeposit.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [TermDeposit.lastSyncDate].
         */
        var lastSyncDate: OffsetDateTime? = null

        /**
         * See [TermDeposit.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [TermDeposit.displayName].
         */
        var displayName: String? = null

        /**
         * See [TermDeposit.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [TermDeposit.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [TermDeposit.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [TermDeposit.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [TermDeposit.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [TermDeposit.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [TermDeposit.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * See [TermDeposit.bankBranchCode].
         */
        var bankBranchCode: String? = null

        /**
         * See [TermDeposit.bankBranchCode2].
         */
        var bankBranchCode2: String? = null

        /**
         * See [TermDeposit.availableBalance].
         */
        var availableBalance: String? = null

        /**
         * See [TermDeposit.creditLimit].
         */
        var creditLimit: String? = null

        /**
         * See [TermDeposit.minimumPayment].
         */
        var minimumPayment: BigDecimal? = null

        /**
         * See [TermDeposit.minimumPaymentDueDate].
         */
        var minimumPaymentDueDate: OffsetDateTime? = null

        /**
         * See [TermDeposit.bookedBalance].
         */
        fun setBookedBalance(bookedBalance: String?) = apply { this.bookedBalance = bookedBalance }

        /**
         * See [TermDeposit.principalAmount].
         */
        fun setPrincipalAmount(principalAmount: BigDecimal?) =
            apply { this.principalAmount = principalAmount }

        /**
         * See [TermDeposit.accruedInterest].
         */
        fun setAccruedInterest(accruedInterest: BigDecimal?) =
            apply { this.accruedInterest = accruedInterest }

        /**
         * See [TermDeposit.IBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setIBAN(IBAN: String?) = apply { this.IBAN = IBAN }

        /**
         * See [TermDeposit.BBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setBBAN(BBAN: String?) = apply { this.BBAN = BBAN }

        /**
         * See [TermDeposit.unMaskableAttributes].
         */
        fun setUnMaskableAttributes(unMaskableAttributes: Set<MaskableAttribute>?) =
            apply { this.unMaskableAttributes = unMaskableAttributes }

        /**
         * See [TermDeposit.currency].
         */
        fun setCurrency(currency: String?) = apply { this.currency = currency }

        /**
         * See [TermDeposit.urgentTransferAllowed].
         */
        fun setUrgentTransferAllowed(urgentTransferAllowed: Boolean?) =
            apply { this.urgentTransferAllowed = urgentTransferAllowed }

        /**
         * See [TermDeposit.productNumber].
         */
        fun setProductNumber(productNumber: String?) = apply { this.productNumber = productNumber }

        /**
         * See [TermDeposit.accountInterestRate].
         */
        fun setAccountInterestRate(accountInterestRate: BigDecimal?) =
            apply { this.accountInterestRate = accountInterestRate }

        /**
         * See [TermDeposit.startDate].
         */
        fun setStartDate(startDate: OffsetDateTime?) = apply { this.startDate = startDate }

        /**
         * See [TermDeposit.termUnit].
         */
        fun setTermUnit(termUnit: TimeUnit?) = apply { this.termUnit = termUnit }

        /**
         * See [TermDeposit.termNumber].
         */
        fun setTermNumber(termNumber: BigDecimal?) = apply { this.termNumber = termNumber }

        /**
         * See [TermDeposit.maturityDate].
         */
        fun setMaturityDate(maturityDate: OffsetDateTime?) =
            apply { this.maturityDate = maturityDate }

        /**
         * See [TermDeposit.maturityAmount].
         */
        fun setMaturityAmount(maturityAmount: BigDecimal?) =
            apply { this.maturityAmount = maturityAmount }

        /**
         * See [TermDeposit.autoRenewalIndicator].
         */
        fun setAutoRenewalIndicator(autoRenewalIndicator: Boolean?) =
            apply { this.autoRenewalIndicator = autoRenewalIndicator }

        /**
         * See [TermDeposit.interestPaymentFrequencyUnit].
         */
        fun setInterestPaymentFrequencyUnit(interestPaymentFrequencyUnit: TimeUnit?) =
            apply { this.interestPaymentFrequencyUnit = interestPaymentFrequencyUnit }

        /**
         * See [TermDeposit.interestPaymentFrequencyNumber].
         */
        fun setInterestPaymentFrequencyNumber(interestPaymentFrequencyNumber: BigDecimal?) =
            apply { this.interestPaymentFrequencyNumber = interestPaymentFrequencyNumber }

        /**
         * See [TermDeposit.interestSettlementAccount].
         */
        fun setInterestSettlementAccount(interestSettlementAccount: String?) =
            apply { this.interestSettlementAccount = interestSettlementAccount }

        /**
         * See [TermDeposit.valueDateBalance].
         */
        fun setValueDateBalance(valueDateBalance: BigDecimal?) =
            apply { this.valueDateBalance = valueDateBalance }

        /**
         * See [TermDeposit.accountHolderNames].
         */
        fun setAccountHolderNames(accountHolderNames: String?) =
            apply { this.accountHolderNames = accountHolderNames }

        /**
         * See [TermDeposit.outstandingPrincipalAmount].
         */
        fun setOutstandingPrincipalAmount(outstandingPrincipalAmount: BigDecimal?) =
            apply { this.outstandingPrincipalAmount = outstandingPrincipalAmount }

        /**
         * See [TermDeposit.creditAccount].
         */
        fun setCreditAccount(creditAccount: Boolean?) = apply { this.creditAccount = creditAccount }

        /**
         * See [TermDeposit.debitAccount].
         */
        fun setDebitAccount(debitAccount: Boolean?) = apply { this.debitAccount = debitAccount }

        /**
         * See [TermDeposit.minimumRequiredBalance].
         */
        fun setMinimumRequiredBalance(minimumRequiredBalance: BigDecimal?) =
            apply { this.minimumRequiredBalance = minimumRequiredBalance }

        /**
         * See [TermDeposit.id].
         */
        fun setId(id: String?) = apply { this.id = id }

        /**
         * See [TermDeposit.name].
         */
        fun setName(name: String?) = apply { this.name = name }

        /**
         * See [TermDeposit.externalTransferAllowed].
         */
        fun setExternalTransferAllowed(externalTransferAllowed: Boolean?) =
            apply { this.externalTransferAllowed = externalTransferAllowed }

        /**
         * See [TermDeposit.crossCurrencyAllowed].
         */
        fun setCrossCurrencyAllowed(crossCurrencyAllowed: Boolean?) =
            apply { this.crossCurrencyAllowed = crossCurrencyAllowed }

        /**
         * See [TermDeposit.productKindName].
         */
        fun setProductKindName(productKindName: String?) =
            apply { this.productKindName = productKindName }

        /**
         * See [TermDeposit.productTypeName].
         */
        fun setProductTypeName(productTypeName: String?) =
            apply { this.productTypeName = productTypeName }

        /**
         * See [TermDeposit.bankAlias].
         */
        fun setBankAlias(bankAlias: String?) = apply { this.bankAlias = bankAlias }

        /**
         * See [TermDeposit.sourceId].
         */
        fun setSourceId(sourceId: String?) = apply { this.sourceId = sourceId }

        /**
         * See [TermDeposit.accountOpeningDate].
         */
        fun setAccountOpeningDate(accountOpeningDate: OffsetDateTime?) =
            apply { this.accountOpeningDate = accountOpeningDate }

        /**
         * See [TermDeposit.lastUpdateDate].
         */
        fun setLastUpdateDate(lastUpdateDate: OffsetDateTime?) =
            apply { this.lastUpdateDate = lastUpdateDate }

        /**
         * See [TermDeposit.userPreferences].
         */
        fun setUserPreferences(userPreferences: UserPreferences?) =
            apply { this.userPreferences = userPreferences }

        /**
         * See [TermDeposit.state].
         */
        fun setState(state: ProductState?) = apply { this.state = state }

        /**
         * See [TermDeposit.subArrangements].
         */
        fun setSubArrangements(subArrangements: List<BaseProduct>?) = apply {
            this.subArrangements = subArrangements
        }

        /**
         * See [TermDeposit.parentId].
         */
        fun setParentId(parentId: String?) = apply { this.parentId = parentId }

        /**
         * See [TermDeposit.financialInstitutionId].
         */
        fun setFinancialInstitutionId(financialInstitutionId: Long?) =
            apply { this.financialInstitutionId = financialInstitutionId }

        /**
         * See [TermDeposit.lastSyncDate].
         */
        fun setLastSyncDate(lastSyncDate: OffsetDateTime?) =
            apply { this.lastSyncDate = lastSyncDate }

        /**
         * See [TermDeposit.additions].
         */
        fun setAdditions(additions: Map<String, String>?) =
            apply { this.additions = additions }

        /**
         * See [TermDeposit.displayName].
         */
        fun setDisplayName(displayName: String?) =
            apply { this.displayName = displayName }

        /**
         * See [TermDeposit.cardDetails].
         */
        fun setCardDetails(cardDetails: CardDetails?) =
            apply { this.cardDetails = cardDetails }

        /**
         * See [TermDeposit.reservedAmount].
         */
        fun setReservedAmount(reservedAmount: BigDecimal?) =
            apply { this.reservedAmount = reservedAmount }

        /**
         * See [TermDeposit.remainingPeriodicTransfers].
         */
        fun setRemainingPeriodicTransfers(remainingPeriodicTransfers: BigDecimal?) =
            apply { this.remainingPeriodicTransfers = remainingPeriodicTransfers }

        /**
         * See [TermDeposit.nextClosingDate].
         */
        fun setNextClosingDate(nextClosingDate: LocalDate?) =
            apply { this.nextClosingDate = nextClosingDate }

        /**
         * See [TermDeposit.overdueSince].
         */
        fun setOverdueSince(overdueSince: LocalDate?) =
            apply { this.overdueSince = overdueSince }

        /**
         * See [TermDeposit.externalAccountStatus].
         */
        fun setExternalAccountStatus(externalAccountStatus: String?) =
            apply { this.externalAccountStatus = externalAccountStatus }

        /**
         * See [TermDeposit.interestDetails].
         */
        fun setInterestDetails(interestDetails: InterestDetails?) =
            apply { this.interestDetails = interestDetails }

        /**
         * See [TermDeposit.bankBranchCode].
         */
        fun setBankBranchCode(bankBranchCode: String?) = apply {
            this.bankBranchCode = bankBranchCode
        }

        /**
         * See [TermDeposit.bankBranchCode2].
         */
        fun setBankBranchCode2(bankBranchCode2: String?) = apply {
            this.bankBranchCode2 = bankBranchCode2
        }

        /**
         * See [TermDeposit.availableBalance].
         */
        fun setAvailableBalance(availableBalance: String?) =
            apply { this.availableBalance = availableBalance }

        /**
         * See [TermDeposit.creditLimit].
         */
        fun setCreditLimit(creditLimit: String?) = apply { this.creditLimit = creditLimit }

        /**
         * See [TermDeposit.minimumPayment].
         */
        fun setMinimumPayment(minimumPayment: BigDecimal?) =
            apply { this.minimumPayment = minimumPayment }

        /**
         * See [TermDeposit.minimumPaymentDueDate].
         */
        fun setMinimumPaymentDueDate(minimumPaymentDueDate: OffsetDateTime?) =
            apply { this.minimumPaymentDueDate = minimumPaymentDueDate }

        /**
         * Builds an instance of [TermDeposit].
         */
        fun build() = TermDeposit(
            bookedBalance = bookedBalance,
            principalAmount = principalAmount,
            accruedInterest = accruedInterest,
            IBAN = IBAN,
            BBAN = BBAN,
            unMaskableAttributes = unMaskableAttributes,
            currency = currency,
            urgentTransferAllowed = urgentTransferAllowed,
            productNumber = productNumber,
            accountInterestRate = accountInterestRate,
            startDate = startDate,
            termUnit = termUnit,
            termNumber = termNumber,
            maturityDate = maturityDate,
            maturityAmount = maturityAmount,
            autoRenewalIndicator = autoRenewalIndicator,
            interestPaymentFrequencyUnit = interestPaymentFrequencyUnit,
            interestPaymentFrequencyNumber = interestPaymentFrequencyNumber,
            interestSettlementAccount = interestSettlementAccount,
            valueDateBalance = valueDateBalance,
            accountHolderNames = accountHolderNames,
            outstandingPrincipalAmount = outstandingPrincipalAmount,
            creditAccount = creditAccount,
            debitAccount = debitAccount,
            minimumRequiredBalance = minimumRequiredBalance,
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
            subArrangements = subArrangements,
            parentId = parentId,
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
            bankBranchCode = bankBranchCode,
            bankBranchCode2 = bankBranchCode2,
            availableBalance = availableBalance,
            creditLimit = creditLimit,
            minimumPayment = minimumPayment,
            minimumPaymentDueDate = minimumPaymentDueDate,
        )
    }
}

/**
 * Builds an instance of [TermDeposit] with the [initializer] parameters.
 */
@Suppress("FunctionName") // DSL initializer
fun TermDeposit(initializer: TermDeposit.Builder.() -> Unit): TermDeposit {
    return TermDeposit.Builder().apply(initializer).build()
}
