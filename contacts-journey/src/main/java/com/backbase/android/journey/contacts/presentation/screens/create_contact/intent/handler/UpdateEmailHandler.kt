package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.intentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.EmailValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus

fun <StateExtension> updateEmailHandler() = intentHandler<CreateContactIntent.UpdateEmail, CreateContactState<StateExtension>> { intent, onUiStateUpdated ->
    val state = this.uiState.value
    onUiStateUpdated(state.copy(name = state.email.copy(value = intent.value)))

    val newValue = if (EmailValidator.validateEmail(intent.value)){
        state.copy(email = state.email.copy(fieldStatus = FieldStatus.Valid))
    } else {
        state.copy(email = state.email.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_email_empty_error)))
    }

    onUiStateUpdated(newValue)
}