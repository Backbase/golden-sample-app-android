package com.backbase.android.retail.contacts

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.contactsJourney(block: ContactJourneyScope.() -> Unit) {
    ContactJourneyScope(this).block()
}

class ContactJourneyScope(val navBuilder: NavGraphBuilder) {
    var destinationPrefix = ""
}

fun ContactJourneyScope.contactsScreen(block: ContactsScreenScope.() -> Unit) {
    val scope = ContactsScreenScope().apply(block)
    navBuilder.composable(destinationPrefix + scope.destination) {
        ContactsJourney()
    }
}

fun ContactJourneyScope.detailsScreen(block: ContactsScreenScope.() -> Unit) {
    val scope = ContactsScreenScope().apply(block)
    navBuilder.composable(destinationPrefix + scope.destination) {
        ContactsJourney()
    }
}

class ContactsRouteScope {
    var onSearchAction: (flag: Boolean) -> Unit = {}
    var onItemClickedAction: (userName: String) -> Unit = {}

}

class ContactsScreenScope {
    var destination: String? = null
    var pluggableScreen: (@Composable () -> Unit)? = null
    fun router(block: ContactsRouteScope.() -> Unit) {

    }

    fun configuration(block: ContactConfigurationScope.() -> Unit) {

    }
}

class ContactConfigurationScope {
    var title: String = "default title"
}
