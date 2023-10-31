package com.backbase.accounts_use_case.mapper

import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail
import com.backbase.accounts_journey.domain.model.account_detail.AccountUserPreferences
import com.backbase.accounts_journey.domain.model.account_detail.ExternalProductItem
import com.backbase.accounts_journey.domain.model.account_detail.ExternalProductKindItem
import com.backbase.android.client.gen2.arrangementclient2.model.AccountArrangementItem as AccountDetailDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.AccountUserPreferences as AccountUserPreferencesDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.ExternalProductItem as ExternalProductItemDataModel
import com.backbase.android.client.gen2.arrangementclient2.model.ExternalProductKindItem as ExternalProductKindItemDataModel

@Suppress("LongMethod")
internal fun AccountDetailDataModel.mapToDomain(): AccountDetail {
    val data = this@mapToDomain
    return AccountDetail {
        id = data.id
        productId = data.productId
        productKindName = data.productKindName
        legalEntityIds = data.legalEntityIds
        productTypeName = data.productTypeName
        externalProductId = data.externalProductId
        externalArrangementId = data.externalArrangementId
        userPreferences = data.userPreferences?.mapToDomain()
        product = data.product?.mapToDomain()
        state = data.state?.mapToDomain()
        parentId = data.parentId
        currency = data.currency
        name = data.name
        bookedBalance = data.bookedBalance
        availableBalance = data.availableBalance
        creditLimit = data.creditLimit
        IBAN = data.IBAN
        BBAN = data.BBAN
        unmaskableAttributes = data.unmaskableAttributes?.mapToDomain()
        BIC = data.BIC
        externalTransferAllowed = data.externalTransferAllowed
        urgentTransferAllowed = data.urgentTransferAllowed
        accruedInterest = data.accruedInterest
        number = data.number
        principalAmount = data.principalAmount
        currentInvestmentValue = data.currentInvestmentValue
        productNumber = data.productNumber
        bankBranchCode = data.bankBranchCode
        bankBranchCode2 = data.bankBranchCode2
        accountOpeningDate = data.accountOpeningDate
        accountInterestRate = data.accountInterestRate
        valueDateBalance = data.valueDateBalance
        creditLimitUsage = data.creditLimitUsage
        creditLimitInterestRate = data.creditLimitInterestRate
        creditLimitExpiryDate = data.creditLimitExpiryDate
        startDate = data.startDate
        termUnit = data.termUnit?.mapToDomain()
        termNumber = data.termNumber
        interestPaymentFrequencyUnit = data.interestPaymentFrequencyUnit?.mapToDomain()
        interestPaymentFrequencyNumber = data.interestPaymentFrequencyNumber
        maturityDate = data.maturityDate
        maturityAmount = data.maturityAmount
        autoRenewalIndicator = data.autoRenewalIndicator
        interestSettlementAccount = data.interestSettlementAccount
        outstandingPrincipalAmount = data.outstandingPrincipalAmount
        monthlyInstalmentAmount = data.monthlyInstalmentAmount
        amountInArrear = data.amountInArrear
        minimumRequiredBalance = data.minimumRequiredBalance
        creditCardAccountNumber = data.creditCardAccountNumber
        validThru = data.validThru
        applicableInterestRate = data.applicableInterestRate
        remainingCredit = data.remainingCredit
        outstandingPayment = data.outstandingPayment
        minimumPayment = data.minimumPayment
        minimumPaymentDueDate = data.minimumPaymentDueDate
        totalInvestmentValue = data.totalInvestmentValue
        debitCards = data.debitCards?.mapToDomain()
        accountHolderAddressLine1 = data.accountHolderAddressLine1
        accountHolderAddressLine2 = data.accountHolderAddressLine2
        accountHolderStreetName = data.accountHolderStreetName
        town = data.town
        postCode = data.postCode
        countrySubDivision = data.countrySubDivision
        accountHolderNames = data.accountHolderNames
        accountHolderCountry = data.accountHolderCountry
        creditAccount = data.creditAccount
        debitAccount = data.debitAccount
        lastUpdateDate = data.lastUpdateDate
        bankAlias = data.bankAlias
        sourceId = data.sourceId
        externalStateId = data.externalStateId
        externalParentId = data.externalParentId
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

internal fun AccountUserPreferencesDataModel.mapToDomain(): AccountUserPreferences {
    val data = this@mapToDomain
    return AccountUserPreferences {
        arrangementId = data.arrangementId
        alias = data.alias
        visible = data.visible
        favorite = data.favorite
        additions = data.additions
    }
}

internal fun ExternalProductItemDataModel.mapToDomain(): ExternalProductItem {
    val data = this@mapToDomain
    return ExternalProductItem {
        externalId = data.externalId
        externalTypeId = data.externalTypeId
        typeName = data.typeName
        productKind = data.productKind?.mapToDomain()
        additions = data.additions
    }
}

internal fun ExternalProductKindItemDataModel.mapToDomain(): ExternalProductKindItem {
    val data = this@mapToDomain
    return ExternalProductKindItem {
        externalKindId = data.externalKindId
        kindName = data.kindName
        kindUri = data.kindUri
        additions = data.additions
    }
}
