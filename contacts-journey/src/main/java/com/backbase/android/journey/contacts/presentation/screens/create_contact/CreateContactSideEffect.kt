package com.backbase.android.journey.contacts.presentation.screens.create_contact

interface CreateContactSideEffect {

    class RunBankAccountValidation(val accountNumber: String): CreateContactSideEffect
    class RunEmailValidation(val email: String): CreateContactSideEffect
    class RunNameValidation(val name: String): CreateContactSideEffect

    object SaveContact: CreateContactSideEffect
    object ToContactCreatedResult: CreateContactSideEffect
}