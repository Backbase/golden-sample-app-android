package com.backbase.android.journey.contacts.presentation.screens.detail

import com.backbase.android.journey.contacts.domain.model.ContactModel

data class ContactDetailsState(
    val contact: ContactModel,
    val isLoading: Boolean = false,
    val error: String? = null
)