package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.journey.contacts.ContactsRouting
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.presentation.components.ContactsComponents

fun NavGraphBuilder.contactListNavigation(
    contactsListViewModel: ContactsListViewModel,
    onNavigateToDetails: (ContactModel) -> Unit,
    onNavigateToCreate: () -> Unit,
    contactsComponents: ContactsComponents = ContactsComponents(),
    routePrefix: String = ""
){
    composable(ContactsRouting.List.route(routePrefix)) {
        ContactListScreen(
            viewModel = contactsListViewModel,
            contactsComponents = contactsComponents,
            onNavigateToDetails = onNavigateToDetails,
            onNavigateToCreate = onNavigateToCreate
        )
    }
}