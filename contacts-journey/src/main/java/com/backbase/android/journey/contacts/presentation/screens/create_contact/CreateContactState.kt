package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import com.backbase.android.journey.contacts.presentation.util.FieldValue

data class CreateContactState<StateExtension>(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSaved: Boolean = false,

    val name: FieldValue<String> = FieldValue(""),
    val accountNumber: FieldValue<String> = FieldValue(""),
    val email: FieldValue<String> = FieldValue(""),

    val extension: StateExtension? = null
){
    fun isValid() =
        name.fieldStatus is FieldStatus.Valid &&
                accountNumber.fieldStatus is FieldStatus.Valid &&
                email.fieldStatus is FieldStatus.Valid //TODO move to a changeable spot
}

typealias DefaultCreateContactState = CreateContactState<Unit>