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

/**
 * An implementation of [AccountsUseCase] based on [ProductSummaryApi].
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
class AccountSummaryUseCaseImpl constructor(
    private val productSummaryApi: ProductSummaryApi,
    private val dispatchers: DispatcherProvider,
) : AccountsUseCase {

    private var cache: AccountSummary? = null

    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        if (useCache && cache != null) {
            return Result.Success(cache!!)
        }

        val callResult = withContext(dispatchers.io()) {
            productSummaryApi
                .getProductSummary(ProductSummaryApiParams.GetProductSummary { })
                .parseExecute()
        }

        return when (callResult) {
            is CallResult.Success -> {
                val dataModel = callResult.data
                val domainModel = dataModel.mapToDomain()
                cache = domainModel
                Result.Success(domainModel)
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
