package com.backbase.android.journey.contacts

object ContactsRouting {

    object Details {
        const val CONTACT_ID = "contactId"
        private const val ROUTE = "contacts/{$CONTACT_ID}"

        fun registerRoute(routePrefix: String = "") = routePrefix + ROUTE
        fun navigationRoute(
            contactId: String,
            routePrefix: String = ""
        ) = routePrefix + "${routePrefix}contacts/$contactId"
    }

    object List {
        private const val ROUTE = "contacts"

        fun route(routePrefix: String = "") = routePrefix + ROUTE
    }

    object Create {
        private const val ROUTE = "contacts/create"

        fun route(routePrefix: String = "") = routePrefix + ROUTE


        object Success {
            private const val ROUTE = "contacts/create/success"

            fun route(routePrefix: String = "") = routePrefix + ROUTE
        }
    }

    object CreateAccount {
        const val CONTACT_ID = "contactId"
        private const val ROUTE = "contacts/{$CONTACT_ID}/account/create"

        fun navigationRoute(
            contactId: String,
            routePrefix: String = ""
        ) = "${routePrefix}contacts/$contactId/account/create"
        fun registerRoute(routePrefix: String = "") = routePrefix + ROUTE
    }

    fun startDestination(routePrefix: String) = List.route(routePrefix)

}