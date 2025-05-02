package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.BankAccountValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.flow

fun <S> updateAccountNumberHandler() = IntentHandler<CreateContactIntent.UpdateEmail, CreateContactState<S>> { intent ->
        flow {
            val uiState = uiState.value
            emit(uiState.copy(name = uiState.accountNumber.copy(value = intent.value)))

            if (uiState.accountNumber.fieldStatus is FieldStatus.Init) return@flow

            val validationResult = BankAccountValidator.validateBankAccount(uiState.accountNumber.value)
            val updatedUiState = when {
                validationResult -> uiState
                    .copy(accountNumber = uiState.accountNumber.copy(fieldStatus = FieldStatus.Valid))
                else -> uiState
                    .copy(accountNumber = uiState.accountNumber.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_account_create_field_wrong_format)))
            }

            emit(updatedUiState)
        }
    }
