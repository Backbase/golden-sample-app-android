package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.AccountNameValidator
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.NameValidationResult
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Interface for handling the [CreateContactIntent.UpdateName] intent.
 *
 * This handler processes updates to the name field during the contact creation flow.
 *
 * @param StateExtension A generic parameter used to extend the state if needed.
 */
interface UpdateNameHandler<StateExtension>{
    operator fun invoke(
        intent: CreateContactIntent.UpdateName
    )
}

/**
 * Implementation of [UpdateNameHandler] that updates the name field in the [CreateContactState]
 * and performs validation based on the current field status.
 *
 * - Updates the state with the new name value from the intent.
 * - If the field status is [FieldStatus.Init], skips validation.
 * - Otherwise, validates the name using [AccountNameValidator].
 * - Sets [FieldStatus.Valid] or [FieldStatus.Invalid] with the appropriate error resource ID
 *   based on the validation result.
 *
 * @param StateExtension A generic parameter used to extend the state if needed.
 * @property stateFlow The [MutableStateFlow] holding the current [CreateContactState].
 */
class UpdateNameHandlerImpl<StateExtension>(
    private val stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
): UpdateNameHandler<StateExtension> {

    override operator fun invoke(
        intent: CreateContactIntent.UpdateName
    ) {
        stateFlow.value = stateFlow.value.copy(
            name = stateFlow.value.name.copy(value = intent.value)
        )

        if(stateFlow.value.name.fieldStatus is FieldStatus.Init) {
            return //Does not validate when on Init
        } else {
            val validationResult = AccountNameValidator.validate(stateFlow.value.name.value)
            when(validationResult){
                is NameValidationResult.Valid -> stateFlow.value = stateFlow.value.copy(name = stateFlow.value.name.copy(fieldStatus = FieldStatus.Valid))
                is NameValidationResult.Empty -> stateFlow.value = stateFlow.value.copy(name = stateFlow.value.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_empty_error)))
                is NameValidationResult.IllegalCharacters -> stateFlow.value = stateFlow.value.copy(name = stateFlow.value.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_illegal_characters)))
            }
        }
    }

}