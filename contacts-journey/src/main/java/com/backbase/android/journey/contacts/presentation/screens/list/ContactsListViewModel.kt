package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.usecase.GetContactsUseCase
import com.backbase.android.journey.contacts.presentation.screens.list.intent.ContactsListIntent
import com.backbase.android.journey.contacts.presentation.screens.list.intent.ContactsListIntentHandler
import kotlinx.coroutines.flow.*
/**
 * ViewModel for the Contacts List screen.
 *
 * Responsible for maintaining and exposing [ContactsListState] to the UI,
 * and for interpreting and delegating [ContactsListIntent] actions to the
 * [ContactsListIntentHandler] for execution.
 *
 * This ViewModel does not allow for intent handling to be customized.
 *
 * @property getContactsUseCase Use case to retrieve contacts from a data source, supporting pagination and search.
 */
class ContactsListViewModel(
    private val getContactsUseCase: GetContactsUseCase,
) : ViewModel() {

    /**
     * State holder representing the current UI state of the contacts list.
     * Includes fields like the current list of contacts, search query,
     * loading status, and pagination data.
     */
    private val _state = MutableStateFlow(ContactsListState<Unit>())

    /**
     * Publicly exposed state flow to be collected by the UI layer.
     */
    val state: StateFlow<ContactsListState<Unit>> = _state.asStateFlow()

    private val intentHandler = ContactsListIntentHandler<Unit>(
        getContactsUseCase = getContactsUseCase,
        scope = viewModelScope
    )

    init {
        // Automatically trigger initial loading of contacts when ViewModel is created.
        handleIntent(ContactsListIntent.InitLoadContacts)
    }

    /**
     * Processes user and screen-level intents.
     *
     * Depending on the type of [ContactsListIntent], it delegates the appropriate logic
     * to [ContactsListIntentHandler] to perform actions such as:
     * - Loading initial contacts
     * - Loading more contacts (pagination)
     * - Executing a debounced search
     * - Refreshing the contact list
     *
     * @param intent The intent representing a user action or screen event.
     */
    fun handleIntent(intent: ContactsListIntent) {
        when (intent) {
            is ContactsListIntent.Search -> intentHandler.handleSearch(intent, state.value, _state)
            ContactsListIntent.LoadMore -> intentHandler.handleLoadMode(state.value, _state)
            ContactsListIntent.Refresh -> intentHandler.handleRefresh(state.value, _state)
            ContactsListIntent.InitLoadContacts -> intentHandler.handleInitLoadContacts(state.value, _state)
        }
    }
}