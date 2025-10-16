package com.backbase.accounts_journey.data.usecase

import com.backbase.android.client.gen2.arrangementclient2.model.AccountArrangementItem
import dev.drewhamilton.poko.Poko

interface AccountDetailUseCase {
    suspend fun getAccountDetail(params: Params): Result<AccountArrangementItem>

    @Poko
    class Params private constructor(val id: String) {

        class Builder {
            lateinit var id: String

            fun build() = Params(
                id = id
            )
        }
    }
}

fun Params(
    initializer: AccountDetailUseCase.Params.Builder.() -> Unit
): AccountDetailUseCase.Params = AccountDetailUseCase.Params.Builder().apply(initializer).build()
