package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactsListViewModel(
    private val repository: ContactsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ContactsListState())
    val state: StateFlow<ContactsListState> = _state.asStateFlow()

    private val pageSize = 20

    init {
        loadContacts()
    }

    fun handleIntent(intent: ContactsListIntent) {
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