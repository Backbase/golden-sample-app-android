package com.backbase.android.journey.contacts

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.backbase.android.journey.contacts.presentation.screens.create_contact.createContactNavigation
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsViewModel
import com.backbase.android.journey.contacts.presentation.screens.detail.contactDetailsNavigation
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListViewModel
import com.backbase.android.journey.contacts.presentation.screens.list.contactListNavigation

fun NavGraphBuilder.contactsJourneyNavigation(
    createContactViewModelFactory:  ViewModelProvider.Factory,
    navController: NavController,
    contactsListViewModel: ContactsListViewModel,
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
        createContactViewModelFactory = createContactViewModelFactory,
        routePrefix = routePrefix,
        onNavigateAfterSuccess = {
            navController.navigate(
                ContactsRouting.Create.route(routePrefix)
            )
        }
    )
}
