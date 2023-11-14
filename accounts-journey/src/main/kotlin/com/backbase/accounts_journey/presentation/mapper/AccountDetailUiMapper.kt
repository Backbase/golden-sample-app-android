package com.backbase.accounts_journey.presentation.mapper

import com.backbase.accounts_journey.common.mapCatching
import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail
import com.backbase.accounts_journey.presentation.model.AccountDetailUiModel
import com.backbase.android.design.amount.AmountFormat
import java.math.BigDecimal
import java.time.format.DateTimeFormatter

class AccountDetailUiMapper {

    fun mapToUi(domain: AccountDetail): AccountDetailUiModel? {
        return domain.mapCatching {
            AccountDetailUiModel(
                id = this.id,
                name = this.name!!,
                BBAN = this.BBAN!!,
                availableBalance = formatCurrency(this.currency, this.availableBalance.toString()),
                accountHolderNames = this.accountHolderNames!!,
                productTypeName = this.productTypeName!!,
                bankBranchCode = this.bankBranchCode!!,
                lastUpdateDate = this.lastUpdateDate!!.format(DateTimeFormatter.ofPattern("M/d/yy h:mma")),
                accountInterestRate = this.accountInterestRate.toString(),
                accruedInterest = formatCurrency(this.currency, this.accruedInterest.toString()),
                creditLimit = formatCurrency(this.currency, this.creditLimit.toString()),
                accountOpeningDate = this.accountOpeningDate!!.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"))
            )
        }
    }

    private fun formatCurrency(currency: String?, amount: String?): String {
        return AmountFormat().apply {
            enableAbbreviation = false
            currencyCode = currency
        }.format(amount?.toBigDecimal() ?: BigDecimal.ZERO)
    }
}
