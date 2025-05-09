package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.uiStateSnapshot
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent.UpdateEmail
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showEmailUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showFieldValidationUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.EmailValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Valid

fun <S> updateEmailIntentHandler() = IntentHandler<UpdateEmail, CreateContactState<S>, CreateContactViewEffect> { intent, emitState, _ ->
    emitState(showEmailUpdated(email = intent.value))

    if (uiStateSnapshot.accountNumber.fieldStatus is FieldStatus.Init) return@IntentHandler

    val isValidEmail = EmailValidator.validateEmail(intent.value)
    if (isValidEmail) emitState(showFieldValidationUpdated(status = Valid))
    else emitState(showFieldValidationUpdated(status = Invalid(R.string.contacts_create_field_email_empty_error)))
}
