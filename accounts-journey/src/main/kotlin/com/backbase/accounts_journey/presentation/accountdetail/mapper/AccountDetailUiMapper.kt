package com.backbase.accounts_journey.presentation.accountdetail.mapper

import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.configuration.icon.IconsConfiguration
import com.backbase.accounts_journey.domain.model.AccountType
import com.backbase.accounts_journey.presentation.accountdetail.model.AccountDetailUiModel
import com.backbase.android.client.gen2.arrangementclient2.model.AccountArrangementItem
import com.backbase.android.design.amount.AmountFormat
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.format.DateTimeFormatter

/**
 *  A AccountDetail mapper from DTO models to UI model.
 *
 *  Created by Backbase R&D B.V on 16/11/2023.
 */
class AccountDetailUiMapper(accountsJourneyConfiguration: AccountsJourneyConfiguration) {

    private val iconsConfiguration: IconsConfiguration by lazy {
        accountsJourneyConfiguration.iconsConfiguration
    }

    fun mapToUi(dto: AccountArrangementItem): AccountDetailUiModel {
        return AccountDetailUiModel(
            id = dto.id,
            name = dto.name,
            BBAN = dto.BBAN ?: dto.BIC,
            availableBalance = formatCurrency(dto.currency, dto.availableBalance),
            accountHolderNames = dto.accountHolderNames,
            productKindName = dto.product?.productKind?.kindName,
            bankBranchCode = dto.bankBranchCode,
            lastUpdateDate = dto.lastUpdateDate?.format(DateTimeFormatter.ofPattern("M/d/yy h:mma")),
            accountInterestRate = dto.accountInterestRate?.let { rate ->
                rate.setScale(2, RoundingMode.CEILING).let { "$it%" }
            },
            accruedInterest = formatCurrency(dto.currency, dto.accruedInterest),
            creditLimit = formatCurrency(dto.currency, dto.creditLimit),
            accountOpeningDate = dto.accountOpeningDate?.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
            icon = getIcon(dto.product?.externalId)
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
