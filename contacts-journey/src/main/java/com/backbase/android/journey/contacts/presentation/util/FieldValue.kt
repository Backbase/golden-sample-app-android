package com.backbase.android.journey.contacts.presentation.util


data class FieldValue<T>(
    val value: T,
    val fieldStatus: FieldStatus = FieldStatus.Init
)