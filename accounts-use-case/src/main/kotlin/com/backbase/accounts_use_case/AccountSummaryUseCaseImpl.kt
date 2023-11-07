package com.backbase.accounts_use_case

import com.backbase.accounts_journey.common.ConnectionException
import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.domain.model.product_summary.AccountSummary
import com.backbase.accounts_use_case.mapper.mapToDomain
import com.backbase.accounts_use_case.service.ArrangementManagerService
import com.backbase.network.common.isConnectionError

class AccountSummaryUseCaseImpl(
    private val arrangementManagerService: ArrangementManagerService
) : AccountsUseCase {

    private var cache: AccountSummary? = null

    @Suppress("TooGenericExceptionCaught")
    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        if (useCache && cache != null) {
            return Result.success(cache!!)
        }

        try {
            val response = arrangementManagerService.productSummary()
            return if (response.isSuccessful) {
                val dataModel = checkNotNull(response.body())
                val domainModel = dataModel.mapToDomain()
                cache = domainModel
                Result.success(domainModel)
            } else {
                Result.failure(FailedGetDataException(response.message()))
            }
        } catch (e: Exception) {
            return when {
                e.isConnectionError() -> Result.failure(ConnectionException(e.message))
                else -> Result.failure(FailedGetDataException(e.message))
            }
        }
    }
}
