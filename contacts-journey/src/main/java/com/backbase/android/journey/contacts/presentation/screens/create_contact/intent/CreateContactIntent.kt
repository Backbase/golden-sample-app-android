package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent

import com.backbase.android.foundation.mvi.Intent

sealed class CreateContactIntent : Intent {
    data class UpdateName(val value: String) : CreateContactIntent()
    data class UpdateAccountNumber(val value: String) : CreateContactIntent()
    data class UpdateEmail(val value: String) : CreateContactIntent()
    object Submit : CreateContactIntent()
}