package com.backbase.accounts_journey.presentation.accountdetail

import androidx.annotation.StringRes
import com.backbase.accounts_journey.presentation.model.AccountDetailUiModel
import com.backbase.accounts_journey.presentation.model.ListItem

/**
 * The UI state for the account list screen.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
data class AccountDetailScreenState(
    val isLoading: Boolean = false,
    val accountDetail: AccountDetailUiModel? = null,
    @StringRes val error: Int? = null
)
