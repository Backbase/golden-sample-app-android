package com.backbase.android.journey.contacts.presentation.screens.list.intent

import com.backbase.android.journey.contacts.domain.usecase.GetContactsUseCase
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.plus

/**
 * Handles user intents for the Contacts List screen, including loading, pagination,
 * search with debounce, and refresh operations.
 *
 * This class coordinates interaction between the UI state and the [GetContactsUseCase],
 * updating the [ContactsListState] through the provided [MutableStateFlow].
 *
 * @property getContactsUseCase Use case for retrieving contacts with pagination and search support.
 * @property scope Coroutine scope used for launching background tasks like loading and debouncing.
 */
class ContactsListIntentHandler(
    private val getContactsUseCase: GetContactsUseCase,
    private val scope: CoroutineScope,
) {

    private val pageSize = 20
    private var searchJob: Job? = null

    /**
     * Loads the initial page of contacts.
     * Triggers a coroutine to fetch contacts using the current state parameters.
     *
     * @param state The current state of the contacts list.
     * @param stateFlow The mutable state flow used to update UI state.
     */
    fun handleInitLoadContacts(
        state: ContactsListState,
        stateFlow: MutableStateFlow<ContactsListState>
    ) {
        scope.launch{
            loadContacts(
                state = state,
                stateFlow = stateFlow
            )
        }
    }

    /**
     * Loads the next page of contacts if not already loading.
     * Increments the current page and fetches additional contacts.
     *
     * @param state The current state of the contacts list.
     * @param stateFlow The mutable state flow used to update UI state.
     */
    fun handleLoadMode(
        state: ContactsListState,
        stateFlow: MutableStateFlow<ContactsListState>
    ) {
        if (state.isLoading) return

        stateFlow.value = state.copy(currentPage = state.currentPage + 1)
        scope.launch{
            loadContacts(
                state = state,
                stateFlow = stateFlow
            )
        }
    }

    /**
     * Handles search query changes with a debounce of 300ms.
     * Cancels any ongoing search and loads contacts based on the new query.
     *
     * @param query The updated search query.
     * @param state The current state of the contacts list.
     * @param stateFlow The mutable state flow used to update UI state.
     */
    fun handleSearch(
        searchIntent: ContactsListIntent.Search,
        state: ContactsListState,
        stateFlow: MutableStateFlow<ContactsListState>
    ) {
        stateFlow.value = state.copy(searchQuery = searchIntent.query)
        searchJob?.cancel()
        scope.launch {
            delay(300)
            loadContacts(
                state = state,
                stateFlow = stateFlow
            )
        }
    }

    /**
     * Refreshes the contact list if not currently loading.
     * Resets the page and contact list before reloading.
     *
     * @param state The current state of the contacts list.
     * @param stateFlow The mutable state flow used to update UI state.
     */
    fun handleRefresh(
        state: ContactsListState,
        stateFlow: MutableStateFlow<ContactsListState>
    ) {
        if (state.isLoading) return

        stateFlow.value = state.copy(
            contacts = emptyList(),
            currentPage = 0
        )
        scope.launch{
            loadContacts(
                state = state,
                stateFlow = stateFlow
            )
        }
    }

    /**
     * Loads contacts from the use case and updates the state accordingly.
     * Handles both success and failure outcomes.
     *
     * @param state The current state of the contacts list.
     * @param stateFlow The mutable state flow used to update UI state.
     */
    private suspend fun loadContacts(
        state: ContactsListState,
        stateFlow: MutableStateFlow<ContactsListState>
    ) {
        stateFlow.value = state.copy(isLoading = true)
        getContactsUseCase(
            page = state.currentPage,
            pageSize = pageSize,
            query = state.searchQuery,
        ).onSuccess { newContacts ->
            stateFlow.value = state.copy(
                contacts = state.contacts + newContacts,
                currentPage = state.currentPage + 1,
                isLoading = false,
                error = null,
                canLoadMore = newContacts.size >= pageSize
            )
        }.onFailure { error ->
            stateFlow.value = state.copy(
                isLoading = false,
                error = error.message,
                canLoadMore = false
            )
        }
    }
}