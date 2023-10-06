package com.backbase.fake_accounts_use_case

import com.backbase.accounts_journey.common.Result
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.domain.model.product_summary.AccountSummary

class FakeContactsUseCase(
    private var accountList: AccountSummary
) : AccountsUseCase {
    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        return Result.Success(accountList)
    }


}
