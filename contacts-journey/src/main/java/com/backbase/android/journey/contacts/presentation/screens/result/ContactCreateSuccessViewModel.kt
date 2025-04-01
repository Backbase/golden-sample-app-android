package com.backbase.android.journey.contacts.presentation.screens.result

import androidx.lifecycle.ViewModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactCreateSuccessViewModel(
    val contact: ContactModel
): ViewModel() {

    private val _state = MutableStateFlow(DefaultContactCreateSuccessState(
        contact = contact
    ))
    val state: StateFlow<DefaultContactCreateSuccessState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ContactCreateSuccessViewEffect>()
    val effect = _effect.asSharedFlow()

    public fun handleIntent(intent: ContactCreateSuccessIntent) {
        when (intent) {
            ContactCreateSuccessIntent.BackToContacts -> _effect.tryEmit(ContactCreateSuccessViewEffect.NavigateToContacts)
            ContactCreateSuccessIntent.CreateNewContact -> _effect.tryEmit(ContactCreateSuccessViewEffect.NavigateToNewContact)
        }
    }

}