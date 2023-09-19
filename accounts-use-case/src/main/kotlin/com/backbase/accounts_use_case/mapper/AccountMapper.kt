package com.backbase.accounts_use_case.mapper

import com.backbase.android.client.gen2.arrangementclient2.model.BaseProduct as BaseProductDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.CardDetails as CardDetailsDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.CurrentAccount as CurrentAccountDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.CurrentAccountProductKinds as CurrentAccountsDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.CustomProductKind as CustomProductDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.DebitCardItem as DebitCardItemDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.GeneralAccount as GeneralAccountDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.InterestDetails as InterestDetailsDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.MaskableAttribute as MaskableAttributeDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary as ProductSummaryDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.SavingsAccount as SavingsAccountDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.SavingsAccountProductKinds as SavingsAccountsDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.StateItem as StateItemDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.SummaryAggregatedBalance as AggregatedBalanceDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.TimeUnit as TimeUnitDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.UserPreferences as UserPreferencesDataModel
import com.backbase.accounts_journey.domain.model.product_summary.AccountSummary
import com.backbase.accounts_journey.domain.model.product_summary.MaskableAttribute
import com.backbase.accounts_journey.domain.model.product_summary.UserPreferences
import com.backbase.accounts_journey.domain.model.product_summary.common.AggregatedBalance
import com.backbase.accounts_journey.domain.model.product_summary.common.BaseProduct
import com.backbase.accounts_journey.domain.model.product_summary.common.CardDetails
import com.backbase.accounts_journey.domain.model.product_summary.common.DebitCardItem
import com.backbase.accounts_journey.domain.model.product_summary.common.InterestDetails
import com.backbase.accounts_journey.domain.model.product_summary.common.ProductState
import com.backbase.accounts_journey.domain.model.product_summary.common.TimeUnit
import com.backbase.accounts_journey.domain.model.product_summary.current_accounts.CurrentAccount
import com.backbase.accounts_journey.domain.model.product_summary.current_accounts.CurrentAccounts
import com.backbase.accounts_journey.domain.model.product_summary.custom_products.CustomProducts
import com.backbase.accounts_journey.domain.model.product_summary.custom_products.GeneralAccount
import com.backbase.accounts_journey.domain.model.product_summary.savings_accounts.SavingsAccount
import com.backbase.accounts_journey.domain.model.product_summary.savings_accounts.SavingsAccounts

internal fun ProductSummaryDataModel.mapToDomain(): AccountSummary {
    val data = this@mapToDomain
    return AccountSummary {
        customProducts = data.customProductKinds.mapToDomain()
        currentAccounts = data.currentAccounts?.mapToDomain()
        savingsAccounts = data.savingsAccounts?.mapToDomain()
    }
}

@JvmName("CustomProductMapper")
internal fun List<CustomProductDataModel>.mapToDomain(): List<CustomProducts> {
    return this.map {
        CustomProducts {
            id = it.id
            name = it.name
            aggregatedBalance = it.aggregatedBalance?.mapToDomain()
            products = it.products.mapToDomain()
        }
    }
}

@JvmName("CurrentAccountsMapper")
internal fun CurrentAccountsDataModel.mapToDomain(): CurrentAccounts {
    val data = this@mapToDomain
    return CurrentAccounts {
        products = data.products.mapToDomain()
        name = data.name
        aggregatedBalance = data.aggregatedBalance?.mapToDomain()
        additions = data.additions
    }
}

internal fun SavingsAccountsDataModel.mapToDomain(): SavingsAccounts {
    val data = this@mapToDomain
    return SavingsAccounts {
        products = data.products.mapToDomain()
        name = data.name
        aggregatedBalance = data.aggregatedBalance?.mapToDomain()
        additions = data.additions
    }
}

internal fun AggregatedBalanceDataModel.mapToDomain(): AggregatedBalance {
    val data = this@mapToDomain
    return AggregatedBalance {
        value = data.value
        currency = data.currency
        additions = data.additions
    }
}

@JvmName("generalAccountMapper")
internal fun List<GeneralAccountDataModel>.mapToDomain(): List<GeneralAccount> {
    return this.map { data ->
        GeneralAccount {
            debitCardItems = data.debitCardsItems.mapToDomain()
            bookedBalance = data.bookedBalance
            availableBalance = data.availableBalance
            creditLimit = data.creditLimit
            IBAN = data.IBAN
            BBAN = data.BBAN
            BIC = data.BIC
            unMaskableAttributes = data.unmaskableAttributes?.mapToDomain()
            currency = data.currency
            urgentTransferAllowed = data.urgentTransferAllowed
            bankBranchCode = data.bankBranchCode
            bankBranchCode2 = null
            accountInterestRate = data.accountInterestRate
            valueDateBalance = data.valueDateBalance
            creditLimitUsage = data.creditLimitUsage
            creditLimitInterestRate = data.creditLimitInterestRate
            creditLimitExpiryDate = data.creditLimitExpiryDate
            accruedInterest = data.accruedInterest
            startDate = data.startDate
            minimumRequiredBalance = data.minimumRequiredBalance
            accountHolderAddressLine1 = data.accountHolderAddressLine1
            accountHolderAddressLine2 = data.accountHolderAddressLine2
            accountHolderStreetName = data.accountHolderStreetName
            town = data.town
            postCode = data.postCode
            countrySubDivision = data.countrySubDivision
            accountHolderNames = data.accountHolderNames
            accountHolderCountry = data.accountHolderCountry
            number = data.number
            cardNumber = data.cardNumber
            creditCardAccountNumber = data.creditCardAccountNumber
            validThru = data.validThru
            applicableInterestRate = data.applicableInterestRate
            remainingCredit = data.remainingCredit
            outstandingPayment = data.outstandingPayment
            minimumPayment = data.minimumPayment
            minimumPaymentDueDate = data.minimumPaymentDueDate
            currentInvestmentValue = data.currentInvestmentValue
            productNumber = data.productNumber
            principalAmount = data.principalAmount
            termUnit = data.termUnit?.mapToDomain()
            termNumber = data.termNumber
            outstandingPrincipalAmount = data.outstandingPrincipalAmount
            monthlyInstalmentAmount = data.monthlyInstalmentAmount
            amountInArrear = data.amountInArrear
            interestSettlementAccount = data.interestSettlementAccount
            maturityDate = data.maturityDate
            maturityAmount = data.maturityAmount
            autoRenewalIndicator = data.autoRenewalIndicator
            interestPaymentFrequencyUnit = data.interestPaymentFrequencyUnit?.mapToDomain()
            interestPaymentFrequencyNumber = data.interestPaymentFrequencyNumber
            creditAccount = data.creditAccount
            debitAccount = data.debitAccount
            id = data.id
            name = data.name
            externalTransferAllowed = data.externalTransferAllowed
            crossCurrencyAllowed = data.crossCurrencyAllowed
            productKindName = data.productKindName
            productTypeName = data.productTypeName
            bankAlias = data.bankAlias
            sourceId = data.sourceId
            accountOpeningDate = data.accountOpeningDate
            lastUpdateDate = data.lastUpdateDate
            userPreferences = data.userPreferences?.mapToDomain()
            state = data.state?.mapToDomain()
            parentId = data.parentId
            subArrangements = data.subArrangements?.mapToDomain()
            financialInstitutionId = data.financialInstitutionId
            lastSyncDate = data.lastSyncDate
            additions = data.additions
            displayName = data.displayName
            cardDetails = data.cardDetails?.mapToDomain()
            interestDetails = data.interestDetails?.mapToDomain()
            reservedAmount = data.reservedAmount
            remainingPeriodicTransfers = data.remainingPeriodicTransfers
            nextClosingDate = data.nextClosingDate
            overdueSince = data.overdueSince
            externalAccountStatus = data.externalAccountStatus
        }
    }
}

@JvmName("debitCardItemMapper")
internal fun Set<DebitCardItemDataModel>.mapToDomain(): Set<DebitCardItem> {
    return this.map { data ->
        DebitCardItem {
            number = data.number
            expiryDate = data.expiryDate
            cardId = data.cardId
            cardHolderName = data.cardholderName
            cardType = data.cardType
            cardStatus = data.cardStatus
            additions = data.additions
        }
    }.toSet()
}

internal fun Set<MaskableAttributeDataModel>.mapToDomain(): Set<MaskableAttribute> {
    return this.map { data ->
        when (data) {
            com.backbase.android.client.gen2.arrangementclient2.model.MaskableAttribute.IBAN -> MaskableAttribute.IBAN
            com.backbase.android.client.gen2.arrangementclient2.model.MaskableAttribute.BBAN -> MaskableAttribute.BBAN
            com.backbase.android.client.gen2.arrangementclient2.model.MaskableAttribute.Number -> MaskableAttribute.Number
        }
    }.toSet()
}

internal fun TimeUnitDataModel.mapToDomain(): TimeUnit {
    return when (this) {
        TimeUnitDataModel.D -> TimeUnit.Day
        TimeUnitDataModel.W -> TimeUnit.Week
        TimeUnitDataModel.M -> TimeUnit.Month
        TimeUnitDataModel.Y -> TimeUnit.Year
    }
}

internal fun UserPreferencesDataModel.mapToDomain(): UserPreferences {
    val data = this@mapToDomain
    return UserPreferences {
        alias = data.alias
        visible = data.visible
        favorite = data.favorite
        additions = data.additions
    }
}

internal fun StateItemDataModel.mapToDomain(): ProductState {
    val data = this@mapToDomain
    return ProductState {
        externalStateId = data.externalStateId
        state = data.state
        additions = data.additions
    }
}

internal fun List<BaseProductDataModel>.mapToDomain(): List<BaseProduct> {
    return this.mapNotNull { data ->
        BaseProduct {
            id = data.id
            name = data.name
            externalTransferAllowed = data.externalTransferAllowed
            crossCurrencyAllowed = data.crossCurrencyAllowed
            productKindName = data.productKindName
            productTypeName = data.productTypeName
            bankAlias = data.bankAlias
            sourceId = data.sourceId
            visible = data.visible
            accountOpeningDate = data.accountOpeningDate
            lastUpdateDate = data.lastUpdateDate
            userPreferences = data.userPreferences?.mapToDomain()
            state = data.state?.mapToDomain()
            parentId = data.parentId
            subArrangements = data.subArrangements?.mapToDomain()
            financialInstitutionId = data.financialInstitutionId
            lastSyncDate = data.lastSyncDate
            additions = data.additions
            cardDetails = data.cardDetails?.mapToDomain()
            interestDetails = data.interestDetails?.mapToDomain()
            reservedAmount = data.reservedAmount
            remainingPeriodicTransfers = data.remainingPeriodicTransfers
            nextClosingDate = data.nextClosingDate
            overdueSince = data.overdueSince
            externalAccountStatus = data.externalAccountStatus
            bankBranchCode2 = data.bankBranchCode2
        }
    }
}

internal fun CardDetailsDataModel.mapToDomain(): CardDetails {
    val data = this@mapToDomain
    return CardDetails {
        cardProvider = data.cardProvider
        secured = data.secured
        availableCashCredit = data.availableCashCredit
        cashCreditLimit = data.cashCreditLimit
        lastPaymentDate = data.lastPaymentDate
        lastPaymentAmount = data.lastPaymentAmount
        latePaymentFee = data.latePaymentFee
        previousStatementDate = data.previousStatementDate
        previousStatementBalance = data.previousStatementBalance
        statementBalance = data.statementBalance
        additions = data.additions
    }
}

internal fun InterestDetailsDataModel.mapToDomain(): InterestDetails {
    val data = this@mapToDomain
    return InterestDetails {
        lastYearAccruedInterest = data.lastYearAccruedInterest
        dividendWithheldYTD = data.dividendWithheldYTD
        annualPercentageYield = data.annualPercentageYield
        cashAdvanceInterestRate = data.cashAdvanceInterestRate
        penaltyInterestRate = data.penaltyInterestRate
        additions = data.additions
    }
}

@JvmName("CurrentAccountMapper")
internal fun List<CurrentAccountDataModel>.mapToDomain(): List<CurrentAccount> {
    return this.map { data ->
        CurrentAccount {
            debitCardItems = data.debitCardsItems.mapToDomain()
            bookedBalance = data.bookedBalance
            availableBalance = data.availableBalance
            creditLimit = data.creditLimit
            IBAN = data.IBAN
            BBAN = data.BBAN
            BIC = data.BIC
            unMaskableAttributes = data.unmaskableAttributes?.mapToDomain()
            currency = data.currency
            urgentTransferAllowed = data.urgentTransferAllowed
            bankBranchCode = data.bankBranchCode
            bankBranchCode2 = null
            accountInterestRate = data.accountInterestRate
            valueDateBalance = data.valueDateBalance
            creditLimitUsage = data.creditLimitUsage
            creditLimitInterestRate = data.creditLimitInterestRate
            creditLimitExpiryDate = data.creditLimitExpiryDate
            accruedInterest = data.accruedInterest
            accountHolderNames = data.accountHolderNames
            startDate = data.startDate
            minimumRequiredBalance = data.minimumRequiredBalance
            accountHolderAddressLine1 = data.accountHolderAddressLine1
            accountHolderAddressLine2 = data.accountHolderAddressLine2
            accountHolderStreetName = data.accountHolderStreetName
            town = data.town
            postCode = data.postCode
            countrySubDivision = data.countrySubDivision
            creditAccount = data.creditAccount
            debitAccount = data.debitAccount
            accountHolderCountry = data.accountHolderCountry
            id = data.id
            name = data.name
            externalTransferAllowed = data.externalTransferAllowed
            crossCurrencyAllowed = data.crossCurrencyAllowed
            productKindName = data.productKindName
            productTypeName = data.productTypeName
            bankAlias = data.bankAlias
            sourceId = data.sourceId
            accountOpeningDate = data.accountOpeningDate
            lastUpdateDate = data.lastUpdateDate
            userPreferences = data.userPreferences?.mapToDomain()
            state = data.state?.mapToDomain()
            parentId = data.parentId
            subArrangements = data.subArrangements?.mapToDomain()
            financialInstitutionId = data.financialInstitutionId
            lastSyncDate = data.lastSyncDate
            additions = data.additions
            displayName = data.displayName
            cardDetails = data.cardDetails?.mapToDomain()
            interestDetails = data.interestDetails?.mapToDomain()
            reservedAmount = data.reservedAmount
            remainingPeriodicTransfers = data.remainingPeriodicTransfers
            nextClosingDate = data.nextClosingDate
            overdueSince = data.overdueSince
            externalAccountStatus = data.externalAccountStatus
        }
    }
}

@JvmName("SavingsAccountMapper")
internal fun List<SavingsAccountDataModel>.mapToDomain(): List<SavingsAccount> {
    return this.map { data ->
        SavingsAccount {
            bookedBalance = data.bookedBalance
            availableBalance = data.availableBalance
            accruedInterest = data.accruedInterest
            IBAN = data.IBAN
            BBAN = data.BBAN
            BIC = data.BIC
            unMaskableAttributes = data.unmaskableAttributes?.mapToDomain()
            currency = data.currency
            urgentTransferAllowed = data.urgentTransferAllowed
            bankBranchCode = data.bankBranchCode
            bankBranchCode2 = null
            accountInterestRate = data.accountInterestRate
            minimumRequiredBalance = data.minimumRequiredBalance
            startDate = data.startDate
            termUnit = data.termUnit?.mapToDomain()
            termNumber = data.termNumber
            maturityDate = data.maturityDate
            maturityAmount = data.maturityAmount
            autoRenewalIndicator = data.autoRenewalIndicator
            interestPaymentFrequencyUnit = data.interestPaymentFrequencyUnit?.mapToDomain()
            interestPaymentFrequencyNumber = data.interestPaymentFrequencyNumber
            principalAmount = data.principalAmount
            interestSettlementAccount = data.interestSettlementAccount
            accountHolderNames = data.accountHolderNames
            valueDateBalance = data.valueDateBalance
            accountHolderAddressLine1 = data.accountHolderAddressLine1
            accountHolderAddressLine2 = data.accountHolderAddressLine2
            accountHolderStreetName = data.accountHolderStreetName
            town = data.town
            postCode = data.postCode
            countrySubDivision = data.countrySubDivision
            accountHolderCountry = data.accountHolderCountry
            creditAccount = data.creditAccount
            debitAccount = data.debitAccount
            id = data.id
            name = data.name
            externalTransferAllowed = data.externalTransferAllowed
            crossCurrencyAllowed = data.crossCurrencyAllowed
            productKindName = data.productKindName
            productTypeName = data.productTypeName
            bankAlias = data.bankAlias
            sourceId = data.sourceId
            accountOpeningDate = data.accountOpeningDate
            lastUpdateDate = data.lastUpdateDate
            userPreferences = data.userPreferences?.mapToDomain()
            state = data.state?.mapToDomain()
            parentId = data.parentId
            subArrangements = data.subArrangements?.mapToDomain()
            financialInstitutionId = data.financialInstitutionId
            lastSyncDate = data.lastSyncDate
            additions = data.additions
            displayName = data.displayName
            cardDetails = data.cardDetails?.mapToDomain()
            interestDetails = data.interestDetails?.mapToDomain()
            reservedAmount = data.reservedAmount
            remainingPeriodicTransfers = data.remainingPeriodicTransfers
            nextClosingDate = data.nextClosingDate
            overdueSince = data.overdueSince
            externalAccountStatus = data.externalAccountStatus
        }
    }
}
