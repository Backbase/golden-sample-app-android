package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.intentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.BankAccountValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus

fun <StateExtension> updateAccountNumberHandler() = intentHandler<CreateContactIntent.UpdateEmail, CreateContactState<StateExtension>> { intent, onUiStateUpdated ->
    val state = this.uiState.value
    onUiStateUpdated(state.copy(name = state.accountNumber.copy(value = intent.value)))

    if (state.accountNumber.fieldStatus is FieldStatus.Init) return@intentHandler

    val validationResult = BankAccountValidator.validateBankAccount(state.accountNumber.value)
    val newValue = when {
        validationResult -> state.copy(accountNumber = state.accountNumber.copy(fieldStatus = FieldStatus.Valid))
        else -> state.copy(accountNumber = state.accountNumber.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_account_create_field_wrong_format)))
    }

    onUiStateUpdated(newValue)
}
