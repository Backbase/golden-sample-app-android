package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.journey.contacts.ContactsRouting
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect.ToContactCreateResult

fun NavGraphBuilder.createContactNavigation(
    createContactViewModelFactory:  ViewModelProvider.Factory,
    onNavigateAfterSuccess: () -> Unit,
    routePrefix: String = ""
){
    composable(routePrefix + ContactsRouting.Create.route(routePrefix)) {
        val viewModel: CreateContactViewModel<*> = viewModel(
            factory = createContactViewModelFactory
        )
        val state by viewModel.uiState.collectAsState()
        val effect by viewModel.effects.collectAsState(initial = null)

        LaunchedEffect(effect) {
            when (effect) {
                is ToContactCreateResult -> onNavigateAfterSuccess()
                null -> { /* no-op */ }
            }
        }

        CreateContactScreen(state) { intent -> viewModel.handle(intent) }
    }
}
