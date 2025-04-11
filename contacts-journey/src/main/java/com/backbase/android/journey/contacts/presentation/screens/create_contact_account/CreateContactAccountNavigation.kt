package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.backbase.android.journey.contacts.ContactsRouting

fun NavGraphBuilder.createContactAccountNavigation(
    createContactAccountViewModel: CreateContactAccountViewModel<Unit>,
    routePrefix: String = ""
){
    composable(ContactsRouting.Details.registerRoute(routePrefix), arguments = listOf(
        navArgument(ContactsRouting.Details.CONTACT_ID) { type = NavType.StringType }
    )) { backStackEntry ->
        CreateContactAccountScreen(
            viewModel = createContactAccountViewModel,
            onNavigateAfterSuccess = {}
        )
    }
}