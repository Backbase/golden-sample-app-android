package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.EmailValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.flow

fun <S> updateEmailHandler() = IntentHandler<CreateContactIntent.UpdateEmail, CreateContactState<S>> { intent ->
    flow {
        val uiState = uiState.value
        emit(uiState.copy(name = uiState.email.copy(value = intent.value)))

        val updatedUiState = if (EmailValidator.validateEmail(intent.value)) {
            uiState.copy(email = uiState.email.copy(fieldStatus = FieldStatus.Valid))
        } else {
            uiState.copy(email = uiState.email.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_email_empty_error)))
        }

        emit(updatedUiState)
    }
}