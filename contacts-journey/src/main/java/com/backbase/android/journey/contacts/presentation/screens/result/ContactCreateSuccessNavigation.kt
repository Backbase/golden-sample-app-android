package com.backbase.android.journey.contacts.presentation.screens.result

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.journey.contacts.ContactsRouting

/**
 * Adds the contact creation success screen to the navigation graph.
 *
 * This destination represents the screen shown after successfully creating a contact.
 * It allows users to either create another contact or return to the contact list.
 *
 * @param onNavigateToCreateContact Callback triggered when the user chooses to create another contact.
 * @param onNavigateToContactList Callback triggered when the user chooses to return to the contact list.
 * @param routePrefix Optional prefix used to namespace the route within the navigation graph.
 */
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