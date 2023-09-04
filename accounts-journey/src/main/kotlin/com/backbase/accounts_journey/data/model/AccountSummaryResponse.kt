package com.backbase.accounts_journey.data.model

import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.domain.model.account.AccountSummary
import dev.drewhamilton.poko.Poko

@Poko
class AccountSummaryResponse private constructor(
    val result: AccountsUseCase.AccountsTransactionsResult,
    val accountSummary: AccountSummary?,
) {
    class Builder {
        @set:JvmSynthetic
        var result: AccountsUseCase.AccountsTransactionsResult? = null

        @set:JvmSynthetic
        var accountSummary: AccountSummary? = null

        fun build() = AccountSummaryResponse(
            result = checkNotNull(result),
            accountSummary = accountSummary
        )
    }
}

@JvmSynthetic
fun AccountSummaryResponse(block: AccountSummaryResponse.Builder.() -> Unit) =
    AccountSummaryResponse.Builder()
        .apply(block).build()
