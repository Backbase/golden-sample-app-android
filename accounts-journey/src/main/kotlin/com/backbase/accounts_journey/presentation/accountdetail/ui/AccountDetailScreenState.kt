package com.backbase.accounts_journey.presentation.accountdetail.ui

import androidx.annotation.StringRes
import com.backbase.accounts_journey.presentation.accountdetail.model.AccountDetailUiModel

/**
 * The UI state for the account detail screen.
 *
 * Created by Backbase R&D B.V on 16/11/2023.
 */
data class AccountDetailScreenState(
    val isLoading: Boolean = false,
    val accountDetail: AccountDetailUiModel? = null,
    @StringRes val error: Int? = null
)
