package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateEmail
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.EmailValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Valid

fun <S> updateEmailIntentHandler() = IntentHandler<UpdateEmail, CreateContactState<S>, CreateContactViewEffect> {
    updateUiState { currentState ->
        currentState.copy(email = currentState.email.copy(value = intent.value))
    }

    if (uiStateSnapshot.accountNumber.fieldStatus is FieldStatus.Init) return@IntentHandler
    val isValidEmail = EmailValidator.validateEmail(intent.value)

    when (isValidEmail) {
        true -> updateUiState { currentState ->
            currentState.copy(email = currentState.email.copy(fieldStatus = Valid))
        }
        false -> updateUiState { currentState ->
            currentState.copy(email = currentState.email.copy(fieldStatus = Invalid(R.string.contacts_create_field_email_empty_error)))
        }
    }
}
