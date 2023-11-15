package com.backbase.accounts_journey.presentation.mapper

import com.backbase.accounts_journey.R
import com.backbase.accounts_journey.common.mapCatching
import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail
import com.backbase.accounts_journey.presentation.model.AccountDetailUiModel
import com.backbase.android.design.amount.AmountFormat
import java.math.BigDecimal
import java.math.RoundingMode
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
                accountInterestRate = this.accountInterestRate?.let { rate ->
                    rate.setScale(2, RoundingMode.CEILING).let { "$it%" }
                },
                accruedInterest = formatCurrency(this.currency, this.accruedInterest.toString()),
                creditLimit = formatCurrency(this.currency, this.creditLimit.toString()),
                accountOpeningDate = this.accountOpeningDate!!.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
                icon = getIcon(this.product!!.externalTypeId!!)
            )
        }
    }

    private fun formatCurrency(currency: String?, amount: String?): String {
        return AmountFormat().apply {
            enableAbbreviation = false
            currencyCode = currency
        }.format(amount?.toBigDecimal() ?: BigDecimal.ZERO)
    }

    private fun getIcon(productType: String): Int {
        return when (AccountType.getValue(productType)) {
            AccountType.CURRENT_ACCOUNT -> R.drawable.ic_account_type_current_v2
            AccountType.SAVINGS_ACCOUNT -> R.drawable.ic_account_type_savings_term_deposits_v2
            AccountType.TERM_DEPOSIT -> R.drawable.ic_account_type_savings_term_deposits_v2
            AccountType.LOAN -> R.drawable.ic_account_type_loan_v2
            AccountType.CREDIT_CARD -> R.drawable.ic_account_type_credit_debit_card_v2
            AccountType.DEBIT_CARD -> R.drawable.ic_account_type_credit_debit_card_v2
            AccountType.INVESTMENT_ACCOUNT -> R.drawable.ic_account_type_investment_v2
            AccountType.GENERAL_ACCOUNT -> R.drawable.ic_account_type_card_v2
        }
    }

    enum class AccountType(val id: String) {
        CURRENT_ACCOUNT("current-account"),
        SAVINGS_ACCOUNT("savings-account"),
        TERM_DEPOSIT("term-deposit"),
        LOAN("loan"),
        CREDIT_CARD("credit-card"),
        DEBIT_CARD("debit-card"),
        INVESTMENT_ACCOUNT("investment-account"),
        GENERAL_ACCOUNT("general-account");

        companion object {
            fun getValue(id: String): AccountType {
                return values().first { it.id == id }
                throw IllegalArgumentException("Value not found, use default")
            }
        }
    }
}
