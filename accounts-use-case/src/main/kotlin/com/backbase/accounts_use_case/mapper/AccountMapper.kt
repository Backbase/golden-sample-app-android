package com.backbase.accounts_use_case.mapper

import com.backbase.accounts_journey.common.DataToDomainMapper
import com.backbase.accounts_journey.domain.model.account.AccountSummary
import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary

class AccountMapper : DataToDomainMapper<ProductSummary, AccountSummary> {

    override fun mapToDomain(dataModel: ProductSummary): AccountSummary {
        return AccountSummary {
           // dataModel.accountSummary.savingsAccounts
//            currentAccounts = dataModel.accountSummary.currentAccounts
//            savingsAccounts
        }
    }
}