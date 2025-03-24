package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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


class CreateContactAccountViewModel2<StateExtension, IntentExtension>(
    private val intentExtensionHandler: (CreateContactAccountState<StateExtension>, IntentExtension, MutableStateFlow<CreateContactAccountState<StateExtension>>) -> Unit
) : ViewModel() {
    private val intentHandler = CreateContactAccountIntentHandler<StateExtension>()

    private val _state = MutableStateFlow(CreateContactAccountState<StateExtension>())
    val state: StateFlow<CreateContactAccountState<StateExtension>> = _state.asStateFlow()

    fun handleIntent(intent: CreateContactAccountIntent2<IntentExtension>) {
        when(intent){
            is CreateContactAccountIntent2.ChangeAccountName -> TODO()
            is CreateContactAccountIntent2.ChangeAccountNumber -> TODO()
            is CreateContactAccountIntent2.SaveAccount -> TODO()
            is CreateContactAccountIntent2.GenericIntent -> intentExtensionHandler(_state.value, intent.data, _state)
        }
    }
}

class CreateContactAccountViewModelFactory<StateExtension, IntentExtension>(
    private val intentExtensionHandler: (
        CreateContactAccountState<StateExtension>,
        IntentExtension,
        MutableStateFlow<CreateContactAccountState<StateExtension>>
    ) -> Unit
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateContactAccountViewModel2::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateContactAccountViewModel2(intentExtensionHandler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class CustomFragment: Fragment(){
    private val viewModel: CreateContactAccountViewModel2<CustomCreateContactStateExtension, CustomCreateContactAccountIntent2> by viewModels {
        CreateContactAccountViewModelFactory<CustomCreateContactStateExtension, CustomCreateContactAccountIntent2>({
                state, intent, stateFlow -> {
                    when(intent){
                        CustomCreateContactAccountIntent2.CustomIntent -> TODO()
                        CustomCreateContactAccountIntent2.CustomIntent2 -> TODO()
                    }
            }
        })
    }
}