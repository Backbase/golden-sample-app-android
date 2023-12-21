package com.backbase.accounts_journey.presentation.accountlist.model

/**
 * The UI model for Accounts. It holds the header and accounts.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
data class AccountsUiModel(
    val header: AccountHeaderUiModel,
    val products: List<AccountUiModel>,
)
