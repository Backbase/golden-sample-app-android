package com.backbase.fake_accounts_use_case

import com.backbase.domain.data.AccountsUseCase
import com.backbase.domain.model.product_summary.AccountSummary

class FakeAccountsUseCase(
    private var accountList: AccountSummary
) : AccountsUseCase {
    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        return Result.success(accountList)
    }
}
