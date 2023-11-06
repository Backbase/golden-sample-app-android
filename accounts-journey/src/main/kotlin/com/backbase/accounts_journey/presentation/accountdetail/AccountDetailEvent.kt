package com.backbase.accounts_journey.presentation.accountdetail

sealed interface AccountDetailEvent {
    data class OnGetAccountDetail(val id: String) : AccountDetailEvent
}
