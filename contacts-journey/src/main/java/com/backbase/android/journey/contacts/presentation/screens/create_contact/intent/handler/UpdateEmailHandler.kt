package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.EmailValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Interface for handling the [CreateContactIntent.UpdateEmail] intent.
 *
 * This handler processes updates to the email field during the contact creation flow.
 *
 * @param StateExtension A generic parameter used to extend the state on project if needed.
 */
interface UpdateEmailHandler<StateExtension>{
    operator fun invoke(
        intent: CreateContactIntent.UpdateEmail
    )
}

/**
 * Implementation of [UpdateEmailHandler] that updates the email field in the [CreateContactState]
 * and performs validation on the entered email.
 *
 * - Updates the state with the new email value from the intent.
 * - Validates the email using [EmailValidator].
 * - Sets [FieldStatus.Valid] or [FieldStatus.Invalid] with an error message resource ID.
 *
 * @param StateExtension A generic parameter used to extend the state on project if needed.
 * @property stateFlow The [MutableStateFlow] holding the current [CreateContactState].
 */
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