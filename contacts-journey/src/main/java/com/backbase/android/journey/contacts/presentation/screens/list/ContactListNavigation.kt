package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.journey.contacts.ContactsRouting
import com.backbase.android.journey.contacts.domain.model.DefaultContactModel

fun NavGraphBuilder.contactListNavigation(
    navController: NavController,
    contactsListViewModel: ContactsListViewModel,
    onNavigateToDetails: (DefaultContactModel) -> Unit = { contact ->
        navController.navigate(
            ContactsRouting.Details.detailsUrl(contact.id)
        )
    },
    onNavigateToCreate: () -> Unit = {
        navController.navigate(ContactsRouting.Create.ROUTE)
    }
){
    composable(ContactsRouting.List.ROUTE) {
        ContactListScreen(
            viewModel = contactsListViewModel,
            onNavigateToDetails = onNavigateToDetails,
            onNavigateToCreate = onNavigateToCreate
        )
    }
}