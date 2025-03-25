package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

sealed class CreateContactAccountIntent {
    class ChangeAccountNumber(val accountNumber: String): CreateContactAccountIntent()
    class ChangeAccountName(val accountName: String): CreateContactAccountIntent()
    object SaveAccount: CreateContactAccountIntent()
}