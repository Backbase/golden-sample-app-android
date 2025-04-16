package com.backbase.android.journey.contacts.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.usecase.GetContactDetailsUseCase
import com.backbase.android.journey.contacts.presentation.screens.detail.intent.ContactDetailsIntent
import com.backbase.android.journey.contacts.presentation.screens.detail.intent.ContactDetailsIntentHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactDetailsViewModel(
    private val getContactDetailsUseCase: GetContactDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ContactDetailsState<Unit>())
    val state: StateFlow<ContactDetailsState<Unit>> = _state.asStateFlow()

    private val contactDetailsIntentHandler = ContactDetailsIntentHandler(
        getContactDetailsUseCase = getContactDetailsUseCase,
        stateFlow = _state,
        scope = viewModelScope
    )

    fun handleIntent(intent: ContactDetailsIntent) =
        contactDetailsIntentHandler.handleIntent(intent)

}