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
class CreateContactViewModel<StateExtension>(
    saveNewContactUseCase: SaveNewContactUseCase,

    private val _state: MutableStateFlow<CreateContactState<StateExtension>> = MutableStateFlow(CreateContactState<StateExtension>()),
    private val _effect: MutableSharedFlow<CreateContactViewEffect> = MutableSharedFlow(),

    private val saveContactIntentHandler: SaveContactIntentHandler<StateExtension> = SaveContactIntentHandlerImpl(
        saveNewContactUseCase= saveNewContactUseCase,
        stateFlow = _state,
        effectFlow = _effect
    ),
    private val updateAccountNumberHandler: UpdateAccountNumberHandler<StateExtension> = UpdateAccountNumberHandlerImpl<StateExtension>(
        stateFlow = _state
    ),
    private val updateEmailHandler: UpdateEmailHandler<StateExtension> = UpdateEmailHandlerImpl<StateExtension>(
        stateFlow = _state
    ),
    private val updateNameHandler: UpdateNameHandler<StateExtension> = UpdateNameHandlerImpl<StateExtension>(
        stateFlow = _state
    )
) : ViewModel() {

    val state: StateFlow<CreateContactState<StateExtension>> = _state.asStateFlow()
    val effect: SharedFlow<CreateContactViewEffect?> = _effect.asSharedFlow()

    fun handleIntent(intent: CreateContactIntent) {
        when (intent) {
            is CreateContactIntent.Submit -> saveContactIntentHandler(viewModelScope)
            is CreateContactIntent.UpdateAccountNumber -> updateAccountNumberHandler(intent)
            is CreateContactIntent.UpdateEmail -> updateEmailHandler(intent)
            is CreateContactIntent.UpdateName -> updateNameHandler(intent)
        }
    }
}

class CreateContactViewModelFactory<Extension>(
    private val saveNewContactUseCase: SaveNewContactUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateContactViewModel<Extension>(saveNewContactUseCase) as T
    }
}



class CustomCreateContactViewModelFactory<Extension>(
    private val saveNewContactUseCase: SaveNewContactUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateContactViewModel(
            saveNewContactUseCase= saveNewContactUseCase,
            _state = MutableStateFlow(CreateContactState<Extension>()),
            updateEmailHandler = object: UpdateEmailHandler<Extension>{
                override fun invoke(intent: CreateContactIntent.UpdateEmail) {

                }
            }
        ) as T
    }
}