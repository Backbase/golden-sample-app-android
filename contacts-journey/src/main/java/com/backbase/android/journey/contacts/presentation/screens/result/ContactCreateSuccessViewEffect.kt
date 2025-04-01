package com.backbase.android.journey.contacts.presentation.screens.result

sealed class ContactCreateSuccessViewEffect {
    object NavigateToContacts : ContactCreateSuccessViewEffect()
    object NavigateToNewContact : ContactCreateSuccessViewEffect()
}