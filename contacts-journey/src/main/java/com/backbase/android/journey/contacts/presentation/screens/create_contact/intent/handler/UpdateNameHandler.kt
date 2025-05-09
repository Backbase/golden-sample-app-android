package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.uiStateSnapshot
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent.UpdateName
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showFieldValidationUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showNameUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.AccountNameValidator
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.NameValidationResult
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Valid
import kotlinx.coroutines.flow.flow

fun <S> updateNameIntentHandler() = IntentHandler<UpdateName, CreateContactState<S>, CreateContactViewEffect> { intent, emitState, _ ->
    emitState(showNameUpdated(name = intent.value))

    if (uiStateSnapshot.name.fieldStatus is FieldStatus.Init) return@IntentHandler
    if (uiStateSnapshot.accountNumber.fieldStatus is FieldStatus.Init) return@IntentHandler

    val validateNameResult = AccountNameValidator.validate(uiStateSnapshot.name.value)
    when (validateNameResult) {
        is NameValidationResult.Valid -> emitState(showFieldValidationUpdated(status = Valid))
        is NameValidationResult.Empty -> emitState(showFieldValidationUpdated(status = Invalid(R.string.contacts_create_field_name_empty_error)))
        NameValidationResult.IllegalCharacters -> emitState(showFieldValidationUpdated(status = Invalid(R.string.contacts_create_field_name_illegal_characters)))
    }
}
