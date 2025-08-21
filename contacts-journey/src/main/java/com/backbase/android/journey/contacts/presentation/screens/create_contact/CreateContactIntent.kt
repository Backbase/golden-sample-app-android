package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.NameValidationResult

interface CreateContactIntent {

    data class UpdateName(val accountName: String) : CreateContactIntent
    data class NameValidationResultIntent(val result: NameValidationResult): CreateContactIntent

    data class UpdateAccountNumber(val accountNumber: String) : CreateContactIntent
    data class AccountNumberValidationResult(val isValid: Boolean) : CreateContactIntent

    data class UpdateEmail(val email: String) : CreateContactIntent
    data class EmailValidationResult(val isValid: Boolean) : CreateContactIntent

    object Submit : CreateContactIntent
    object Submitted : CreateContactIntent
    class SaveContactError(val cause: Throwable): CreateContactIntent
}