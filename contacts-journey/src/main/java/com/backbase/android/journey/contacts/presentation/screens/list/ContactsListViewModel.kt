package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.usecase.GetContactsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactsListViewModel(
    private val getContactsUseCase: GetContactsUseCase,
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
            is ContactsListIntent.SelectContact -> TODO()
        }
    }

    private fun loadContacts() {
        if(state.value.isLoading) return
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getContactsUseCase(
                page = _state.value.currentPage,
                pageSize = pageSize,
                query = _state.value.searchQuery,
            ).onSuccess { newContacts ->
                    _state.value = _state.value.copy(
                        contacts = _state.value.contacts + newContacts,
                        currentPage = _state.value.currentPage + 1,
                        isLoading = false,
                        error = null
                    )
            }.onFailure { error ->
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