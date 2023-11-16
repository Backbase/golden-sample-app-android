package com.backbase.accounts_journey.presentation.accountdetail.ui

sealed interface AccountDetailEvent {
    data class OnGetAccountDetail(val id: String) : AccountDetailEvent
}
