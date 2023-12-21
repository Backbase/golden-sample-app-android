package com.backbase.fake_accounts_use_case

import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.domain.model.account_summary.AccountSummary

class FakeAccountsUseCase(
    private var accountList: AccountSummary
) : AccountsUseCase {
    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        return Result.success(accountList)
    }
}
