package com.backbase.accounts_journey.presentation.ui

sealed class AccountListEvent {
    object OnGetAccounts : AccountListEvent()
    data class OnSearch(val query: String) : AccountListEvent()
}
