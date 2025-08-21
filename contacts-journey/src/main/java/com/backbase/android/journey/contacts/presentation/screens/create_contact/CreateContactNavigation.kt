package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.backbase.android.journey.contacts.ContactsRouting

fun <E> NavGraphBuilder.createContactNavigation(
    viewModelFactory:  CreateContactViewModelFactory<E>,
    onEffect: (CreateContactSideEffect?) -> Unit,
    content: @Composable (state: CreateContactState<E>, onIntent: (CreateContactIntent) -> Unit) -> Unit,
    routePrefix: String = "",
){
    composable(routePrefix + ContactsRouting.Create.route(routePrefix)) {
        val viewModel: CreateContactViewModel<E> = viewModel(factory = viewModelFactory)
        val state by viewModel.uiState.collectAsState()
        val effect by viewModel.effects.collectAsState(initial = null)

        LaunchedEffect(effect) { onEffect(effect) }
        content(state) { intent -> viewModel.handle(intent) }
    }
}
