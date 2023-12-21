package com.backbase.accounts_journey.presentation.accountdetail.ui

/**
 * The possible events for the AccountDetail screen.
 *
 * Created by Backbase R&D B.V on 16/11/2023.
 */
sealed interface AccountDetailEvent {
    data class OnGetAccountDetail(val id: String) : AccountDetailEvent
}
