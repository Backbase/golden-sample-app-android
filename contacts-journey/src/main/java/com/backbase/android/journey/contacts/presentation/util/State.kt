package com.backbase.android.journey.contacts.presentation.util

import androidx.annotation.StringRes

data class FieldValue<T>(
    val value: T,
    val fieldStatus: FieldStatus = FieldStatus.Init
)

sealed class FieldStatus{
    object Init: FieldStatus()
    object Valid: FieldStatus()
    data class Invalid(@StringRes val errorMessage: Int? = null): FieldStatus()
}