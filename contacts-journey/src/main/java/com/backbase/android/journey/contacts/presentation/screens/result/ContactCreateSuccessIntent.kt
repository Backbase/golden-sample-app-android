package com.backbase.android.journey.contacts.presentation.screens.result

sealed class ContactCreateSuccessIntent {
    object BackToContacts : ContactCreateSuccessIntent()
    object CreateNewContact : ContactCreateSuccessIntent()
}