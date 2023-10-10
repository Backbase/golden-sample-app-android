package com.backbase.accounts_journey.presentation.ui

/**
 * The possible events for the AccountList screen.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
sealed class AccountListEvent {
    object OnGetAccounts : AccountListEvent()
    object OnRefresh : AccountListEvent()
    data class OnSearch(val query: String) : AccountListEvent()
}
