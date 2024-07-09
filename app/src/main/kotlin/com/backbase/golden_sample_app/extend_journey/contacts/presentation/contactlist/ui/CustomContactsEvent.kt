package com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.ui

sealed interface CustomContactsEvent {
    data class OnGetContacts(val query: String = "") : CustomContactsEvent
}
