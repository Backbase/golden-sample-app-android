package com.backbase.accounts_use_case

import com.backbase.accounts_journey.common.DispatcherProvider
import com.backbase.accounts_journey.data.model.AccountSummaryResponse
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApiParams
import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary
import com.backbase.android.clients.common.CallResult
import com.backbase.android.clients.common.coroutines.executeAsSuspended
import kotlinx.coroutines.withContext

class AccountSummaryUseCaseImpl @JvmOverloads constructor(
    private val productSummaryApi: ProductSummaryApi,
    private val dispatcherProvider: DispatcherProvider,
) : AccountsUseCase {

    override suspend fun getAccounts(): AccountSummaryResponse {
        lateinit var callResult: CallResult<ProductSummary>
        withContext(dispatcherProvider.io()) {
            callResult = productSummaryApi.getProductSummary(
                ProductSummaryApiParams.GetProductSummary { }
            ).executeAsSuspended()
        }

        return when (callResult) {
            is CallResult.Success -> AccountSummaryResponse {
                result = AccountsUseCase.AccountsTransactionsResult.SUCCESS
                accountSummary = this.accountSummary
            }

            else -> AccountSummaryResponse {
                result = AccountsUseCase.AccountsTransactionsResult.FAILURE
            }
        }
    }
}
