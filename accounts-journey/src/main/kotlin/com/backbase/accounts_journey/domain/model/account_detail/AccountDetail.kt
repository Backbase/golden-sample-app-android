package com.backbase.accounts_journey.domain.model.account_detail

import com.backbase.accounts_journey.domain.model.account_summary.MaskableAttribute
import com.backbase.accounts_journey.domain.model.common.CardDetails
import com.backbase.accounts_journey.domain.model.common.DebitCardItem
import com.backbase.accounts_journey.domain.model.common.InterestDetails
import com.backbase.accounts_journey.domain.model.common.ProductState
import com.backbase.accounts_journey.domain.model.common.TimeUnit
import dev.drewhamilton.poko.Poko
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime

@Suppress("LongParameterList")
@Poko
class AccountDetail private constructor(
    val id: String,
    val productId: String,
    val productKindName: String?,
    val legalEntityIds: Set<String>?,
    val productTypeName: String?,
    val externalProductId: String?,
    val externalArrangementId: String?,
    val userPreferences: AccountUserPreferences?,
    val product: ExternalProductItem?,
    val state: ProductState?,
    val parentId: String?,
    val currency: String,
    val name: String?,
    val bookedBalance: BigDecimal?,
    val availableBalance: BigDecimal?,
    val creditLimit: BigDecimal?,
    @Suppress("ConstructorParameterNaming") val IBAN: String?,
    @Suppress("ConstructorParameterNaming") val BBAN: String?,
    @Suppress("ConstructorParameterNaming") val BIC: String?,
    val externalTransferAllowed: Boolean?,
    val urgentTransferAllowed: Boolean?,
    val accruedInterest: BigDecimal?,
    val number: String?,
    val principalAmount: BigDecimal?,
    val currentInvestmentValue: BigDecimal?,
    val productNumber: String?,
    val bankBranchCode: String?,
    val bankBranchCode2: String?,
    val accountOpeningDate: OffsetDateTime?,
    val accountInterestRate: BigDecimal?,
    val valueDateBalance: BigDecimal?,
    val creditLimitUsage: BigDecimal?,
    val creditLimitInterestRate: BigDecimal?,
    val creditLimitExpiryDate: OffsetDateTime?,
    val startDate: OffsetDateTime?,
    val termUnit: TimeUnit?,
    val termNumber: BigDecimal?,
    val interestPaymentFrequencyUnit: TimeUnit?,
    val interestPaymentFrequencyNumber: BigDecimal?,
    val maturityDate: OffsetDateTime?,
    val maturityAmount: BigDecimal?,
    val autoRenewalIndicator: Boolean?,
    val interestSettlementAccount: String?,
    val outstandingPrincipalAmount: BigDecimal?,
    val monthlyInstalmentAmount: BigDecimal?,
    val amountInArrear: BigDecimal?,
    val minimumRequiredBalance: BigDecimal?,
    val creditCardAccountNumber: String?,
    val validThru: OffsetDateTime?,
    val applicableInterestRate: BigDecimal?,
    val remainingCredit: BigDecimal?,
    val outstandingPayment: BigDecimal?,
    val minimumPayment: BigDecimal?,
    val minimumPaymentDueDate: OffsetDateTime?,
    val totalInvestmentValue: BigDecimal?,
    val debitCards: Set<DebitCardItem>?,
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
    val lastUpdateDate: OffsetDateTime?,
    val bankAlias: String?,
    val sourceId: String?,
    val externalStateId: String?,
    val externalParentId: String?,

    val financialInstitutionId: Long?,
    val lastSyncDate: OffsetDateTime?,
    val additions: Map<String, String>?,
    val unmaskableAttributes: Set<MaskableAttribute>?,
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
     * A Builder for [AccountArrangementItem]
     */
    class Builder {

        /**
         * See [AccountArrangementItem.id].
         */
        lateinit var id: String

        /**
         * See [AccountArrangementItem.productId].
         */
        lateinit var productId: String

        /**
         * See [AccountArrangementItem.productKindName].
         */
        var productKindName: String? = null

        /**
         * See [AccountArrangementItem.legalEntityIds].
         */
        var legalEntityIds: Set<String>? = null

        /**
         * See [AccountArrangementItem.productTypeName].
         */
        var productTypeName: String? = null

        /**
         * See [AccountArrangementItem.externalProductId].
         */
        var externalProductId: String? = null

        /**
         * See [AccountArrangementItem.externalArrangementId].
         */
        var externalArrangementId: String? = null

        /**
         * See [AccountArrangementItem.userPreferences].
         */
        var userPreferences: AccountUserPreferences? = null

        /**
         * See [AccountArrangementItem.product].
         */
        var product: ExternalProductItem? = null

        /**
         * See [AccountArrangementItem.state].
         */
        var state: ProductState? = null

        /**
         * See [AccountArrangementItem.parentId].
         */
        var parentId: String? = null

        /**
         * See [AccountArrangementItem.currency].
         */
        lateinit var currency: String

        /**
         * See [AccountArrangementItem.name].
         */
        var name: String? = null

        /**
         * See [AccountArrangementItem.bookedBalance].
         */
        var bookedBalance: BigDecimal? = null

        /**
         * See [AccountArrangementItem.availableBalance].
         */
        var availableBalance: BigDecimal? = null

        /**
         * See [AccountArrangementItem.creditLimit].
         */
        var creditLimit: BigDecimal? = null

        /**
         * See [AccountArrangementItem.IBAN].
         */
        @Suppress("VariableNaming")
        var IBAN: String? = null

        /**
         * See [AccountArrangementItem.BBAN].
         */
        @Suppress("VariableNaming")
        var BBAN: String? = null

        /**
         * See [AccountArrangementItem.BIC].
         */
        @Suppress("VariableNaming")
        var BIC: String? = null

        /**
         * See [AccountArrangementItem.externalTransferAllowed].
         */
        var externalTransferAllowed: Boolean? = null

        /**
         * See [AccountArrangementItem.urgentTransferAllowed].
         */
        var urgentTransferAllowed: Boolean? = null

        /**
         * See [AccountArrangementItem.accruedInterest].
         */
        var accruedInterest: BigDecimal? = null

        /**
         * See [AccountArrangementItem.number].
         */
        var number: String? = null

        /**
         * See [AccountArrangementItem.principalAmount].
         */
        var principalAmount: BigDecimal? = null

        /**
         * See [AccountArrangementItem.currentInvestmentValue].
         */
        var currentInvestmentValue: BigDecimal? = null

        /**
         * See [AccountArrangementItem.productNumber].
         */
        var productNumber: String? = null

        /**
         * See [AccountArrangementItem.bankBranchCode].
         */
        var bankBranchCode: String? = null

        /**
         * See [AccountArrangementItem.bankBranchCode2].
         */
        var bankBranchCode2: String? = null

        /**
         * See [AccountArrangementItem.accountOpeningDate].
         */
        var accountOpeningDate: OffsetDateTime? = null

        /**
         * See [AccountArrangementItem.accountInterestRate].
         */
        var accountInterestRate: BigDecimal? = null

        /**
         * See [AccountArrangementItem.valueDateBalance].
         */
        var valueDateBalance: BigDecimal? = null

        /**
         * See [AccountArrangementItem.creditLimitUsage].
         */
        var creditLimitUsage: BigDecimal? = null

        /**
         * See [AccountArrangementItem.creditLimitInterestRate].
         */
        var creditLimitInterestRate: BigDecimal? = null

        /**
         * See [AccountArrangementItem.creditLimitExpiryDate].
         */
        var creditLimitExpiryDate: OffsetDateTime? = null

        /**
         * See [AccountArrangementItem.startDate].
         */
        var startDate: OffsetDateTime? = null

        /**
         * See [AccountArrangementItem.termUnit].
         */
        var termUnit: TimeUnit? = null

        /**
         * See [AccountArrangementItem.termNumber].
         */
        var termNumber: BigDecimal? = null

        /**
         * See [AccountArrangementItem.interestPaymentFrequencyUnit].
         */
        var interestPaymentFrequencyUnit: TimeUnit? = null

        /**
         * See [AccountArrangementItem.interestPaymentFrequencyNumber].
         */
        var interestPaymentFrequencyNumber: BigDecimal? = null

        /**
         * See [AccountArrangementItem.maturityDate].
         */
        var maturityDate: OffsetDateTime? = null

        /**
         * See [AccountArrangementItem.maturityAmount].
         */
        var maturityAmount: BigDecimal? = null

        /**
         * See [AccountArrangementItem.autoRenewalIndicator].
         */
        var autoRenewalIndicator: Boolean? = null

        /**
         * See [AccountArrangementItem.interestSettlementAccount].
         */
        var interestSettlementAccount: String? = null

        /**
         * See [AccountArrangementItem.outstandingPrincipalAmount].
         */
        var outstandingPrincipalAmount: BigDecimal? = null

        /**
         * See [AccountArrangementItem.monthlyInstalmentAmount].
         */
        var monthlyInstalmentAmount: BigDecimal? = null

        /**
         * See [AccountArrangementItem.amountInArrear].
         */
        var amountInArrear: BigDecimal? = null

        /**
         * See [AccountArrangementItem.minimumRequiredBalance].
         */
        var minimumRequiredBalance: BigDecimal? = null

        /**
         * See [AccountArrangementItem.creditCardAccountNumber].
         */
        var creditCardAccountNumber: String? = null

        /**
         * See [AccountArrangementItem.validThru].
         */
        var validThru: OffsetDateTime? = null

        /**
         * See [AccountArrangementItem.applicableInterestRate].
         */
        var applicableInterestRate: BigDecimal? = null

        /**
         * See [AccountArrangementItem.remainingCredit].
         */
        var remainingCredit: BigDecimal? = null

        /**
         * See [AccountArrangementItem.outstandingPayment].
         */
        var outstandingPayment: BigDecimal? = null

        /**
         * See [AccountArrangementItem.minimumPayment].
         */
        var minimumPayment: BigDecimal? = null

        /**
         * See [AccountArrangementItem.minimumPaymentDueDate].
         */
        var minimumPaymentDueDate: OffsetDateTime? = null

        /**
         * See [AccountArrangementItem.totalInvestmentValue].
         */
        var totalInvestmentValue: BigDecimal? = null

        /**
         * See [AccountArrangementItem.debitCards].
         */
        var debitCards: Set<DebitCardItem>? = null

        /**
         * See [AccountArrangementItem.accountHolderAddressLine1].
         */
        var accountHolderAddressLine1: String? = null

        /**
         * See [AccountArrangementItem.accountHolderAddressLine2].
         */
        var accountHolderAddressLine2: String? = null

        /**
         * See [AccountArrangementItem.accountHolderStreetName].
         */
        var accountHolderStreetName: String? = null

        /**
         * See [AccountArrangementItem.town].
         */
        var town: String? = null

        /**
         * See [AccountArrangementItem.postCode].
         */
        var postCode: String? = null

        /**
         * See [AccountArrangementItem.countrySubDivision].
         */
        var countrySubDivision: String? = null

        /**
         * See [AccountArrangementItem.accountHolderNames].
         */
        var accountHolderNames: String? = null

        /**
         * See [AccountArrangementItem.accountHolderCountry].
         */
        var accountHolderCountry: String? = null

        /**
         * See [AccountArrangementItem.creditAccount].
         */
        var creditAccount: Boolean? = null

        /**
         * See [AccountArrangementItem.debitAccount].
         */
        var debitAccount: Boolean? = null

        /**
         * See [AccountArrangementItem.lastUpdateDate].
         */
        var lastUpdateDate: OffsetDateTime? = null

        /**
         * See [AccountArrangementItem.bankAlias].
         */
        var bankAlias: String? = null

        /**
         * See [AccountArrangementItem.sourceId].
         */
        var sourceId: String? = null

        /**
         * See [AccountArrangementItem.externalStateId].
         */
        var externalStateId: String? = null

        /**
         * See [AccountArrangementItem.externalParentId].
         */
        var externalParentId: String? = null

        /**
         * See [AccountArrangementItem.financialInstitutionId].
         */
        var financialInstitutionId: Long? = null

        /**
         * See [AccountArrangementItem.lastSyncDate].
         */
        var lastSyncDate: OffsetDateTime? = null

        /**
         * See [AccountArrangementItem.additions].
         */
        var additions: Map<String, String>? = null

        /**
         * See [AccountArrangementItem.unmaskableAttributes].
         */
        var unmaskableAttributes: Set<MaskableAttribute>? = null

        /**
         * See [AccountArrangementItem.displayName].
         */
        var displayName: String? = null

        /**
         * See [AccountArrangementItem.cardDetails].
         */
        var cardDetails: CardDetails? = null

        /**
         * See [AccountArrangementItem.interestDetails].
         */
        var interestDetails: InterestDetails? = null

        /**
         * See [AccountArrangementItem.reservedAmount].
         */
        var reservedAmount: BigDecimal? = null

        /**
         * See [AccountArrangementItem.remainingPeriodicTransfers].
         */
        var remainingPeriodicTransfers: BigDecimal? = null

        /**
         * See [AccountArrangementItem.nextClosingDate].
         */
        var nextClosingDate: LocalDate? = null

        /**
         * See [AccountArrangementItem.overdueSince].
         */
        var overdueSince: LocalDate? = null

        /**
         * See [AccountArrangementItem.externalAccountStatus].
         */
        var externalAccountStatus: String? = null

        /**
         * Builds an instance of [AccountArrangementItem]
         */
        @Suppress("ConstructorParameterNaming", "LongMethod")
        fun build() = AccountDetail(
            id = id,
            productId = productId,
            productKindName = productKindName,
            legalEntityIds = legalEntityIds,
            productTypeName = productTypeName,
            externalProductId = externalProductId,
            externalArrangementId = externalArrangementId,
            userPreferences = userPreferences,
            product = product,
            state = state,
            parentId = parentId,
            currency = currency,
            name = name,
            bookedBalance = bookedBalance,
            availableBalance = availableBalance,
            creditLimit = creditLimit,
            IBAN = IBAN,
            BBAN = BBAN,
            BIC = BIC,
            externalTransferAllowed = externalTransferAllowed,
            urgentTransferAllowed = urgentTransferAllowed,
            accruedInterest = accruedInterest,
            number = number,
            principalAmount = principalAmount,
            currentInvestmentValue = currentInvestmentValue,
            productNumber = productNumber,
            bankBranchCode = bankBranchCode,
            bankBranchCode2 = bankBranchCode2,
            accountOpeningDate = accountOpeningDate,
            accountInterestRate = accountInterestRate,
            valueDateBalance = valueDateBalance,
            creditLimitUsage = creditLimitUsage,
            creditLimitInterestRate = creditLimitInterestRate,
            creditLimitExpiryDate = creditLimitExpiryDate,
            startDate = startDate,
            termUnit = termUnit,
            termNumber = termNumber,
            interestPaymentFrequencyUnit = interestPaymentFrequencyUnit,
            interestPaymentFrequencyNumber = interestPaymentFrequencyNumber,
            maturityDate = maturityDate,
            maturityAmount = maturityAmount,
            autoRenewalIndicator = autoRenewalIndicator,
            interestSettlementAccount = interestSettlementAccount,
            outstandingPrincipalAmount = outstandingPrincipalAmount,
            monthlyInstalmentAmount = monthlyInstalmentAmount,
            amountInArrear = amountInArrear,
            minimumRequiredBalance = minimumRequiredBalance,
            creditCardAccountNumber = creditCardAccountNumber,
            validThru = validThru,
            applicableInterestRate = applicableInterestRate,
            remainingCredit = remainingCredit,
            outstandingPayment = outstandingPayment,
            minimumPayment = minimumPayment,
            minimumPaymentDueDate = minimumPaymentDueDate,
            totalInvestmentValue = totalInvestmentValue,
            debitCards = debitCards,
            accountHolderAddressLine1 = accountHolderAddressLine1,
            accountHolderAddressLine2 = accountHolderAddressLine2,
            accountHolderStreetName = accountHolderStreetName,
            town = town,
            postCode = postCode,
            countrySubDivision = countrySubDivision,
            accountHolderNames = accountHolderNames,
            accountHolderCountry = accountHolderCountry,
            creditAccount = creditAccount,
            debitAccount = debitAccount,
            lastUpdateDate = lastUpdateDate,
            bankAlias = bankAlias,
            sourceId = sourceId,
            externalStateId = externalStateId,
            externalParentId = externalParentId,
            financialInstitutionId = financialInstitutionId,
            lastSyncDate = lastSyncDate,
            additions = additions,
            unmaskableAttributes = unmaskableAttributes,
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
 * DSL to create [AccountDetail].
 *
 * @see [AccountDetail].
 */
@Suppress("FunctionName")
fun AccountDetail(initializer: AccountDetail.Builder.() -> Unit): AccountDetail {
    return AccountDetail.Builder().apply(initializer).build()
}
