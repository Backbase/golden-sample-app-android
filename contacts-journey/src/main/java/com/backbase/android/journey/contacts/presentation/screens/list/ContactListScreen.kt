package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.backbase.android.journey.contacts.domain.model.DefaultContactModel
import com.backbase.android.journey.contacts.presentation.components.ContactList

@Composable
fun ContactListScreen(
    viewModel: ContactsListViewModel<Unit, Unit>,
    onNavigateToDetails: (DefaultContactModel) -> Unit
) {
    val state by viewModel.state.collectAsState()

    ContactList<Unit, Unit>(
        contacts = state.contacts,
        onContactClick = onNavigateToDetails,
        onSearch = { query ->
            viewModel.handleIntent(
                ContactsListIntent.Search(query)
            )
        },
        onLoadMore = {
            viewModel.handleIntent(
                ContactsListIntent.LoadMore
            )
        },
        isLoading = state.isLoading
    )
}