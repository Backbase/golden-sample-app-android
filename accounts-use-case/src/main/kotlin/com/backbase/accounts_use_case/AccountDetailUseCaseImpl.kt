package com.backbase.accounts_use_case

import com.backbase.accounts_journey.common.ConnectionException
import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.data.usecase.AccountDetailUseCase
import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail
import com.backbase.accounts_use_case.mapper.mapToDomain
import com.backbase.accounts_use_case.service.ArrangementManagerService
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.network.common.isConnectionError

/**
 * An implementation of [AccountDetailUseCase] based on [ArrangementsApi].
 *
 * Created by Backbase R&D B.V on 16/11/2023.
 */
class AccountDetailUseCaseImpl(
    private val arrangementManagerService: ArrangementManagerService,
) : AccountDetailUseCase {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun getAccountDetail(params: AccountDetailUseCase.Params): Result<AccountDetail> {
        return try {
            val dataModel = arrangementManagerService.getAccountDetail(params)
            val domainModel = dataModel.mapToDomain()
            Result.success(domainModel)
        } catch (e: Exception) {
            when {
                e.isConnectionError() -> Result.failure(ConnectionException(e.message))
                else -> Result.failure(FailedGetDataException(e.message))
            }
        }
    }
}
