package com.backbase.accounts_journey.presentation.model

data class AccountSummaryUiModel(
    val customProducts: List<AccountsUiModel>,
    val currentAccounts: AccountsUiModel?,
    val savingAccounts: AccountsUiModel?,
    val termDeposits: AccountsUiModel?,
    val loans: AccountsUiModel?,
    val creditCards: AccountsUiModel?,
    val debitCards: AccountsUiModel?,
    val investmentAccounts: AccountsUiModel?,
)
