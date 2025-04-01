package com.backbase.android.journey.contacts.presentation.screens.detail

sealed interface ContactDetailsIntent {
    data class LoadContact(val id: String) : ContactDetailsIntent
}