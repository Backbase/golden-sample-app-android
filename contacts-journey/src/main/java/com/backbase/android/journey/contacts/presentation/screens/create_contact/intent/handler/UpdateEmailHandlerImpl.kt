package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.EmailValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.MutableStateFlow

interface UpdateEmailHandler<StateExtension>{
    operator fun invoke(
        intent: CreateContactIntent.UpdateEmail
    )
}

class UpdateEmailHandlerImpl<StateExtension>(
    private val stateFlow: MutableStateFlow<CreateContactState<StateExtension>>
): UpdateEmailHandler<StateExtension> {

    override operator fun invoke(
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