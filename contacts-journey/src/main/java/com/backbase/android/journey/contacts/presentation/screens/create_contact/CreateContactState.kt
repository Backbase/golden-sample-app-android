package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.annotation.StringRes

data class CreateContactState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSaved: Boolean = false,

    val name: FieldValue<String> = FieldValue(""),
    val accountNumber: FieldValue<String> = FieldValue(""),
    val email: FieldValue<String> = FieldValue("")
){
    fun isValid() =
        name.fieldStatus is FieldStatus.Valid &&
                accountNumber.fieldStatus is FieldStatus.Valid &&
                email.fieldStatus is FieldStatus.Valid
}

data class FieldValue<T>(
    val value: T,
    val fieldStatus: FieldStatus = FieldStatus.Init
)

sealed class FieldStatus{
    object Init: FieldStatus()
    object Valid: FieldStatus()
    data class Invalid(@StringRes val errorMessage: Int? = null): FieldStatus()
}