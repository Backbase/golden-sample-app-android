package com.backbase.android.journey.contacts.presentation.screens.create_contact_account2

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateContactAccountViewModel2<StateExtension, IntentExtension>(
    private val intentExtensionHandler: CreateContactAccountIntentExtensionHandler<StateExtension, IntentExtension>? = null
) : ViewModel() {

    private val _state = MutableStateFlow(CreateContactAccountState<StateExtension>())
    val state: StateFlow<CreateContactAccountState<StateExtension>> = _state.asStateFlow()

    fun handleIntent(intent: CreateContactAccountIntent2<IntentExtension>) {
        when(intent){
            is CreateContactAccountIntent2.ChangeAccountName -> TODO()
            is CreateContactAccountIntent2.ChangeAccountNumber -> TODO()
            is CreateContactAccountIntent2.SaveAccount -> TODO()
            is CreateContactAccountIntent2.GenericIntent -> intentExtensionHandler?.invoke(_state.value, intent.data, _state)
        }
    }
}

typealias DefaultViewModel = CreateContactAccountViewModel2<Unit, Unit>