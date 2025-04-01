package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.AccountNameValidator
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.NameValidationResult
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.MutableStateFlow

class UpdateNameHandler<StateExtension>(
    private val stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
) {

    operator fun invoke(
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