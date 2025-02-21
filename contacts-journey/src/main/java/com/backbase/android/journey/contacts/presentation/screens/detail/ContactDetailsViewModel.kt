package com.backbase.android.journey.contacts.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ContactDetailsIntent {
    data class LoadContact(val id: String) : ContactDetailsIntent
}

data class ContactDetailsState<ContactExtension, AccountExtension>(
    val contact: ContactModel<ContactExtension, AccountExtension>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ContactDetailsViewModel<ContactExtension, AccountExtension>(
    private val repository: ContactsRepository<ContactExtension, AccountExtension>
) : ViewModel() {
    private val _state = MutableStateFlow(ContactDetailsState<ContactExtension, AccountExtension>())
    val state: StateFlow<ContactDetailsState<ContactExtension, AccountExtension>> = _state.asStateFlow()

    fun handleIntent(intent: ContactDetailsIntent) {
        when (intent) {
            is ContactDetailsIntent.LoadContact -> loadContact(intent.id)
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