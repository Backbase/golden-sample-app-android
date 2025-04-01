package com.backbase.android.journey.contacts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModel
import com.backbase.android.journey.contacts.presentation.screens.create_contact.createContactNavigation
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsViewModel
import com.backbase.android.journey.contacts.presentation.screens.detail.contactDetailsNavigation
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListViewModel
import com.backbase.android.journey.contacts.presentation.screens.list.contactListNavigation
import com.backbase.android.journey.contacts.presentation.screens.result.contactCreateSuccessNavigation

fun NavGraphBuilder.contactsJourneyNavigation(
    navController: NavController,
    contactsListViewModel: ContactsListViewModel,
    createContactViewModel: CreateContactViewModel,
    contactDetailsViewModel: ContactDetailsViewModel,
    routePrefix: String = ""
) {

    contactListNavigation(
        contactsListViewModel = contactsListViewModel,
        onNavigateToDetails = { contact ->
            navController.navigate(
                ContactsRouting.Details.navigationRoute(
                    contactId = contact.id,
                    routePrefix = routePrefix
                )
            )
        },
        onNavigateToCreate = {
            navController.navigate(
                ContactsRouting.Create.route(routePrefix)
            )
        },
        routePrefix = routePrefix
    )

    contactDetailsNavigation(
        contactDetailsViewModel = contactDetailsViewModel,
        routePrefix = routePrefix
    )

    createContactNavigation(
        createContactViewModel = createContactViewModel,
        routePrefix = routePrefix,
        onNavigateBack = {
            navController.popBackStack()
         },
        onNavigateAfterSuccess = {
            TODO("Not implemented yet")
        }
    )

    contactCreateSuccessNavigation(
        contactsListViewModel = contactsListViewModel,
        routePrefix = routePrefix
    )

}