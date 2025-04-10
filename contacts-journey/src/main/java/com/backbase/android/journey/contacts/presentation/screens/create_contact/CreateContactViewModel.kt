package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.SaveContactIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.SaveContactIntentHandlerImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.UpdateAccountNumberHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.UpdateEmailHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.UpdateNameHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

//Variation of the ViewModel where the Intent handlers are separated.
class CreateContactViewModel(
    private val saveNewContactUseCase: SaveNewContactUseCase,
    val validationFunctions: MutableList<(CreateContactState<Unit>) -> CreateContactState<Unit>> = mutableListOf(),
    private val saveContactIntentHandler: SaveContactIntentHandler<Unit> = SaveContactIntentHandlerImpl(),
) : ViewModel() {

    private val _state = MutableStateFlow(CreateContactState<Unit>())
    val state: StateFlow<CreateContactState<Unit>> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateContactViewEffect?>()
    val effect: SharedFlow<CreateContactViewEffect?> = _effect.asSharedFlow()

    private val updateAccountNumberHandler = UpdateAccountNumberHandler<Unit>(stateFlow = _state)
    private val updateEmailHandler = UpdateEmailHandler<Unit>(stateFlow = _state)
    private val updateNameHandler = UpdateNameHandler<Unit>(stateFlow = _state)


    fun handleIntent(intent: CreateContactIntent) {
        when (intent) {
            is CreateContactIntent.Submit -> saveContactIntentHandler(
                saveNewContactUseCase = saveNewContactUseCase,
                validationFunctions = validationFunctions,
                stateFlow = _state,
                effectFlow = _effect,
                scope = viewModelScope
            )
            is CreateContactIntent.UpdateAccountNumber -> updateAccountNumberHandler(intent)
            is CreateContactIntent.UpdateEmail -> updateEmailHandler(intent)
            is CreateContactIntent.UpdateName -> updateNameHandler(intent)
        }
    }
}

class CreateContactViewModelFactory(
    private val saveNewContactUseCase: SaveNewContactUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateContactViewModel(saveNewContactUseCase) as T
    }
} 