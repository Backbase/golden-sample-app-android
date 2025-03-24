package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateContactAccountViewModel : ViewModel() {
    private val intentHandler = CreateContactAccountIntentHandler<Unit>()

    private val _state = MutableStateFlow(CreateContactAccountState<Unit>())
    val state: StateFlow<CreateContactAccountState<Unit>> = _state.asStateFlow()

    fun handleIntent(intent: CreateContactAccountIntent) {
        intentHandler.handleIntent(intent, _state, viewModelScope)
    }
}