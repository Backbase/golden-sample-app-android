package com.backbase.android.journey.contacts.presentation

object ContactsRouting {

    object Details {
        const val NAVARG_ID = "contactId"
        const val ROUTE = "contacts/{$NAVARG_ID}"

        fun detailsUrl(contactId: String) = "contacts/$contactId"
    }

    object List {
        const val ROUTE = "contacts"
    }

    object Create {
        const val ROUTE = "contacts/create"
    }
}