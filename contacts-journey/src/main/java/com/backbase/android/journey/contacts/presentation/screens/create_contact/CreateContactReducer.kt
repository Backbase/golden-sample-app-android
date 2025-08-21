package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.foundation.mvi.Reducer
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.AccountNumberValidationResult
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.EmailValidationResult
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.NameValidationResultIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.SaveContactError
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.Submit
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.Submitted
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateEmail
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateName
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactSideEffect.RunBankAccountValidation
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactSideEffect.RunEmailValidation
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactSideEffect.RunNameValidation
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.NameValidationResult
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Valid

class CreateContactReducer<StateExtension> : Reducer<CreateContactIntent, CreateContactState<StateExtension>, CreateContactSideEffect> {

    override fun CreateContactState<StateExtension>.reduce(intent: CreateContactIntent, throwSideEffect: (CreateContactSideEffect) -> Unit) = when (intent) {
        is UpdateAccountNumber -> copy(accountNumber = accountNumber.copy(value = intent.accountNumber))
            .also { throwSideEffect(RunBankAccountValidation(intent.accountNumber)) }

        is AccountNumberValidationResult -> when (intent.isValid) {
            true -> copy(accountNumber = accountNumber.copy(fieldStatus = Valid))
            false -> copy(accountNumber = accountNumber.copy(fieldStatus = Invalid(R.string.contacts_account_create_field_wrong_format)))
        }

        is UpdateEmail -> copy(email = email.copy(value = intent.email))
            .also { throwSideEffect(RunEmailValidation(intent.email)) }

        is EmailValidationResult -> when (intent.isValid) {
            true -> copy(accountNumber = email.copy(fieldStatus = Valid))
            false -> copy(accountNumber = email.copy(fieldStatus = Invalid(R.string.contacts_account_create_field_wrong_format)))
        }

        is UpdateName -> copy(name = name.copy(value = intent.accountName))
            .also { throwSideEffect(RunNameValidation(intent.accountName)) }

        is NameValidationResultIntent -> when (intent.result) {
            is NameValidationResult.Valid -> copy(accountNumber = accountNumber.copy(fieldStatus = Valid))
            is NameValidationResult.Empty -> copy(accountNumber = accountNumber.copy(fieldStatus = Invalid(R.string.contacts_create_field_name_empty_error)))
            NameValidationResult.IllegalCharacters -> copy(accountNumber = accountNumber.copy(fieldStatus = Invalid(R.string.contacts_create_field_name_illegal_characters)))
        }

        is Submit -> copy(isLoading = true)
        is Submitted -> copy(isLoading = false, isSaved = true)
        is SaveContactError -> copy(isLoading = false, error = intent.cause.message)
        else -> error("")
    }
}
