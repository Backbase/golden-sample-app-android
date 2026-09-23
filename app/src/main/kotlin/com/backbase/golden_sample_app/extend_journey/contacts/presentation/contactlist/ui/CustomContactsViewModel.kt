package com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.business.journey.contacts.model.request.GetContactsRequest
import com.backbase.android.business.journey.contacts.state.CallState
import com.backbase.android.business.journey.contacts.usecase.ContactsUseCase
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.mapper.CustomContactUiMapper
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.model.ContactUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class CustomContactsViewModel(
    private val useCase: ContactsUseCase,
    private val mapper: CustomContactUiMapper,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _uiState = MutableStateFlow(CustomContactsScreenState())
    val uiState: StateFlow<CustomContactsScreenState> = _uiState

    private val loadedContacts = mutableSetOf<ContactUiModel>()

    fun onEvent(event: CustomContactsEvent) {
        when (event) {
            is CustomContactsEvent.OnGetContacts -> getContacts(event.query)
        }
    }

    private fun getContacts(query: String = "") {
        viewModelScope.launch {
            withContext(defaultDispatcher) {
                val result = useCase.getContacts(
                    GetContactsRequest {
                        from = 0
                        size = 100
                        this.query = query.trimStart().ifEmpty { null }
                    }
                )
                when (result) {
                    is CallState.Success -> {
                        val data = result.data.map { contact -> mapper.mapToUi(contact) }
                        loadedContacts.clear()
                        loadedContacts += data

                        val filteredContacts = loadedContacts.filter { model ->
                            model.name.lowercase().contains(query.lowercase())
                        }.sortedBy { it.name.uppercase(Locale.US) }

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                contacts = filteredContacts,
                                error = null
                            )
                        }
                    }

                    is CallState.Empty,
                    is CallState.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}
