package com.backbase.android.journey.contacts.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactDetailsViewModel(
    private val repository: ContactsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ContactDetailsState())
    val state: StateFlow<ContactDetailsState> = _state.asStateFlow()

    fun handleIntent(intent: ContactDetailsIntent) {
        when (intent) {
            is ContactDetailsIntent.LoadContact -> loadContact(intent.contactId)
        }
    }

    private fun loadContact(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            repository.getContactDetails(id)
                .onSuccess { contact ->
                    _state.value = _state.value.copy(
                        contact = contact,
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
} 