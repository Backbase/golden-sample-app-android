package com.backbase.presentation.ui

import androidx.annotation.StringRes
import com.backbase.presentation.model.ListItem

/**
 * The UI state for the account list screen.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
data class AccountListScreenState(
    val isLoading: Boolean = false,
    val accountSummary: List<ListItem> = emptyList(),
    @StringRes val error: Int? = null
)
