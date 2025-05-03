package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent.UpdateName
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showFieldValidationUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showNameUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.AccountNameValidator
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.NameValidationResult
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Valid
import kotlinx.coroutines.flow.flow

fun <S> updateNameIntentHandler() = IntentHandler<UpdateName, CreateContactState<S>> { intent, uiState ->
    flow {
        emit(value = showNameUpdated(name = intent.value))

        if (uiState.name.fieldStatus is FieldStatus.Init) return@flow
        if (uiState.accountNumber.fieldStatus is FieldStatus.Init) return@flow

        val validateNameResult = AccountNameValidator.validate(uiState.name.value)
        when (validateNameResult) {
            is NameValidationResult.Valid -> emit(value = showFieldValidationUpdated(status = Valid))
            is NameValidationResult.Empty -> emit(value = showFieldValidationUpdated(status = Invalid(R.string.contacts_create_field_name_empty_error)))
            NameValidationResult.IllegalCharacters -> emit(value = showFieldValidationUpdated(status = Invalid(R.string.contacts_create_field_name_illegal_characters)))
        }
    }
}
