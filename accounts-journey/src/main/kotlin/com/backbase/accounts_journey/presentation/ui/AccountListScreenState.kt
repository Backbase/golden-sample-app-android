package com.backbase.accounts_journey.presentation.ui

import androidx.annotation.StringRes
import com.backbase.accounts_journey.presentation.model.AccountSummaryUiModel

data class AccountListScreenState(
    val isLoading: Boolean = false,
    val accountSummary: AccountSummaryUiModel? = null,
    @StringRes val error: Int? = null
)