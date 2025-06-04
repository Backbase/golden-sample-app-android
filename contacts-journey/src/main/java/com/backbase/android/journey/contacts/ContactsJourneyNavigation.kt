package com.backbase.android.journey.contacts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactScreen
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect.ToContactCreatedResult
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModelFactory
import com.backbase.android.journey.contacts.presentation.screens.create_contact.createContactNavigation
import com.backbase.android.journey.contacts.presentation.screens.detail.ContactDetailsViewModel
import com.backbase.android.journey.contacts.presentation.screens.detail.contactDetailsNavigation
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListViewModel
import com.backbase.android.journey.contacts.presentation.screens.list.contactListNavigation

fun <E> NavGraphBuilder.contactsJourneyNavigation(
    createContactViewModelFactory: CreateContactViewModelFactory<E>,
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
        content = { state, onIntent -> CreateContactScreen(state, onIntent) },
        viewModelFactory = createContactViewModelFactory,
        onEffect = { effect ->
            when (effect) {
                is ToContactCreatedResult -> navController.navigate(ContactsRouting.Create.route(routePrefix))
                null -> { /* no-op */ }
            }
        },
        routePrefix = routePrefix
    )
}
