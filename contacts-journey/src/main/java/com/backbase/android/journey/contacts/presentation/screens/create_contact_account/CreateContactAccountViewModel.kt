package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.presentation.screens.create_contact.FieldValue
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

// Below are examples for customer implementations
sealed class CustomCreateContactAccountIntent {
    class DefaultIntent(val value: CreateContactAccountIntent): CustomCreateContactAccountIntent()
    object CustomIntent: CustomCreateContactAccountIntent()
    object CustomIntent2: CustomCreateContactAccountIntent()
}

data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)

class CustomCreateContactAccountViewModel : ViewModel() {
    private val intentHandler = CreateContactAccountIntentHandler<CustomCreateContactStateExtension>()

    private val _state = MutableStateFlow(CreateContactAccountState<CustomCreateContactStateExtension>())
    val state: StateFlow<CreateContactAccountState<CustomCreateContactStateExtension>> = _state.asStateFlow()

    fun handleIntent(intent: CustomCreateContactAccountIntent) {
        when(intent){
            CustomCreateContactAccountIntent.CustomIntent -> TODO()
            CustomCreateContactAccountIntent.CustomIntent2 -> TODO()
            is CustomCreateContactAccountIntent.DefaultIntent -> intentHandler.handleIntent(intent.value, _state, viewModelScope)
        }
    }
}

class CustomCreateContactAccountViewModel2 : ViewModel() {
    private val intentHandler = CreateContactAccountIntentHandler<CustomCreateContactStateExtension>()

    private val _state = MutableStateFlow(CreateContactAccountState<CustomCreateContactStateExtension>())
    val state: StateFlow<CreateContactAccountState<CustomCreateContactStateExtension>> = _state.asStateFlow()

    fun handleIntent(intent: CustomCreateContactAccountIntent) {
        when(intent){
            CustomCreateContactAccountIntent.CustomIntent -> TODO()
            CustomCreateContactAccountIntent.CustomIntent2 -> TODO()
            is CustomCreateContactAccountIntent.DefaultIntent -> {
                when(intent.value){
                    is CreateContactAccountIntent.ChangeAccountName -> intentHandler.handleIntent(intent.value, _state, viewModelScope)
                    is CreateContactAccountIntent.ChangeAccountNumber -> intentHandler.handleIntent(intent.value, _state, viewModelScope)
                    CreateContactAccountIntent.SaveAccount -> TODO()
                }
            }
        }
    }
}