package com.backbase.accounts_journey.presentation.mapper

import com.backbase.accounts_journey.common.mapCatching
import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail
import com.backbase.accounts_journey.presentation.model.AccountDetailUiModel

class AccountDetailUiMapper {

    // TODO: fix the nullables
    fun mapToUi(domain: AccountDetail): AccountDetailUiModel? {
        return domain.mapCatching {
            AccountDetailUiModel(
                id = this.id,
                name = this.name!!,
                BBAN = this.BBAN!!,
                availableBalance = this.availableBalance.toString(),
                accountHolderNames = this.accountHolderNames!!,
                productTypeName = this.productTypeName!!,
                bankBranchCode = this.bankBranchCode!!,
                lastUpdateDate = this.lastUpdateDate.toString(),
                accountInterestRate = this.accountInterestRate.toString(),
                accruedInterest = this.accruedInterest.toString(),
                creditLimit = this.creditLimit.toString(),
                accountOpeningDate = this.accountOpeningDate.toString()
            )
        }
    }
}
