package com.backbase.accounts_use_case

import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary

class SuccessAccountsUseCase(
    private var accountList: ProductSummary
) : AccountsUseCase {
    override suspend fun getAccountSummary(useCache: Boolean): Result<ProductSummary> {
        return Result.success(accountList)
    }
}

class ErrorAccountsUseCase(
    private var accountList: ProductSummary,
    private val error: Exception = Exception("Error fetching account summary"),
    private val amountOfRefresh: Int = 0
) : AccountsUseCase {
    private var count: Int = 0

    override suspend fun getAccountSummary(useCache: Boolean): Result<ProductSummary> {
        if (count < amountOfRefresh) {
            count++
            return Result.success(accountList)
        }
        return Result.failure(error)
    }
}
