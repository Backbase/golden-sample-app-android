package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showAccountNumberUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showFieldValidationUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.BankAccountValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Valid
import kotlinx.coroutines.flow.flow

fun <S> updateAccountNumberIntentHandler() = IntentHandler<UpdateAccountNumber, CreateContactState<S>> { intent, uiState ->
        flow {
            emit(value = showAccountNumberUpdated(accountNumber = intent.value))

            if (uiState.accountNumber.fieldStatus is FieldStatus.Init) return@flow

            val isValidBankAccount = BankAccountValidator.validateBankAccount(uiState.accountNumber.value)
            if (isValidBankAccount) emit(value = showFieldValidationUpdated(status = Valid))
            else emit(value = showFieldValidationUpdated(status = Invalid(R.string.contacts_account_create_field_wrong_format)))
        }
    }
