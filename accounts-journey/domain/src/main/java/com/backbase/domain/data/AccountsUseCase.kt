package com.backbase.domain.data

import com.backbase.domain.model.product_summary.AccountSummary

/**
 * The use cases for the accounts/products.
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
interface AccountsUseCase {

    /**
     * Method that fetches a list of accounts.
     *
     * @return an instance of [AccountSummary]
     */
    suspend fun getAccountSummary(useCache: Boolean = true): Result<AccountSummary>
}
