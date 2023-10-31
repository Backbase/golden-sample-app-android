package com.backbase.accounts_journey.domain.model.account_summary.custom_products

import android.os.Parcelable
import com.backbase.accounts_journey.domain.model.account_summary.MaskableAttribute
import com.backbase.accounts_journey.domain.model.account_summary.UserPreferences
import com.backbase.accounts_journey.domain.model.common.BaseProduct
import com.backbase.accounts_journey.domain.model.common.CardDetails
import com.backbase.accounts_journey.domain.model.common.DebitCardItem
import com.backbase.accounts_journey.domain.model.common.InterestDetails
import com.backbase.accounts_journey.domain.model.common.ProductState
import com.backbase.accounts_journey.domain.model.common.TimeUnit
import dev.drewhamilton.poko.Poko
import kotlinx.parcelize.Parcelize
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
 * @param startDate
 * @param minimumRequiredBalance Minimum amount that a customer must have in an account in order to receive some sort of service, such as keeping the account open or receive interest.
 * @param accountHolderAddressLine1 Address of the Payer/Payee - Alternate address line for the account.
 * @param accountHolderAddressLine2 Address of the Payer/Payee - Alternate address line for the account.
 * @param accountHolderStreetName Street name of the Payer/Payee - Alternate street name for the account.
 * @param town Town of the Payer/Payee - Alternate town for the account.
 * @param postCode The postcode of the account holder address.
 * @param countrySubDivision The country sub division, if any.
 * @param accountHolderNames Party(s) with a relationship to the account.
 * @param accountHolderCountry Country of the account holder
 * @param number First 6 and/or last 4 digits of a Payment card. All other digits will/to be masked. Be aware that using card number differently is potential PCI risk.
 * @param cardNumber The number of the card, if any.
 * @param creditCardAccountNumber The number of the account the credit card transactions settle on (so actually the reference to the settlement account of the card)?
 * @param validThru Expiration date of a credit card, after which is no longer valid.
 * @param applicableInterestRate The interest rate or rates which would be used for a particular arrangement.
 * @param remainingCredit
 * @param outstandingPayment
 * @param minimumPayment The minimum payment set a percentage of balance, or a fixed cash amount.
 * @param minimumPaymentDueDate Minimum Payment Due Date shown on your monthly statement to remain in good standing.
 * @param currentInvestmentValue
 * @param productNumber The number identifying the contract.
 * @param principalAmount
 * @param termUnit
 * @param termNumber The number of times interest rate is paid on the settlement account.
 * @param outstandingPrincipalAmount This IS the value date balance of the arrangement.
 * @param monthlyInstalmentAmount A fixed payment amount paid by a borrower to the bank at a specified date each calendar month.
 * @param amountInArrear The part of a debt that is overdue after missing one or more required payments. The amount of the arrears is the amount accrued from the date on which the first missed payment was due.
 * @param interestSettlementAccount Account that provides quick access to accumulated cash to facilitate daily settlements with other businesses.
 * @param maturityDate End term of a holding period.
 * @param maturityAmount Amount payable at the end of a holding period of a product (maturity date). For deposit all of the interest is usualy paid at maturity date (IF the term is shorter then one year).
 * @param autoRenewalIndicator Indicates whether or not an arrangement is to be continued after maturity automatically. Usually the product is renewed using the same principal and term unless renegotiation has taken place prior to expiration.
 * @param interestPaymentFrequencyUnit
 * @param interestPaymentFrequencyNumber
 * @param creditAccount Indicator whether or not the arrangement can be used in payment orders as credit account.
 * @param debitAccount Indicator whether or not the arrangement can be used in payment orders as debit account.
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
@Parcelize
class GeneralAccount internal constructor(
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
    val number: String?,
    val cardNumber: BigDecimal?,
    val creditCardAccountNumber: String?,
    val validThru: OffsetDateTime?,
    val applicableInterestRate: BigDecimal?,
    val remainingCredit: BigDecimal?,
    val outstandingPayment: BigDecimal?,
    val minimumPayment: BigDecimal?,
    val minimumPaymentDueDate: OffsetDateTime?,
    val currentInvestmentValue: String?,
    val productNumber: String?,
    val principalAmount: BigDecimal?,
    val termUnit: TimeUnit?,
    val termNumber: BigDecimal?,
    val outstandingPrincipalAmount: BigDecimal?,
    val monthlyInstalmentAmount: BigDecimal?,
    val amountInArrear: BigDecimal?,
    val interestSettlementAccount: String?,
    val maturityDate: OffsetDateTime?,
    val maturityAmount: BigDecimal?,
    val autoRenewalIndicator: Boolean?,
    val interestPaymentFrequencyUnit: TimeUnit?,
    val interestPaymentFrequencyNumber: BigDecimal?,
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
) : Parcelable {

    /**
     * A builder for this configuration class
     *
     * Should be directly used by Java consumers. Kotlin consumers should use DSL function
     */
    class Builder {

        /**
         * See [GeneralAccount.debitCardItems].
         */
        var debitCardItems: Set<DebitCardItem>? = null

        /**
         * See [GeneralAccount.bookedBalance].
         */
        var bookedBalance: String? = null

        /**
         * See [GeneralAccount.availableBalance].
         */
        var availableBalance: String? = null

        /**
         * See [GeneralAccount.creditLimit].
         */
        var creditLimit: String? = null

        /**
         * See [GeneralAccount.IBAN].
         */
        @SuppressWarnings("VariableNaming")
        var IBAN: String? = null

        /**
         * See [GeneralAccount.BBAN].
         */
        @SuppressWarnings("VariableNaming")
        var BBAN: String? = null

        /**
         * See [GeneralAccount.BIC].
         */
        @SuppressWarnings("VariableNaming")
        var BIC: String? = null

        /**
         * See  [GeneralAccount.unMaskableAttributes].
         */
        var unMaskableAttributes: Set<MaskableAttribute>? = null

        /**
         * See [GeneralAccount.currency].
         */
        var currency: String? = null

        /**
         * See [GeneralAccount.urgentTransferAllowed].
         */
        var urgentTransferAllowed: Boolean? = null

        /**
         * See [GeneralAccount.bankBranchCode].
         */
        var bankBranchCode: String? = null

        /**
         * See [GeneralAccount.bankBranchCode2].
         */
        var bankBranchCode2: String? = null

        /**
         * See [GeneralAccount.accountInterestRate].
         */
        var accountInterestRate: BigDecimal? = null

        /**
         * See [GeneralAccount.valueDateBalance].
         */
        var valueDateBalance: BigDecimal? = null

        /**
         * See [GeneralAccount.creditLimitUsage].
         */

        var creditLimitUsage: BigDecimal? = null

        /**
         * See [GeneralAccount.creditLimitInterestRate].
         */
        var creditLimitInterestRate: BigDecimal? = null

        /**
         * See [GeneralAccount.creditLimitExpiryDate].
         */
        var creditLimitExpiryDate: OffsetDateTime? = null

        /**
         * See [GeneralAccount.accruedInterest].
         */
        var accruedInterest: BigDecimal? = null

        /**
         * See [GeneralAccount.startDate].
         */
        var startDate: OffsetDateTime? = null

        /**
         * See [GeneralAccount.minimumRequiredBalance].
         */
        var minimumRequiredBalance: BigDecimal? = null

        /**
         * See [GeneralAccount.accountHolderAddressLine1].
         */
        var accountHolderAddressLine1: String? = null

        /**
         * See [GeneralAccount.accountHolderAddressLine2].
         */
        var accountHolderAddressLine2: String? = null

        /**
         * See [GeneralAccount.accountHolderStreetName].
         */
        var accountHolderStreetName: String? = null

        /**
         * See [GeneralAccount.town].
         */
        var town: String? = null

        /**
         * See [GeneralAccount.postCode].
         */
        var postCode: String? = null

        /**
         * See [GeneralAccount.countrySubDivision].
         */
        var countrySubDivision: String? = null

        /**
         * See [GeneralAccount.accountHolderNames].
         */
        var accountHolderNames: String? = null

        /**
         * See [GeneralAccount.accountHolderCountry].
         */
        var accountHolderCountry: String? = null

        /**
         * See [GeneralAccount.number].
         */
        var number: String? = null

        /**
         * See [GeneralAccount.cardNumber].
         */
        var cardNumber: BigDecimal? = null

        /**
         * See [GeneralAccount.creditCardAccountNumber].
         */
        var creditCardAccountNumber: String? = null

        /**
         * See [GeneralAccount.validThru].
         */
        var validThru: OffsetDateTime? = null

        /**
         * See [GeneralAccount.applicableInterestRate].
         */
        var applicableInterestRate: BigDecimal? = null

        /**
         * See [GeneralAccount.remainingCredit].
         */
        var remainingCredit: BigDecimal? = null

        /**
         * See [GeneralAccount.outstandingPayment].
         */
        var outstandingPayment: BigDecimal? = null

        /**
         * See [GeneralAccount.minimumPayment].
         */
        var minimumPayment: BigDecimal? = null

        /**
         * See [GeneralAccount.minimumPaymentDueDate].
         */
        var minimumPaymentDueDate: OffsetDateTime? = null

        /**
         * See [GeneralAccount.currentInvestmentValue].
         */
        var currentInvestmentValue: String? = null

        /**
         * See [GeneralAccount.productNumber].
         */
        var productNumber: String? = null

        /**
         * See [GeneralAccount.interestSettlementAccount].
         */
        var interestSettlementAccount: String? = null

        /**
         * See [GeneralAccount.principalAmount].
         */
        var principalAmount: BigDecimal? = null

        /**
         * See [GeneralAccount.termNumber].
         */
        var termNumber: BigDecimal? = null

        /**
         * See [GeneralAccount.outstandingPrincipalAmount].
         */
        var outstandingPrincipalAmount: BigDecimal? = null

        /**
         * See [GeneralAccount.monthlyInstalmentAmount].
         */
        var monthlyInstalmentAmount: BigDecimal? = null

        /**
         * See [GeneralAccount.amountInArrear].
         */
        var amountInArrear: BigDecimal? = null

        /**
         * See [GeneralAccount.maturityAmount].
         */
        var maturityAmount: BigDecimal? = null

        /**
         * See [GeneralAccount.termUnit].
         */
        var termUnit: TimeUnit? = null

        /**
         * See [GeneralAccount.interestPaymentFrequencyUnit].
         */
        var interestPaymentFrequencyUnit: TimeUnit? = null

        /**
         * See [GeneralAccount.autoRenewalIndicator].
         */
        var autoRenewalIndicator: Boolean? = null

        /**
         * See [GeneralAccount.creditAccount].
         */
        var creditAccount: Boolean? = null

        /**
         * See [GeneralAccount.debitAccount].
         */
        var debitAccount: Boolean? = null

        /**
         * See [GeneralAccount.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [GeneralAccount.crossCurrencyAllowed].
         */
        var crossCurrencyAllowed: Boolean? = null

        /**
         * See [GeneralAccount.id].
         */
        var id: String? = null

        /**
         * See [GeneralAccount.name].
         */
        var name: String? = null

        /**
         * See [GeneralAccount.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [GeneralAccount.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [GeneralAccount.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [GeneralAccount.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [GeneralAccount.parentId].
         */
        var parentId: String? = null

        /**
         * See [GeneralAccount.subArrangements].
         */
        var subArrangements: List<BaseProduct>? = null

        /**
         * See [GeneralAccount.maturityDate].
         */
        var maturityDate: OffsetDateTime? = null

        /**
         * See [GeneralAccount.accountOpeningDate].
         */
        var accountOpeningDate: OffsetDateTime? = null

        /**
         * See [GeneralAccount.lastUpdateDate].
         */
        var lastUpdateDate: OffsetDateTime? = null

        /**
         * See [GeneralAccount.lastSyncDate].
         */
        var lastSyncDate: OffsetDateTime? = null

        /**
         * See [GeneralAccount.interestPaymentFrequencyNumber].
         */
        var interestPaymentFrequencyNumber: BigDecimal? = null

        /**
         * See [GeneralAccount.userPreferences].
         */
        var userPreferences: UserPreferences? = null

        /**
         * See [GeneralAccount.state].
         */
        var state: ProductState? = null

        /**
         * See [GeneralAccount.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [GeneralAccount.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [GeneralAccount.displayName].
         */
        var displayName: String? = null

        /**
         * See [GeneralAccount.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [GeneralAccount.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [GeneralAccount.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [GeneralAccount.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [GeneralAccount.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [GeneralAccount.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [GeneralAccount.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * See [GeneralAccount.debitCardItems].
         */
        fun setDebitCards(debitCardItems: Set<DebitCardItem>) = apply {
            this.debitCardItems = debitCardItems
        }

        /**
         * See [GeneralAccount.bookedBalance].
         */
        fun setBookedBalance(bookedBalance: String?) = apply {
            this.bookedBalance = bookedBalance
        }

        /**
         * See [GeneralAccount.availableBalance].
         */
        fun setAvailableBalance(availableBalance: String?) = apply {
            this.availableBalance = availableBalance
        }

        /**
         * See [GeneralAccount.creditLimit].
         */
        fun setCreditLimit(creditLimit: String?) = apply {
            this.creditLimit = creditLimit
        }

        /**
         * See [GeneralAccount.IBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setIBAN(IBAN: String?) = apply {
            this.IBAN = IBAN
        }

        /**
         * See [GeneralAccount.BBAN].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setBBAN(BBAN: String?) = apply {
            this.BBAN = BBAN
        }

        /**
         * See [GeneralAccount.BIC].
         */
        @SuppressWarnings("FunctionParameterNaming")
        fun setBIC(BIC: String?) = apply {
            this.BIC = BIC
        }

        /**
         * See [GeneralAccount.unMaskableAttributes].
         */
        fun setUnMaskableAttributes(unMaskableAttributes: Set<MaskableAttribute>?) =
            apply { this.unMaskableAttributes = unMaskableAttributes }

        /**
         * See [GeneralAccount.currency].
         */
        fun setCurrency(currency: String?) = apply {
            this.currency = currency
        }

        /**
         * See [GeneralAccount.urgentTransferAllowed].
         */
        fun setUrgentTransferAllowed(urgentTransferAllowed: Boolean?) = apply {
            this.urgentTransferAllowed = urgentTransferAllowed
        }

        /**
         * See [GeneralAccount.bankBranchCode].
         */
        fun setBankBranchCode(bankBranchCode: String?) = apply {
            this.bankBranchCode = bankBranchCode
        }

        /**
         * See [GeneralAccount.bankBranchCode2].
         */
        fun setBankBranchCode2(bankBranchCode2: String?) = apply {
            this.bankBranchCode2 = bankBranchCode2
        }

        /**
         * See [GeneralAccount.accountInterestRate].
         */
        fun setAccountInterestRate(accountInterestRate: BigDecimal?) = apply {
            this.accountInterestRate = accountInterestRate
        }

        /**
         * See [GeneralAccount.valueDateBalance].
         */
        fun setValueDateBalance(valueDateBalance: BigDecimal?) = apply {
            this.valueDateBalance = valueDateBalance
        }

        /**
         * See [GeneralAccount.creditLimitUsage].
         */
        fun setCreditLimitUsage(creditLimitUsage: BigDecimal?) = apply {
            this.creditLimitUsage = creditLimitUsage
        }

        /**
         * See [GeneralAccount.creditLimitInterestRate].
         */
        fun setCreditLimitInterestRate(creditLimitInterestRate: BigDecimal?) = apply {
            this.creditLimitInterestRate = creditLimitInterestRate
        }

        /**
         * See [GeneralAccount.creditLimitExpiryDate].
         */
        fun setCreditLimitExpiryDate(creditLimitExpiryDate: OffsetDateTime?) = apply {
            this.creditLimitExpiryDate = creditLimitExpiryDate
        }

        /**
         * See [GeneralAccount.accruedInterest].
         */
        fun setAccruedInterest(accruedInterest: BigDecimal?) = apply {
            this.accruedInterest = accruedInterest
        }

        /**
         * See [GeneralAccount.startDate].
         */
        fun setStartDate(startDate: OffsetDateTime?) = apply {
            this.startDate = startDate
        }

        /**
         * See [GeneralAccount.minimumRequiredBalance].
         */
        fun setMinimumRequiredBalance(minimumRequiredBalance: BigDecimal?) = apply {
            this.minimumRequiredBalance = minimumRequiredBalance
        }

        /**
         * See [GeneralAccount.accountHolderAddressLine1].
         */
        fun setAccountHolderAddressLine1(accountHolderAddressLine1: String?) = apply {
            this.accountHolderAddressLine1 = accountHolderAddressLine1
        }

        /**
         * See [GeneralAccount.accountHolderAddressLine2].
         */
        fun setAccountHolderAddressLine2(accountHolderAddressLine2: String?) = apply {
            this.accountHolderAddressLine2 = accountHolderAddressLine2
        }

        /**
         * See [GeneralAccount.accountHolderStreetName].
         */
        fun setAccountHolderStreetName(accountHolderStreetName: String?) = apply {
            this.accountHolderStreetName = accountHolderStreetName
        }

        /**
         * See [GeneralAccount.town].
         */
        fun setTown(town: String?) = apply {
            this.town = town
        }

        /**
         * See [GeneralAccount.postCode].
         */
        fun setPostCode(postCode: String?) = apply {
            this.postCode = postCode
        }

        /**
         * See [GeneralAccount.countrySubDivision].
         */
        fun setCountrySubDivision(countrySubDivision: String?) = apply {
            this.countrySubDivision = countrySubDivision
        }

        /**
         * See [GeneralAccount.accountHolderNames].
         */
        fun setAccountHolderNames(accountHolderNames: String?) = apply {
            this.accountHolderNames = accountHolderNames
        }

        /**
         * See [GeneralAccount.accountHolderCountry].
         */
        fun setAccountHolderCountry(accountHolderCountry: String?) = apply {
            this.accountHolderCountry = accountHolderCountry
        }

        /**
         * See [GeneralAccount.number].
         */
        fun setNumber(number: String?) = apply {
            this.number = number
        }

        /**
         * See [GeneralAccount.cardNumber].
         */
        fun setCardNumber(cardNumber: BigDecimal?) = apply {
            this.cardNumber = cardNumber
        }

        /**
         * See [GeneralAccount.creditCardAccountNumber].
         */
        fun setCreditCardAccountNumber(creditCardAccountNumber: String?) = apply {
            this.creditCardAccountNumber = creditCardAccountNumber
        }

        /**
         * See [GeneralAccount.validThru].
         */
        fun setValidThru(validThru: OffsetDateTime?) = apply {
            this.validThru = validThru
        }

        /**
         * See [GeneralAccount.minimumPaymentDueDate].
         */
        fun setMinimumPaymentDueDate(minimumPaymentDueDate: OffsetDateTime?) = apply {
            this.minimumPaymentDueDate = minimumPaymentDueDate
        }

        /**
         * See [GeneralAccount.maturityDate].
         */
        fun setMaturityDate(maturityDate: OffsetDateTime?) = apply {
            this.maturityDate = maturityDate
        }

        /**
         * See [GeneralAccount.accountOpeningDate].
         */
        fun setAccountOpeningDate(accountOpeningDate: OffsetDateTime?) = apply {
            this.accountOpeningDate = accountOpeningDate
        }

        /**
         * See [GeneralAccount.lastUpdateDate].
         */
        fun setLastUpdateDate(lastUpdateDate: OffsetDateTime?) = apply {
            this.lastUpdateDate = lastUpdateDate
        }

        /**
         * See [GeneralAccount.lastSyncDate].
         */
        fun setLastSyncDate(lastSyncDate: OffsetDateTime?) = apply {
            this.lastSyncDate = lastSyncDate
        }

        /**
         * See [GeneralAccount.applicableInterestRate].
         */
        fun setApplicableInterestRate(applicableInterestRate: BigDecimal?) = apply {
            this.applicableInterestRate = applicableInterestRate
        }

        /**
         * See [GeneralAccount.remainingCredit].
         */
        fun setRemainingCredit(remainingCredit: BigDecimal?) = apply {
            this.remainingCredit = remainingCredit
        }

        /**
         * See [GeneralAccount.outstandingPayment].
         */
        fun setOutstandingPayment(outstandingPayment: BigDecimal?) = apply {
            this.outstandingPayment = outstandingPayment
        }

        /**
         * See [GeneralAccount.minimumPayment].
         */
        fun setMinimumPayment(minimumPayment: BigDecimal?) = apply {
            this.minimumPayment = minimumPayment
        }

        /**
         * See [GeneralAccount.principalAmount].
         */
        fun setPrincipalAmount(principalAmount: BigDecimal?) = apply {
            this.principalAmount = principalAmount
        }

        /**
         * See [GeneralAccount.termNumber].
         */
        fun setTermNumber(termNumber: BigDecimal?) = apply {
            this.termNumber = termNumber
        }

        /**
         * See [GeneralAccount.outstandingPrincipalAmount].
         */
        fun setOutstandingPrincipalAmount(outstandingPrincipalAmount: BigDecimal?) = apply {
            this.outstandingPrincipalAmount = outstandingPrincipalAmount
        }

        /**
         * See [GeneralAccount.monthlyInstalmentAmount].
         */
        fun setMonthlyInstalmentAmount(monthlyInstalmentAmount: BigDecimal?) = apply {
            this.monthlyInstalmentAmount = monthlyInstalmentAmount
        }

        /**
         * See [GeneralAccount.amountInArrear].
         */
        fun setAmountInArrear(amountInArrear: BigDecimal?) = apply {
            this.amountInArrear = amountInArrear
        }

        /**
         * See [GeneralAccount.maturityAmount].
         */
        fun setMaturityAmount(maturityAmount: BigDecimal?) = apply {
            this.maturityAmount = maturityAmount
        }

        /**
         * See [GeneralAccount.interestPaymentFrequencyNumber].
         */
        fun setInterestPaymentFrequencyNumber(interestPaymentFrequencyNumber: BigDecimal?) = apply {
            this.interestPaymentFrequencyNumber = interestPaymentFrequencyNumber
        }

        /**
         * See [GeneralAccount.currentInvestmentValue].
         */
        fun setCurrentInvestmentValue(currentInvestmentValue: String?) = apply {
            this.currentInvestmentValue = currentInvestmentValue
        }

        /**
         * See [GeneralAccount.productNumber].
         */
        fun setProductNumber(productNumber: String?) = apply {
            this.productNumber = productNumber
        }

        /**
         * See [GeneralAccount.interestSettlementAccount].
         */
        fun setInterestSettlementAccount(interestSettlementAccount: String?) = apply {
            this.interestSettlementAccount = interestSettlementAccount
        }

        /**
         * See [GeneralAccount.id].
         */
        fun setId(id: String?) = apply {
            this.id = id
        }

        /**
         * See [GeneralAccount.name].
         */
        fun setName(name: String?) = apply {
            this.name = name
        }

        /**
         * See [GeneralAccount.productKindName].
         */
        fun setProductKindName(productKindName: String?) = apply {
            this.productKindName = productKindName
        }

        /**
         * See [GeneralAccount.productTypeName].
         */
        fun setProductTypeName(productTypeName: String?) = apply {
            this.productTypeName = productTypeName
        }

        /**
         * See [GeneralAccount.bankAlias].
         */
        fun setBankAlias(bankAlias: String?) = apply {
            this.bankAlias = bankAlias
        }

        /**
         * See [GeneralAccount.sourceId].
         */
        fun setSourceId(sourceId: String?) = apply {
            this.sourceId = sourceId
        }

        /**
         * See [GeneralAccount.parentId].
         */
        fun setParentId(parentId: String?) = apply {
            this.parentId = parentId
        }

        /**
         * See [GeneralAccount.subArrangements].
         */
        fun setSubArrangements(subArrangements: List<BaseProduct>?) = apply {
            this.subArrangements = subArrangements
        }

        /**
         * See [GeneralAccount.termUnit].
         */
        fun setTermUnit(termUnit: TimeUnit?) = apply {
            this.termUnit = termUnit
        }

        /**
         * See [GeneralAccount.interestPaymentFrequencyUnit].
         */
        fun setInterestPaymentFrequencyUnit(interestPaymentFrequencyUnit: TimeUnit?) = apply {
            this.interestPaymentFrequencyUnit = interestPaymentFrequencyUnit
        }

        /**
         * See [GeneralAccount.autoRenewalIndicator].
         */
        fun setAutoRenewalIndicator(autoRenewalIndicator: Boolean?) = apply {
            this.autoRenewalIndicator = autoRenewalIndicator
        }

        /**
         * See [GeneralAccount.creditAccount].
         */
        fun setCreditAccount(creditAccount: Boolean?) = apply {
            this.creditAccount = creditAccount
        }

        /**
         * See [GeneralAccount.debitAccount].
         */
        fun setDebitAccount(debitAccount: Boolean?) = apply {
            this.debitAccount = debitAccount
        }

        /**
         * See [GeneralAccount.externalTransferAllowed].
         */
        fun setExternalTransferAllowed(externalTransferAllowed: Boolean?) = apply {
            this.externalTransferAllowed = externalTransferAllowed
        }

        /**
         * See [GeneralAccount.crossCurrencyAllowed].
         */
        fun setCrossCurrencyAllowed(crossCurrencyAllowed: Boolean?) = apply {
            this.crossCurrencyAllowed = crossCurrencyAllowed
        }

        /**
         * See [GeneralAccount.userPreferences].
         */
        fun setUserPreferences(userPreferences: UserPreferences?) = apply {
            this.userPreferences = userPreferences
        }

        /**
         * See [GeneralAccount.state].
         */
        fun setState(state: ProductState?) = apply {
            this.state = state
        }

        /**
         * See [GeneralAccount.financialInstitutionId].
         */
        fun setFinancialInstitutionId(financialInstitutionId: Long?) = apply {
            this.financialInstitutionId = financialInstitutionId
        }

        /**
         * See [GeneralAccount.additions].
         */
        fun setAdditions(additions: Map<String, String>?) = apply {
            this.additions = additions
        }

        /**
         * See [GeneralAccount.displayName].
         */
        fun setDisplayName(displayName: String?) = apply {
            this.displayName = displayName
        }

        /**
         * See [GeneralAccount.cardDetails].
         */
        fun setCardDetails(cardDetails: CardDetails?) =
            apply { this.cardDetails = cardDetails }

        /**
         * See [GeneralAccount.reservedAmount].
         */
        fun setReservedAmount(reservedAmount: BigDecimal?) =
            apply { this.reservedAmount = reservedAmount }

        /**
         * See [GeneralAccount.remainingPeriodicTransfers].
         */
        fun setRemainingPeriodicTransfers(remainingPeriodicTransfers: BigDecimal?) =
            apply { this.remainingPeriodicTransfers = remainingPeriodicTransfers }

        /**
         * See [GeneralAccount.nextClosingDate].
         */
        fun setNextClosingDate(nextClosingDate: LocalDate?) =
            apply { this.nextClosingDate = nextClosingDate }

        /**
         * See [GeneralAccount.overdueSince].
         */
        fun setOverdueSince(overdueSince: LocalDate?) =
            apply { this.overdueSince = overdueSince }

        /**
         * See [GeneralAccount.externalAccountStatus].
         */
        fun setExternalAccountStatus(externalAccountStatus: String?) =
            apply { this.externalAccountStatus = externalAccountStatus }

        /**
         * See [GeneralAccount.interestDetails].
         */
        fun setInterestDetails(interestDetails: InterestDetails?) =
            apply { this.interestDetails = interestDetails }

        /**
         * Builds an instance of [GeneralAccount].
         */
        @Suppress("LongMethod")
        fun build() = GeneralAccount(
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
            startDate = startDate,
            minimumRequiredBalance = minimumRequiredBalance,
            accountHolderAddressLine1 = accountHolderAddressLine1,
            accountHolderAddressLine2 = accountHolderAddressLine2,
            accountHolderStreetName = accountHolderStreetName,
            town = town,
            postCode = postCode,
            countrySubDivision = countrySubDivision,
            accountHolderNames = accountHolderNames,
            accountHolderCountry = accountHolderCountry,
            number = number,
            cardNumber = cardNumber,
            creditCardAccountNumber = creditCardAccountNumber,
            validThru = validThru,
            applicableInterestRate = applicableInterestRate,
            remainingCredit = remainingCredit,
            outstandingPayment = outstandingPayment,
            minimumPayment = minimumPayment,
            minimumPaymentDueDate = minimumPaymentDueDate,
            currentInvestmentValue = currentInvestmentValue,
            productNumber = productNumber,
            principalAmount = principalAmount,
            termUnit = termUnit,
            termNumber = termNumber,
            outstandingPrincipalAmount = outstandingPrincipalAmount,
            monthlyInstalmentAmount = monthlyInstalmentAmount,
            amountInArrear = amountInArrear,
            interestSettlementAccount = interestSettlementAccount,
            maturityDate = maturityDate,
            maturityAmount = maturityAmount,
            autoRenewalIndicator = autoRenewalIndicator,
            interestPaymentFrequencyUnit = interestPaymentFrequencyUnit,
            interestPaymentFrequencyNumber = interestPaymentFrequencyNumber,
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
 * DSL to create [GeneralAccount]
 */
fun GeneralAccount(block: GeneralAccount.Builder.() -> Unit) = GeneralAccount.Builder().apply(block).build()
