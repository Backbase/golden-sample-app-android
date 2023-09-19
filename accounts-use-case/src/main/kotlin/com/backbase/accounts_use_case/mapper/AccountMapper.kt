package com.backbase.accounts_use_case.mapper

import com.backbase.accounts_journey.common.DataToDomainMapper
import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary as ProductSummaryData
import com.backbase.accounts_journey.domain.model.product_summary.AccountSummary
import com.backbase.android.client.gen2.arrangementclient2.model.SavingsAccount
import com.backbase.android.client.gen2.arrangementclient2.model.SavingsAccountProductKinds

class AccountMapper : DataToDomainMapper<ProductSummaryData, AccountSummary> {

    override fun mapToDomain(dataModel: ProductSummaryData): AccountSummary {
        return AccountSummary {
            // dataModel.accountSummary.savingsAccounts
//            currentAccounts = dataModel.accountSummary.currentAccounts
//            savingsAccounts
        }
    }

    private fun mapSavingAccounts(savingsAccounts: SavingsAccountProductKinds?): SavingsAccount? {
        if (savingsAccounts == null) return null

        savingsAccounts.additions

        return null
    }
}