package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.backbase.android.journey.contacts.data.MockContactsCreatorImpl
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.model.DefaultContactModel
import com.backbase.android.journey.contacts.presentation.components.ContactListItem
import com.backbase.android.journey.contacts.presentation.components.SearchBar

@Composable
fun ContactListScreen(
    viewModel: ContactsListViewModel<Unit, Unit>,
    onNavigateToDetails: (DefaultContactModel) -> Unit,
    onNavigateToCreate: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreate) {
                Text("+")
            }
        }
    ) { padding ->
        ContactList(
            state = state,
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
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun <ContactExtension, AccountExtension> ContactList(
    state: ContactsListState<ContactExtension, AccountExtension>,
    onContactClick: (ContactModel<ContactExtension, AccountExtension>) -> Unit,
    onSearch: (String) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize()) {
        SearchBar(onSearch)
        if(state.error != null) { // Error
            Text(
                text = "Error"
            )
        } else if (state.isLoading && state.contacts.isEmpty()){ // Loading
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        } else if (state.contacts.isEmpty()){ // Result empty
            Text(
                text = "Empty"
            )
        } else { // Not empty
            LazyColumn(Modifier.weight(1f)) {
                items(state.contacts) { contact ->
                    ContactListItem(contact, onContactClick)
                }
                if (state.isLoading) { // Load more
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .padding(8.dp, 16.dp, 8.dp, 0.dp)
                        )
                    }
                } else {
                    item {
                        Button(onClick = onLoadMore, modifier = Modifier.fillMaxWidth().padding(8.dp, 16.dp, 8.dp, 0.dp)) {
                            Text("Load More")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactsListPreview() {
    ContactList<Unit, Unit>(
        state = ContactsListState(
            contacts = MockContactsCreatorImpl.createMockContactsList(10),
            isLoading = false,
            error = null,
            currentPage = 1,
            searchQuery = ""
        ),
        onContactClick = {},
        onSearch = {},
        onLoadMore = {}
    )
}