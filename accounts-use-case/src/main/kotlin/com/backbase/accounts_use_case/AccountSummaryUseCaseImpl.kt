package com.backbase.accounts_use_case

import com.backbase.accounts_journey.common.ConnectionException
import com.backbase.accounts_journey.common.FailedGetDataException
import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.domain.model.account_summary.AccountSummary
import com.backbase.accounts_use_case.mapper.mapToDomain
import com.backbase.accounts_use_case.service.ArrangementManagerService
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.network.common.isConnectionError

/**
 * An implementation of [AccountsUseCase] based on [ProductSummaryApi].
 *
 * Created by Backbase R&D B.V on 19/09/2023.
 */
class AccountSummaryUseCaseImpl(
    private val arrangementManagerService: ArrangementManagerService,
) : AccountsUseCase {

    private var cache: AccountSummary? = null

    @Suppress("TooGenericExceptionCaught")
    override suspend fun getAccountSummary(useCache: Boolean): Result<AccountSummary> {
        if (useCache && cache != null) {
            return Result.success(cache!!)
        }

        return try {
            val dataModel = arrangementManagerService.getProductSummary()
            val domainModel = dataModel.mapToDomain()
            cache = domainModel
            Result.success(domainModel)
        } catch (e: Exception) {
            when {
                e.isConnectionError() -> Result.failure(ConnectionException(e.message))
                else -> Result.failure(FailedGetDataException(e.message))
            }
        }
    }
}
