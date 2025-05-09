package com.backbase.android.journey.contacts.presentation.screens.create_contact

interface CreateContactIntent {
    data class UpdateName(val value: String) : CreateContactIntent
    data class UpdateAccountNumber(val value: String) : CreateContactIntent
    data class UpdateEmail(val value: String) : CreateContactIntent
    object Submit : CreateContactIntent
}