package com.backbase.android.journey.contacts.presentation.screens.result

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.journey.contacts.ContactsRouting


fun NavGraphBuilder.contactCreateSuccessNavigation(
    onNavigateToCreateContact: () -> Unit,
    onNavigateToContactList: () -> Unit,
    routePrefix: String = ""
){
    composable(ContactsRouting.Create.Success.route(routePrefix)) {
        ContactCreateSuccessScreen(
            onNavigateToCreateContact = onNavigateToCreateContact,
            onNavigateToContactList = onNavigateToContactList
        )
    }
}