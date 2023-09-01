package com.backbase.accounts_journey.data.model

import com.backbase.accounts_journey.data.usecase.AccountsUseCase
import com.backbase.accounts_journey.domain.model.account.Account
import dev.drewhamilton.poko.Poko

@Poko
class AccountSummaryResponse private constructor(
    @Deprecated(
        message = "AccountSummaryResponse.accountList is only used by the journey if AccountSummaryResponse.productSummary is null. " +
                "If AccountSummaryResponse.productSummary is not null, then whatever value is in AccountSummaryResponse.accountList is ignored."
    )
    val accountList: List<Account>,
    val result: AccountsUseCase.AccountsTransactionsResult,
//    val productSummary: ProductSummary?,
) {
}