package com.backbase.android.journey.contacts.presentation.screens.list

sealed interface ContactsListIntent {
    data class Search(val query: String) : ContactsListIntent
    object LoadMore : ContactsListIntent
    object Refresh : ContactsListIntent
}