package com.backbase.accounts_use_case

import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.common.NoInternetException
import com.backbase.accounts_journey.common.NoResponseException
import com.backbase.accounts_journey.data.usecase.AccountDetailUseCase
import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail
import com.backbase.accounts_use_case.mapper.mapToDomain
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApiParams
import com.backbase.android.clients.common.CallResult
import com.backbase.android.core.errorhandling.ErrorCodes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountDetailUseCaseImpl(
    private val arrangementsApi: ArrangementsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AccountDetailUseCase {

    override suspend fun getAccountDetail(params: AccountDetailUseCase.Params): Result<AccountDetail> {
        val callResult = withContext(dispatcher) {
            arrangementsApi.getArrangementById(
                ArrangementsApiParams.GetArrangementById {
                    arrangementId = params.id
                }
            )
        }.parseExecute()

        return when (callResult) {
            is CallResult.Success -> {
                val dataModel = callResult.data
                val domainModel = dataModel.mapToDomain()
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
