package com.backbase.android.journey.contacts.presentation.screens.create_contact_account2

import com.backbase.android.journey.contacts.presentation.screens.create_contact.FieldValue

data class CreateContactAccountState<StateExtension> (
    val accountNumber: FieldValue<String> = FieldValue(""),
    val accountName: FieldValue<String> = FieldValue(""),

    val isLoading: Boolean = false,
    val error: String? = null,
    val isSaved: Boolean = false,
    val extension: StateExtension? = null
)