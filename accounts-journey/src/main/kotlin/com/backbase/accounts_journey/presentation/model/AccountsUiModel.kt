package com.backbase.accounts_journey.presentation.model

data class AccountsUiModel(
    val name: String,
    val id: Int? = null,
    val products: List<AccountUiModel>,
)