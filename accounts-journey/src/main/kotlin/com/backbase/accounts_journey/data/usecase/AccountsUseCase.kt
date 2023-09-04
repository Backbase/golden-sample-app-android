package com.backbase.accounts_journey.data.usecase

import com.backbase.accounts_journey.data.model.AccountSummaryResponse

/**
 * Created by Backbase R&D B.V. on 01/07/2020.
 *
 * The use cases for the accounts/products.
 */
interface AccountsUseCase {

    /**
     * Method that fetches a list of accounts.
     *
     * @return an instance of [AccountSummaryResponse]
     */
    suspend fun getAccounts(): AccountSummaryResponse

    /**
     * The status of the response of a request.
     */
    enum class AccountsTransactionsResult {
        FAILURE, SUCCESS
    }
}