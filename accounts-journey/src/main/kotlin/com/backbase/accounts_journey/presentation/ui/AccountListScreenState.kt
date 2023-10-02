package com.backbase.accounts_journey.presentation.ui

import androidx.annotation.StringRes
import com.backbase.accounts_journey.presentation.model.ListItem

data class AccountListScreenState(
    val isLoading: Boolean = false,
    val accountSummary: List<ListItem> = emptyList(),
    @StringRes val error: Int? = null
)
