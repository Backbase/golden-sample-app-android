package com.backbase.android.journey.contacts.presentation.screens.list

import com.backbase.android.journey.contacts.domain.model.ContactModel

sealed interface ContactsListIntent {
    data class Search(val query: String) : ContactsListIntent
    object LoadMore : ContactsListIntent
    object Refresh : ContactsListIntent
    data class SelectContact(val contact: ContactModel): ContactsListIntent
}