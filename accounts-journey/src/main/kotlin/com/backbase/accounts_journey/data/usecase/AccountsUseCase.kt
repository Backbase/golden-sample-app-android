package com.backbase.accounts_journey.data.usecase

import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary

/**
 * The use cases for the accounts/products.
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
interface AccountsUseCase {

    /**
     * Method that fetches a list of accounts.
     *
     * @return an instance of [ProductSummary]
     */
    suspend fun getAccountSummary(useCache: Boolean = true): Result<ProductSummary>
}
