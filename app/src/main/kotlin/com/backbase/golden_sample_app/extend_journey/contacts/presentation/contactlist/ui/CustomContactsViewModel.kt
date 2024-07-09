package com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.retail.journey.contacts.ContactsPageCursor
import com.backbase.android.retail.journey.contacts.ContactsPageFirstCursor
import com.backbase.android.retail.journey.contacts.ContactsPageRequestParameters
import com.backbase.android.retail.journey.contacts.ContactsUseCase
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

    private var nextPageCursor: ContactsPageCursor = ContactsPageFirstCursor
    private val loadedContacts = mutableSetOf<ContactUiModel>()

    fun onEvent(event: CustomContactsEvent) {
        when (event) {
            is CustomContactsEvent.OnGetContacts -> getContacts(event.query)
        }
    }

    private fun getContacts(query: String = "") {
        viewModelScope.launch {
            withContext(defaultDispatcher) {
                val result = useCase.getContactsPage(
                    ContactsPageRequestParameters {
                        searchQuery = query.trimStart()
                        cursor = nextPageCursor
                    }
                )
                when (result) {
                    is ContactsUseCase.Result.Success -> {
                        nextPageCursor = result.value.nextPageCursor ?: ContactsPageFirstCursor
                        val data = result.value.contacts.map { data -> mapper.mapToUi(data) }
                        loadedContacts += data

                        val filteredContacts = loadedContacts.filter { model ->
                            model.name.lowercase().contains(query)
                        }.sortedBy { it.name.uppercase(Locale.US) }

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                contacts = filteredContacts,
                                error = null
                            )
                        }
                    }

                    is ContactsUseCase.Result.Failure -> {
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
