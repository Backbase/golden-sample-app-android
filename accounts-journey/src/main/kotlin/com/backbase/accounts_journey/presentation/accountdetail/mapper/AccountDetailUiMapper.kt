package com.backbase.accounts_journey.presentation.accountdetail.mapper

import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.configuration.icon.IconsConfiguration
import com.backbase.accounts_journey.domain.model.AccountType
import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail
import com.backbase.accounts_journey.presentation.accountdetail.model.AccountDetailUiModel
import com.backbase.android.design.amount.AmountFormat
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.format.DateTimeFormatter

/**
 *  A AccountDetail mapper from domain models to UI model.
 *
 *  Created by Backbase R&D B.V on 16/11/2023.
 */
class AccountDetailUiMapper(accountsJourneyConfiguration: AccountsJourneyConfiguration) {

    private val iconsConfiguration: IconsConfiguration by lazy {
        accountsJourneyConfiguration.iconsConfiguration
    }

    fun mapToUi(domain: AccountDetail): AccountDetailUiModel {
        return AccountDetailUiModel(
            id = domain.id,
            name = domain.name,
            BBAN = domain.BBAN ?: domain.BIC,
            availableBalance = formatCurrency(domain.currency, domain.availableBalance),
            accountHolderNames = domain.accountHolderNames,
            productKindName = domain.product?.productKind?.kindName,
            bankBranchCode = domain.bankBranchCode,
            lastUpdateDate = domain.lastUpdateDate?.format(DateTimeFormatter.ofPattern("M/d/yy h:mma")),
            accountInterestRate = domain.accountInterestRate?.let { rate ->
                rate.setScale(2, RoundingMode.CEILING).let { "$it%" }
            },
            accruedInterest = formatCurrency(domain.currency, domain.accruedInterest),
            creditLimit = formatCurrency(domain.currency, domain.creditLimit),
            accountOpeningDate = domain.accountOpeningDate!!.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
            icon = getIcon(domain.product?.externalId)
        )
    }

    private fun formatCurrency(currency: String?, amount: BigDecimal?): String {
        return AmountFormat().apply {
            enableAbbreviation = false
            currencyCode = currency
        }.format(amount ?: BigDecimal.ZERO)
    }

    private fun getIcon(productType: String?): Int {
        if (productType == null) {
            return iconsConfiguration.iconCustomProduct
        }

        return when (AccountType.getValueOrNull(productType)) {
            AccountType.CURRENT_ACCOUNT -> iconsConfiguration.iconCurrentAccount
            AccountType.SAVINGS_ACCOUNT -> iconsConfiguration.iconSavingsAccount
            AccountType.TERM_DEPOSIT -> iconsConfiguration.iconTermDeposit
            AccountType.LOAN -> iconsConfiguration.iconLoan
            AccountType.CREDIT_CARD -> iconsConfiguration.iconCreditCard
            AccountType.DEBIT_CARD -> iconsConfiguration.iconDebitCard
            AccountType.INVESTMENT_ACCOUNT -> iconsConfiguration.iconInvestmentAccount
            else -> iconsConfiguration.iconCustomProduct
        }
    }
}
