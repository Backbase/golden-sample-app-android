package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.EmailValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.MutableStateFlow

class UpdateEmailHandler<StateExtension>(
    private val stateFlow: MutableStateFlow<CreateContactState<StateExtension>>
) {

    operator fun invoke(
        intent: CreateContactIntent.UpdateEmail
    ) {
        stateFlow.value = stateFlow.value.copy(
            email = stateFlow.value.email.copy(value = intent.value)
        )

        if(EmailValidator.validateEmail(intent.value)){
            stateFlow.value = stateFlow.value.copy(email = stateFlow.value.email.copy(fieldStatus = FieldStatus.Valid))
        } else {
            stateFlow.value = stateFlow.value.copy(email = stateFlow.value.email.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_email_empty_error)))
        }

    }
}