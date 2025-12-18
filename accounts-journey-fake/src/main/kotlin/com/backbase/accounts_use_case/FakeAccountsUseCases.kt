package com.backbase.accounts_use_case

import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.domain.model.account_summary.AccountSummary

class SuccessAccountsUseCase(
    private var accountList: AccountSummary
) : AccountsUseCase {
    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        return Result.success(accountList)
    }
}

class ErrorAccountsUseCase(
    private var accountList: AccountSummary,
    private val error: Exception = Exception("Error fetching account summary"),
    private val amountOfRefresh: Int = 0
) : AccountsUseCase {
    private var count: Int = 0

    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        if (count < amountOfRefresh) {
            count++
            return Result.success(accountList)
        }
        return Result.failure(error)
    }
}
