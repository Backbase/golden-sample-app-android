package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.IntentScope.Companion.uiStateSnapshot
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateName
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.AccountNameValidator
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.NameValidationResult
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Valid

fun <S> updateNameIntentHandler() = IntentHandler<UpdateName, CreateContactState<S>, CreateContactViewEffect> {
    updateUiState { currentState -> currentState.copy(name = currentState.name.copy(value = intent.value)) }

    val validateNameResult = AccountNameValidator.validate(uiStateSnapshot.name.value)
    when (validateNameResult) {
        is NameValidationResult.Valid -> updateUiState { currentState ->
            currentState.copy(accountNumber = currentState.accountNumber.copy(fieldStatus = Valid))
        }
        is NameValidationResult.Empty -> updateUiState { currentState ->
            currentState.copy(accountNumber = currentState.accountNumber.copy(fieldStatus = Invalid(R.string.contacts_create_field_name_empty_error)))
        }
        NameValidationResult.IllegalCharacters -> updateUiState { currentState ->
            currentState.copy(accountNumber = currentState.accountNumber.copy(fieldStatus = Invalid(R.string.contacts_create_field_name_illegal_characters)))
        }
    }
}
