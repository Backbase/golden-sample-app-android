package com.backbase.domain.model.product_summary.loan

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
 * @param currency
 * @param urgentTransferAllowed Defines if urgent transfer is allowed.
 * @param productNumber The number identifying the contract.
 * @param accountInterestRate The annualized cost of credit or debt-capital computed as the percentage ratio of interest to the principal.
 * @param termUnit
 * @param termNumber The number of times interest rate is paid on the settlement account.
 * @param outstandingPrincipalAmount This IS the value date balance of the arrangement.
 * @param monthlyInstalmentAmount A fixed payment amount paid by a borrower to the bank at a specified date each calendar month.
 * @param amountInArrear The part of a debt that is overdue after missing one or more required payments. The amount of the arrears is the amount accrued from the date on which the first missed payment was due.
 * @param interestSettlementAccount Account that provides quick access to accumulated cash to facilitate daily settlements with other businesses.
 * @param accruedInterest
 * @param accountHolderNames Party(s) with a relationship to the account.
 * @param maturityDate End term of a holding period.
 * @param valueDateBalance The balance of the account on a specific date that needs to be used for the calculation of interest. NB! If no date is specified (like for the book date balance) the current date can be assumed.
 * @param creditAccount Indicator whether or not the arrangement can be used in payment orders as credit account.
 * @param debitAccount Indicator whether or not the arrangement can be used in payment orders as debit account.
 * @param IBAN The International Bank Account Number. If specified, it must be a valid IBAN, otherwise an invalid value error could be raised.
 * @param BBAN Specifying the BBAN of the account - BBAN is short for Basic Bank Account Number. It represents a country-specific bank account number.
 * @param unMaskableAttributes An optional list of the maskable attributes that can be unmasked.
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
 * @param financialInstitutionId Financial institution ID
 * @param subArrangements A list of arrangements whose parent is this product.
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
@Suppress("LongParameterList", "MaxLineLength")
@Poko
class Loan internal constructor(
    val bookedBalance: String?,
    val principalAmount: BigDecimal?,
    val currency: String?,
    val urgentTransferAllowed: Boolean?,
    val productNumber: String?,
    val accountInterestRate: BigDecimal?,
    val termUnit: TimeUnit?,
    val termNumber: BigDecimal?,
    val outstandingPrincipalAmount: BigDecimal?,
    val monthlyInstalmentAmount: BigDecimal?,
    val amountInArrear: BigDecimal?,
    val interestSettlementAccount: String?,
    val accruedInterest: BigDecimal?,
    val accountHolderNames: String?,
    val maturityDate: OffsetDateTime?,
    val valueDateBalance: BigDecimal?,
    val creditAccount: Boolean?,
    val debitAccount: Boolean?,
    @SuppressWarnings("ConstructorParameterNaming") val IBAN: String?,
    @SuppressWarnings("ConstructorParameterNaming") val BBAN: String?,
    val unMaskableAttributes: Set<MaskableAttribute>?,
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
    val financialInstitutionId: Long?,
    val subArrangements: List<BaseProduct>?,
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
     * A builder for [Loan].
     */
    class Builder {

        /**
         * See [Loan.bookedBalance].
         */
        var bookedBalance: String? = null

        /**
         * See [Loan.principalAmount].
         */
        var principalAmount: BigDecimal? = null

        /**
         * See [Loan.currency].
         */
        var currency: String? = null

        /**
         * See [Loan.urgentTransferAllowed].
         */
        var urgentTransferAllowed: Boolean? = null

        /**
         * See [Loan.productNumber].
         */
        var productNumber: String? = null

        /**
         * See [Loan.accountInterestRate].
         */
        var accountInterestRate: BigDecimal? = null

        /**
         * See [Loan.termUnit].
         */
        var termUnit: TimeUnit? = null

        /**
         * See [Loan.termNumber].
         */
        var termNumber: BigDecimal? = null

        /**
         * See [Loan.outstandingPrincipalAmount].
         */
        var outstandingPrincipalAmount: BigDecimal? = null

        /**
         * See [Loan.monthlyInstalmentAmount].
         */
        var monthlyInstalmentAmount: BigDecimal? = null

        /**
         * See [Loan.amountInArrear].
         */
        var amountInArrear: BigDecimal? = null

        /**
         * See [Loan.interestSettlementAccount].
         */
        var interestSettlementAccount: String? = null

        /**
         * See [Loan.accruedInterest].
         */
        var accruedInterest: BigDecimal? = null

        /**
         * See [Loan.accountHolderNames].
         */
        var accountHolderNames: String? = null

        /**
         * See [Loan.maturityDate].
         */
        var maturityDate: OffsetDateTime? = null

        /**
         * See [Loan.valueDateBalance].
         */
        var valueDateBalance: BigDecimal? = null

        /**
         * See [Loan.creditAccount].
         */
        var creditAccount: Boolean? = null

        /**
         * See [Loan.debitAccount].
         */
        var debitAccount: Boolean? = null

        /**
         * See [Loan.IBAN].
         */
        @SuppressWarnings("VariableNaming")
        var IBAN: String? = null

        /**
         * See [Loan.BBAN].
         */
        @SuppressWarnings("VariableNaming")
        var BBAN: String? = null

        /**
         * See [Loan.unMaskableAttributes].
         */
        var unMaskableAttributes: Set<MaskableAttribute>? = null

        /**
         * See [Loan.id].
         */
        var id: String? = null

        /**
         * See [Loan.name].
         */
        var name: String? = null

        /**
         * See [Loan.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [Loan.crossCurrencyAllowed].
         */
        var crossCurrencyAllowed: Boolean? = null

        /**
         * See [Loan.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [Loan.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [Loan.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [Loan.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [Loan.accountOpeningDate].
         */
        var accountOpeningDate: OffsetDateTime? = null

        /**
         * See [Loan.lastUpdateDate].
         */
        var lastUpdateDate: OffsetDateTime? = null

        /**
         * See [Loan.userPreferences].
         */
        var userPreferences: UserPreferences? = null

        /**
         * See [Loan.state].
         */
        var state: ProductState? = null

        /**
         * See [Loan.parentId].
         */
        var parentId: String? = null

        /**
         * See [Loan.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [Loan.subArrangements].
         */
        var subArrangements: List<BaseProduct>? = null

        /**
         * See [Loan.lastSyncDate].
         */
        var lastSyncDate: OffsetDateTime? = null

        /**
         * See [Loan.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [Loan.displayName].
         */
        var displayName: String? = null

        /**
         * See [Loan.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [Loan.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [Loan.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [Loan.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [Loan.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [Loan.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [Loan.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * See [Loan.bankBranchCode].
         */
        var bankBranchCode: String? = null

        /**
         * See [Loan.bankBranchCode2].
         */
        var bankBranchCode2: String? = null

        /**
         * See [Loan.availableBalance].
         */
        var availableBalance: String? = null

        /**
         * See [Loan.creditLimit].
         */
        var creditLimit: String? = null

        /**
         * See [Loan.minimumPayment].
         */
        var minimumPayment: BigDecimal? = null

        /**
         * See [Loan.minimumPaymentDueDate].
         */
        var minimumPaymentDueDate: OffsetDateTime? = null

        /**
         * See [Loan.bookedBalance].
         */
        fun setBookedBalance(bookedBalance: String?) = apply { this.bookedBalance = bookedBalance }

        /**
         * See [Loan.principalAmount].
         */
        fun setPrincipalAmount(principalAmount: BigDecimal?) =
            apply { this.principalAmount = principalAmount }

        /**
         * See [Loan.currency].
         */
        fun setCurrency(currency: String?) = apply { this.currency = currency }

        /**
         * See [Loan.urgentTransferAllowed].
         */
        fun setUrgentTransferAllowed(urgentTransferAllowed: Boolean?) =
            apply { this.urgentTransferAllowed = urgentTransferAllowed }

        /**
         * See [Loan.productNumber].
         */
        fun setProductNumber(productNumber: String?) = apply { this.productNumber = productNumber }

        /**
         * See [Loan.accountInterestRate].
         */
        fun setAccountInterestRate(accountInterestRate: BigDecimal?) =
            apply { this.accountInterestRate = accountInterestRate }

        /**
         * See [Loan.termUnit].
         */
        fun setTermUnit(termUnit: TimeUnit?) = apply { this.termUnit = termUnit }

        /**
         * See [Loan.termNumber].
         */
        fun setTermNumber(termNumber: BigDecimal?) = apply { this.termNumber = termNumber }

        /**
         * See [Loan.outstandingPrincipalAmount].
         */
        fun setOutstandingPrincipalAmount(outstandingPrincipalAmount: BigDecimal?) =
            apply { this.outstandingPrincipalAmount = outstandingPrincipalAmount }

        /**
         * See [Loan.monthlyInstalmentAmount].
         */
        fun setMonthlyInstalmentAmount(monthlyInstalmentAmount: BigDecimal?) =
            apply { this.monthlyInstalmentAmount = monthlyInstalmentAmount }

        /**
         * See [Loan.amountInArrear].
         */
        fun setAmountInArrear(amountInArrear: BigDecimal?) = apply { this.amountInArrear = amountInArrear }

        /**
         * See [Loan.interestSettlementAccount].
         */
        fun setInterestSettlementAccount(interestSettlementAccount: String?) =
            apply { this.interestSettlementAccount = interestSettlementAccount }

        /**
         * See [Loan.accruedInterest].
         */
        fun setAccruedInterest(accruedInterest: BigDecimal?) =
            apply { this.accruedInterest = accruedInterest }

        /**
         * See [Loan.accountHolderNames].
         */
        fun setAccountHolderNames(accountHolderNames: String?) =
            apply { this.accountHolderNames = accountHolderNames }

        /**
         * See [Loan.maturityDate].
         */
        fun setMaturityDate(maturityDate: OffsetDateTime?) = apply { this.maturityDate = maturityDate }

        /**
         * See [Loan.valueDateBalance].
         */
        fun setValueDateBalance(valueDateBalance: BigDecimal?) =
            apply { this.valueDateBalance = valueDateBalance }

        /**
         * See [Loan.creditAccount].
         */
        fun setCreditAccount(creditAccount: Boolean?) = apply { this.creditAccount = creditAccount }

        /**
         * See [Loan.debitAccount].
         */
        fun setDebitAccount(debitAccount: Boolean?) = apply { this.debitAccount = debitAccount }

        /**
         * See [Loan.IBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setIBAN(IBAN: String?) = apply { this.IBAN = IBAN }

        /**
         * See [Loan.BBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setBBAN(BBAN: String?) = apply { this.BBAN = BBAN }

        /**
         * See [Loan.unMaskableAttributes].
         */
        fun setUnMaskableAttributes(unMaskableAttributes: Set<MaskableAttribute>?) =
            apply { this.unMaskableAttributes = unMaskableAttributes }

        /**
         * See [Loan.id].
         */
        fun setId(id: String?) = apply { this.id = id }

        /**
         * See [Loan.name].
         */
        fun setName(name: String?) = apply { this.name = name }

        /**
         * See [Loan.externalTransferAllowed].
         */
        fun setExternalTransferAllowed(externalTransferAllowed: Boolean?) =
            apply { this.externalTransferAllowed = externalTransferAllowed }

        /**
         * See [Loan.crossCurrencyAllowed].
         */
        fun setCrossCurrencyAllowed(crossCurrencyAllowed: Boolean?) =
            apply { this.crossCurrencyAllowed = crossCurrencyAllowed }

        /**
         * See [Loan.productKindName].
         */
        fun setProductKindName(productKindName: String?) = apply { this.productKindName = productKindName }

        /**
         * See [Loan.productTypeName].
         */
        fun setProductTypeName(productTypeName: String?) = apply { this.productTypeName = productTypeName }

        /**
         * See [Loan.bankAlias].
         */
        fun setBankAlias(bankAlias: String?) = apply { this.bankAlias = bankAlias }

        /**
         * See [Loan.sourceId].
         */
        fun setSourceId(sourceId: String?) = apply { this.sourceId = sourceId }

        /**
         * See [Loan.accountOpeningDate].
         */
        fun setAccountOpeningDate(accountOpeningDate: OffsetDateTime?) =
            apply { this.accountOpeningDate = accountOpeningDate }

        /**
         * See [Loan.lastUpdateDate].
         */
        fun setLastUpdateDate(lastUpdateDate: OffsetDateTime?) =
            apply { this.lastUpdateDate = lastUpdateDate }

        /**
         * See [Loan.userPreferences].
         */
        fun setUserPreferences(userPreferences: UserPreferences?) = apply { this.userPreferences = userPreferences }

        /**
         * See [Loan.state].
         */
        fun setState(state: ProductState?) = apply { this.state = state }

        /**
         * See [Loan.parentId].
         */
        fun setParentId(parentId: String?) = apply { this.parentId = parentId }

        /**
         * See [Loan.financialInstitutionId].
         */
        fun setFinancialInstitutionId(financialInstitutionId: Long?) =
            apply { this.financialInstitutionId = financialInstitutionId }

        /**
         * See [Loan.subArrangements].
         */
        fun setSubArrangements(subArrangements: List<BaseProduct>?) = apply {
            this.subArrangements = subArrangements
        }

        /**
         * See [Loan.lastSyncDate].
         */
        fun setLastSyncDate(lastSyncDate: OffsetDateTime?) = apply { this.lastSyncDate = lastSyncDate }

        /**
         * See [Loan.additions].
         */
        fun setAdditions(additions: Map<String, String>?) =
            apply { this.additions = additions }

        /**
         * See [Loan.displayName].
         */
        fun setDisplayName(displayName: String?) =
            apply { this.displayName = displayName }

        /**
         * See [Loan.cardDetails].
         */
        fun setCardDetails(cardDetails: CardDetails?) =
            apply { this.cardDetails = cardDetails }

        /**
         * See [Loan.reservedAmount].
         */
        fun setReservedAmount(reservedAmount: BigDecimal?) =
            apply { this.reservedAmount = reservedAmount }

        /**
         * See [Loan.remainingPeriodicTransfers].
         */
        fun setRemainingPeriodicTransfers(remainingPeriodicTransfers: BigDecimal?) =
            apply { this.remainingPeriodicTransfers = remainingPeriodicTransfers }

        /**
         * See [Loan.nextClosingDate].
         */
        fun setNextClosingDate(nextClosingDate: LocalDate?) =
            apply { this.nextClosingDate = nextClosingDate }

        /**
         * See [Loan.overdueSince].
         */
        fun setOverdueSince(overdueSince: LocalDate?) =
            apply { this.overdueSince = overdueSince }

        /**
         * See [Loan.externalAccountStatus].
         */
        fun setExternalAccountStatus(externalAccountStatus: String?) =
            apply { this.externalAccountStatus = externalAccountStatus }

        /**
         * See [Loan.interestDetails].
         */
        fun setInterestDetails(interestDetails: InterestDetails?) =
            apply { this.interestDetails = interestDetails }

        /**
         * See [Loan.bankBranchCode].
         */
        fun setBankBranchCode(bankBranchCode: String?) = apply {
            this.bankBranchCode = bankBranchCode
        }

        /**
         * See [Loan.bankBranchCode2].
         */
        fun setBankBranchCode2(bankBranchCode2: String?) = apply {
            this.bankBranchCode2 = bankBranchCode2
        }

        /**
         * See [Loan.availableBalance].
         */
        fun setAvailableBalance(availableBalance: String?) = apply { this.availableBalance = availableBalance }

        /**
         * See [Loan.creditLimit].
         */
        fun setCreditLimit(creditLimit: String?) = apply { this.creditLimit = creditLimit }

        /**
         * See [Loan.minimumPayment].
         */
        fun setMinimumPayment(minimumPayment: BigDecimal?) = apply { this.minimumPayment = minimumPayment }

        /**
         * See [Loan.minimumPaymentDueDate].
         */
        fun setMinimumPaymentDueDate(minimumPaymentDueDate: OffsetDateTime?) =
            apply { this.minimumPaymentDueDate = minimumPaymentDueDate }

        /**
         * Builds an instance of [Loan].
         */
        fun build() = Loan(
            bookedBalance = bookedBalance,
            principalAmount = principalAmount,
            currency = currency,
            urgentTransferAllowed = urgentTransferAllowed,
            productNumber = productNumber,
            accountInterestRate = accountInterestRate,
            termUnit = termUnit,
            termNumber = termNumber,
            outstandingPrincipalAmount = outstandingPrincipalAmount,
            monthlyInstalmentAmount = monthlyInstalmentAmount,
            amountInArrear = amountInArrear,
            interestSettlementAccount = interestSettlementAccount,
            accruedInterest = accruedInterest,
            accountHolderNames = accountHolderNames,
            maturityDate = maturityDate,
            valueDateBalance = valueDateBalance,
            creditAccount = creditAccount,
            debitAccount = debitAccount,
            IBAN = IBAN,
            BBAN = BBAN,
            unMaskableAttributes = unMaskableAttributes,
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
            subArrangements = subArrangements,
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
 * Builds an instance of [Loan] with the [initializer] parameters.
 */
@Suppress("FunctionName") // DSL initializer
fun Loan(initializer: Loan.Builder.() -> Unit): Loan {
    return Loan.Builder().apply(initializer).build()
}
