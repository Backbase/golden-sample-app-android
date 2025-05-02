package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.intentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.AccountNameValidator
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.NameValidationResult
import com.backbase.android.journey.contacts.presentation.util.FieldStatus

fun <StateExtension> updateNameHandler() = intentHandler<CreateContactIntent.UpdateName, CreateContactState<StateExtension>> { intent, onUiStateUpdated ->
    val state = this.uiState.value
    onUiStateUpdated(state.copy(name = state.name.copy(value = intent.value)))

    if(state.name.fieldStatus is FieldStatus.Init) return@intentHandler

    val validationResult = AccountNameValidator.validate(state.name.value)
    val newValue = when(validationResult) {
        is NameValidationResult.Valid -> state.copy(name = state.name.copy(fieldStatus = FieldStatus.Valid))
        is NameValidationResult.Empty -> state.copy(name = state.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_empty_error)))
        is NameValidationResult.IllegalCharacters -> state.copy(name = state.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_illegal_characters)))
    }

    onUiStateUpdated(newValue)
}
