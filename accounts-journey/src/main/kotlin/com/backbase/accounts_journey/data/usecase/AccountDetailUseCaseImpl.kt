package com.backbase.accounts_journey.data.usecase

import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.common.NoInternetException
import com.backbase.accounts_journey.common.NoResponseException
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApiParams
import com.backbase.android.client.gen2.arrangementclient2.model.AccountArrangementItem
import com.backbase.android.clients.common.CallResult
import com.backbase.android.core.errorhandling.ErrorCodes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * An implementation of [AccountDetailUseCase] based on [ArrangementsApi].
 *
 * Created by Backbase R&D B.V on 16/11/2023.
 */
class AccountDetailUseCaseImpl(
    private val arrangementsApi: ArrangementsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AccountDetailUseCase {

    override suspend fun getAccountDetail(params: AccountDetailUseCase.Params): Result<AccountArrangementItem> {
        val callResult = withContext(dispatcher) {
            arrangementsApi.getArrangementById(
                ArrangementsApiParams.GetArrangementById {
                    arrangementId = params.id
                }
            )
        }.parseExecute()

        return when (callResult) {
            is CallResult.Success -> {
                Result.success(callResult.data)
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
