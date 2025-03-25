package com.backbase.android.journey.contacts.presentation.screens.create_contact_account2

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.journey.contacts.presentation.screens.create_contact.FieldValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateContactAccountViewModel2<StateExtension, IntentExtension>(
    private val intentExtensionHandler: IntentExtensionHandler<StateExtension, IntentExtension>? = null
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

interface IntentExtensionHandler<StateExtension, IntentExtension> {
    operator fun invoke(
        currentState: CreateContactAccountState<StateExtension>,
        intentExtension: IntentExtension,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>)
}

class CreateContactAccountViewModelFactory<StateExtension, IntentExtension>(
    private val intentExtensionHandler: IntentExtensionHandler<StateExtension, IntentExtension>? = null
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateContactAccountViewModel2::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateContactAccountViewModel2(intentExtensionHandler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

typealias DefaultViewModel = CreateContactAccountViewModel2<Unit, Unit>

fun defaultViewModelFactory(): CreateContactAccountViewModelFactory<Unit, Unit> =
    CreateContactAccountViewModelFactory(intentExtensionHandler = null)