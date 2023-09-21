package com.backbase.accounts_journey.presentation.ui

sealed class AccountListEvent {
    object OnGetAccounts : AccountListEvent()
}