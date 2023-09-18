package com.backbase.accounts_journey.data.usecase

import com.backbase.accounts_journey.domain.model.account.AccountSummary
import com.backbase.accounts_journey.common.Result

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
