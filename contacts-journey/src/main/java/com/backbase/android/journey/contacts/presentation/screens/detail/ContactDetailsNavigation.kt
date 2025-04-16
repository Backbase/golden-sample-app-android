package com.backbase.android.journey.contacts.presentation.screens.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.backbase.android.journey.contacts.ContactsRouting
import com.backbase.android.journey.contacts.presentation.screens.detail.intent.ContactDetailsIntent

fun NavGraphBuilder.contactDetailsNavigation(
    contactDetailsViewModel: ContactDetailsViewModel,
    routePrefix: String = ""
){
    composable(ContactsRouting.Details.registerRoute(routePrefix), arguments = listOf(
        navArgument(ContactsRouting.Details.CONTACT_ID) { type = NavType.StringType }
    )) { backStackEntry ->
        contactDetailsViewModel.handleIntent(
            ContactDetailsIntent.LoadContact(backStackEntry.arguments?.getString(ContactsRouting.Details.CONTACT_ID) ?: "")
        )
        ContactDetailsScreen(
            viewModel = contactDetailsViewModel
        )
    }
}