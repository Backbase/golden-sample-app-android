package com.backbase.android.journey.contacts.presentation.screens.detail

import com.backbase.android.journey.contacts.domain.model.ContactModel

data class ContactDetailsState<Extension>(
    val contact: ContactModel,
    val isLoading: Boolean = false,
    val error: String? = null,

    val extension: Extension? = null
)