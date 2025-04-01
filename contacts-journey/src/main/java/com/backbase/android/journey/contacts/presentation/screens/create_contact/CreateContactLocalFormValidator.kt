package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.util.FieldStatus

interface CreateContactLocalFormValidator<StateExtension> {
    suspend operator fun invoke(
        formState: CreateContactState<StateExtension>
    ): CreateContactState<StateExtension>
}

class CreateContactLocalFormValidatorImpl<StateExtension>(
) : CreateContactLocalFormValidator<StateExtension> {
    override suspend operator fun invoke(
        currentState: CreateContactState<StateExtension>
    ): CreateContactState<StateExtension> {
        return nameValidation(currentState)
    }

    fun nameValidation(currentState: CreateContactState<StateExtension>): CreateContactState<StateExtension> {
        return if(currentState.name.fieldStatus is FieldStatus.Init) {
            currentState //Does not validate when on Init
        } else if (currentState.name.value.isEmpty()){
            currentState.copy(name = currentState.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_empty_error)))
        } else if (currentState.name.value.contains(regex = Regex("[0-9]"))){
            currentState.copy(name = currentState.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_illegal_characters)))
        } else {
            currentState.copy(name = currentState.name.copy(fieldStatus = FieldStatus.Valid))
        }
    }
}