package com.backbase.golden_sample_app.extend_journey.contacts.presentation.ui

sealed interface CustomContactsEvent {
    object OnAddContact : CustomContactsEvent
    data class OnGetContacts(val query: String = "") : CustomContactsEvent
}
