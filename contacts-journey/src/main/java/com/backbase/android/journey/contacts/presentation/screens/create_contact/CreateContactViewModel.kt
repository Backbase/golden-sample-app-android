package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.SaveContactIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.SaveContactIntentHandlerImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.UpdateAccountNumberHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.UpdateAccountNumberHandlerImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.UpdateEmailHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.UpdateEmailHandlerImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.UpdateNameHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.UpdateNameHandlerImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

//Variation of the ViewModel where the Intent handlers are separated.
class CreateContactViewModel(
    saveNewContactUseCase: SaveNewContactUseCase,

    private val _state: MutableStateFlow<CreateContactState<Unit>> = MutableStateFlow(CreateContactState<Unit>()),
    private val _effect: MutableSharedFlow<CreateContactViewEffect> = MutableSharedFlow(),

    val validationFunctions: MutableList<(CreateContactState<Unit>) -> CreateContactState<Unit>> = mutableListOf(),

    private val saveContactIntentHandler: SaveContactIntentHandler<Unit> = SaveContactIntentHandlerImpl(
        saveNewContactUseCase= saveNewContactUseCase,
        stateFlow = _state,
        effectFlow = _effect
    ),
    private val updateAccountNumberHandler: UpdateAccountNumberHandler<Unit> = UpdateAccountNumberHandlerImpl<Unit>(
        stateFlow = _state
    ),
    private val updateEmailHandler: UpdateEmailHandler<Unit> = UpdateEmailHandlerImpl<Unit>(
        stateFlow = _state
    ),
    private val updateNameHandler: UpdateNameHandler<Unit> = UpdateNameHandlerImpl<Unit>(
        stateFlow = _state
    )
) : ViewModel() {

    val state: StateFlow<CreateContactState<Unit>> = _state.asStateFlow()
    val effect: SharedFlow<CreateContactViewEffect?> = _effect.asSharedFlow()

    fun handleIntent(intent: CreateContactIntent) {
        when (intent) {
            is CreateContactIntent.Submit -> saveContactIntentHandler(
                validationFunctions = validationFunctions,
                scope = viewModelScope
            )
            is CreateContactIntent.UpdateAccountNumber -> updateAccountNumberHandler(
                intent
            )
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



class CustomCreateContactViewModelFactory(
    private val saveNewContactUseCase: SaveNewContactUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateContactViewModel(
            saveNewContactUseCase= saveNewContactUseCase,
            _state = MutableStateFlow(CreateContactState<Unit>()),
            updateEmailHandler = object: UpdateEmailHandler<Unit>{
                override fun invoke(intent: CreateContactIntent.UpdateEmail) {

                }
            }
        ) as T
    }
}