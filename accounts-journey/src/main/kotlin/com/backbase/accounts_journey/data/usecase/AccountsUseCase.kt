package com.backbase.accounts_journey.data.usecase

import com.backbase.accounts_journey.common.Result
import com.backbase.accounts_journey.domain.model.product_summary.AccountSummary

/**
 * The use cases for the accounts/products.
 */
interface AccountsUseCase {

    /**
     * Method that fetches a list of accounts.
     *
     * @return an instance of [AccountSummary]
     */
    suspend fun getAccounts(): Result<AccountSummary>
}
