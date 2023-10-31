package com.backbase.accounts_journey.data.usecase

import com.backbase.accounts_journey.domain.model.account_detail.AccountDetail

interface AccountDetailUseCase {
    suspend fun getAccountDetail(id: String): Result<AccountDetail>
}
