package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.journey.contacts.ContactsRouting


fun NavGraphBuilder.createContactNavigation(
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
        CreateContactScreen(
            viewModel = createContactViewModel,
            onNavigateBack = onNavigateBack,
            onNavigateAfterSuccess = onNavigateAfterSuccess
        )
    }
}