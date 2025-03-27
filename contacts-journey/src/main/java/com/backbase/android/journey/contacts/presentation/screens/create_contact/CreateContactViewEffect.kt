package com.backbase.android.journey.contacts.presentation.screens.create_contact

sealed class CreateContactViewEffect {
    object ToContactCreateResult: CreateContactViewEffect()
}