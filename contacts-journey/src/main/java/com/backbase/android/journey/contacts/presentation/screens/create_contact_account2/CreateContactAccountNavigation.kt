package com.backbase.android.journey.contacts.presentation.screens.create_contact_account2

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.journey.contacts.ContactsRouting
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactScreen
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModel

fun NavGraphBuilder.createContactAccountNavigation(
    navController: NavController,
    createContactViewModel: CreateContactViewModel,
    onNavigateBack: () -> Unit = {
        navController.popBackStack()
    },
    onNavigateAfterSuccess: () -> Unit = {
        navController.navigate(ContactsRouting.List.ROUTE) {
            popUpTo(ContactsRouting.List.ROUTE) {
                inclusive = true
            }
        }
    }
){
    composable(ContactsRouting.Create.ROUTE) {
        CreateContactAccountScreen(
            viewModel = createContactViewModel,
            onNavigateBack = onNavigateBack,
            onNavigateAfterSuccess = onNavigateAfterSuccess
        )
    }
}