package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

sealed class CreateContactAccountIntent {
    class UpdateAccountNumber(val accountNumber: String): CreateContactAccountIntent()
    class UpdateAccountName(val accountName: String): CreateContactAccountIntent()
    object Submit: CreateContactAccountIntent()
}