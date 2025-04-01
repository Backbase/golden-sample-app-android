package com.backbase.android.journey.contacts.presentation.screens.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.backbase.android.journey.contacts.ContactsRouting

fun NavGraphBuilder.contactDetailsNavigation(
    contactDetailsViewModel: ContactDetailsViewModel,
    routePrefix: String = ""
){
    composable(routePrefix + ContactsRouting.Details.ROUTE, arguments = listOf(
        navArgument(ContactsRouting.Details.NAVARG_ID) { type = NavType.StringType }
    )) { backStackEntry ->
        contactDetailsViewModel.handleIntent(
            ContactDetailsIntent.LoadContact(backStackEntry.arguments?.getString(ContactsRouting.Details.NAVARG_ID) ?: "")
        )
        ContactDetailsScreen(
            viewModel = contactDetailsViewModel
        )
    }
}