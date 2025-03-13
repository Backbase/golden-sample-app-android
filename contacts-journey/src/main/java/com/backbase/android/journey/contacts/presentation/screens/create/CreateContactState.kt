package com.backbase.android.journey.contacts.presentation.screens.create

import androidx.annotation.StringRes

data class CreateContactState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSaved: Boolean = false,

    val name: FieldValue<String> = FieldValue(""),
    val accountNumber: FieldValue<String> = FieldValue(""),
    val email: FieldValue<String> = FieldValue("")
)

data class FieldValue<T>(
    val value: T,
    val fieldStatus: FieldStatus = FieldStatus.Init
)

sealed class FieldStatus{
    object Init: FieldStatus()
    object Valid: FieldStatus()
    data class Invalid(@StringRes val errorMessage: Int? = null): FieldStatus()
}