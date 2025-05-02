package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.AccountNameValidator
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.NameValidationResult
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.flow

fun <S> updateNameHandler() = IntentHandler<CreateContactIntent.UpdateName, CreateContactState<S>> { intent ->
    flow {
        val uiState = uiState.value
        emit(uiState.copy(name = uiState.name.copy(value = intent.value)))

        if (uiState.name.fieldStatus is FieldStatus.Init) return@flow

        val validationResult = AccountNameValidator.validate(uiState.name.value)
        val updatedUiState = when (validationResult) {
            is NameValidationResult.Valid -> uiState
                .copy(name = uiState.name.copy(fieldStatus = FieldStatus.Valid))
            is NameValidationResult.Empty -> uiState
                .copy(name = uiState.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_empty_error)))
            is NameValidationResult.IllegalCharacters -> uiState
                .copy(name = uiState.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_illegal_characters)))
        }

        emit(updatedUiState)
    }
}
