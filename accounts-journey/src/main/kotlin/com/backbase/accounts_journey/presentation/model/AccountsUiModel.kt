package com.backbase.accounts_journey.presentation.model

data class AccountsUiModel(
    val header: AccountHeaderUiModel,
    val products: List<AccountUiModel>,
)
