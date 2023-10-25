package com.backbase.accounts_use_case

import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.common.NoInternetException
import com.backbase.accounts_journey.common.NoResponseException
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_use_case.mapper.mapToDomain
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApiParams
import com.backbase.android.clients.common.CallResult
import com.backbase.android.core.errorhandling.ErrorCodes
import com.backbase.domain.model.product_summary.AccountSummary
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * An implementation of [AccountsUseCase] based on [ProductSummaryApi].
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
class AccountSummaryUseCaseImpl constructor(
    private val productSummaryApi: ProductSummaryApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AccountsUseCase {

    private var cache: AccountSummary? = null

    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        if (useCache && cache != null) {
            return Result.success(cache!!)
        }

        val callResult = withContext(ioDispatcher) {
            productSummaryApi
                .getProductSummary(ProductSummaryApiParams.GetProductSummary { })
                .parseExecute()
        }

        return when (callResult) {
            is CallResult.Success -> {
                val dataModel = callResult.data
                val domainModel = dataModel.mapToDomain()
                cache = domainModel
                Result.success(domainModel)
            }

            is CallResult.Error -> {
                val errorResponse = callResult.errorResponse
                when (errorResponse.responseCode) {
                    ErrorCodes.NO_INTERNET.code -> Result.failure(NoInternetException(errorResponse.errorMessage))
                    else -> Result.failure(FailedGetDataException(errorResponse.errorMessage))
                }
            }

            is CallResult.None -> Result.failure(NoResponseException())
        }
    }
}
