package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class OpenContactsListViewModel(
    private val repository: ContactsRepository,
    private val _state: MutableStateFlow<ContactsListState> = MutableStateFlow(ContactsListState())
) : ViewModel() {
    val state: StateFlow<ContactsListState> = _state.asStateFlow()

    private val pageSize = 20

    init {
        loadContacts()
    }

    open fun handleIntent(intent: ContactsListIntent) {
        when (intent) {
            is ContactsListIntent.Search -> performSearch(intent.query)
            ContactsListIntent.LoadMore -> loadContacts()
            ContactsListIntent.Refresh -> refresh()
        }
    }

    private fun loadContacts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            repository.getContacts(_state.value.currentPage, pageSize)
                .onSuccess { newContacts ->
                    _state.value = _state.value.copy(
                        contacts = _state.value.contacts + newContacts,
                        currentPage = _state.value.currentPage + 1,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }

    private fun performSearch(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        refresh()
    }

    private fun refresh() {
        _state.value = _state.value.copy(
            contacts = emptyList(),
            currentPage = 1
        )
        loadContacts()
    }
}

class ExtendedContactListViewModel(
    private val repository: ContactsRepository,
    private val _state: MutableStateFlow<ContactsListState> = MutableStateFlow(ContactsListState())
): OpenContactsListViewModel(
    repository = repository,
    _state = _state
) {

    override fun handleIntent(intent: ContactsListIntent){
        when (intent) {
            is ContactsListIntent.LoadMore -> TODO()
            is ContactsListIntent.Refresh -> TODO()
            is ContactsListIntent.Search -> TODO()
        }
    }

    fun handleIntent(intent: ExtensionIntent){
        when (intent) {
            is ExtensionIntent.DeleteContact -> TODO()
        }
    }
}

sealed class ExtensionIntent{
    class DeleteContact(contactId: String): ExtensionIntent()
}