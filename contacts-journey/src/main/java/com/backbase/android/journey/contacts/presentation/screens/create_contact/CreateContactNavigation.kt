package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.journey.contacts.ContactsRouting

fun NavGraphBuilder.createContactNavigation(
    createContactViewModel: CreateContactViewModel,
    onNavigateBack: () -> Unit,
    onNavigateAfterSuccess: () -> Unit,
    routePrefix: String = ""
){
    composable(routePrefix + ContactsRouting.Create.route(routePrefix)) {
        CreateContactScreen(
            viewModel = createContactViewModel,
            onNavigateBack = onNavigateBack,
            onNavigateAfterSuccess = onNavigateAfterSuccess
        )
    }
}