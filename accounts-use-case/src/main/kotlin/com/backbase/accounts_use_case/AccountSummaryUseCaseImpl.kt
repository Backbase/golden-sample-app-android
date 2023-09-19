package com.backbase.accounts_use_case

import com.backbase.accounts_journey.common.DispatcherProvider
import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.common.NoInternetException
import com.backbase.accounts_journey.common.NoResponseException
import com.backbase.accounts_journey.common.Result
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.domain.model.product_summary.AccountSummary
import com.backbase.accounts_use_case.mapper.mapToDomain
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApiParams
import com.backbase.android.clients.common.CallResult
import com.backbase.android.core.errorhandling.ErrorCodes
import kotlinx.coroutines.withContext

class AccountSummaryUseCaseImpl constructor(
    private val productSummaryApi: ProductSummaryApi,
    private val dispatchers: DispatcherProvider,
) : AccountsUseCase {

    override suspend fun getAccounts(): Result<AccountSummary> {
        val callResult = withContext(dispatchers.io()) {
            productSummaryApi
                .getProductSummary(ProductSummaryApiParams.GetProductSummary { })
                .parseExecute()
        }

        return when (callResult) {
            is CallResult.Success -> {
                val dataModel = callResult.data
                val accountSummary = dataModel.mapToDomain()
                Result.Success(accountSummary)
            }

            is CallResult.Error -> {
                val errorResponse = callResult.errorResponse
                when (errorResponse.responseCode) {
                    ErrorCodes.NO_INTERNET.code -> Result.Error(NoInternetException(errorResponse.errorMessage))
                    else -> Result.Error(FailedGetDataException(errorResponse.errorMessage))
                }
            }

            is CallResult.None -> Result.Error(NoResponseException())
        }
    }
}
