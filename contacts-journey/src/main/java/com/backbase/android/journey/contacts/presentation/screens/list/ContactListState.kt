package com.backbase.android.journey.contacts.presentation.screens.list

import com.backbase.android.journey.contacts.domain.model.ContactModel

data class ContactsListState<ContactExtension, AccountExtension>(
    val contacts: List<ContactModel<ContactExtension, AccountExtension>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1,
    val searchQuery: String = ""
)