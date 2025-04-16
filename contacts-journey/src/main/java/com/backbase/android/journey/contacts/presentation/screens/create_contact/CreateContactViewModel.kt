package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.CreateContactIntentHandlers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

//Variation of the ViewModel where the Intent handlers are separated.
class CreateContactViewModel<StateExtension>(
    saveNewContactUseCase: SaveNewContactUseCase,

    private val stateFlow: MutableStateFlow<CreateContactState<StateExtension>> = MutableStateFlow(CreateContactState<StateExtension>()),
    private val effectFlow: MutableSharedFlow<CreateContactViewEffect> = MutableSharedFlow(),

    private val createContactIntentHandlers: CreateContactIntentHandlers<StateExtension> = CreateContactIntentHandlers<StateExtension>(
        saveNewContactUseCase = saveNewContactUseCase
    )
) : ViewModel() {

    val state: StateFlow<CreateContactState<StateExtension>> = stateFlow.asStateFlow()
    val effect: SharedFlow<CreateContactViewEffect?> = effectFlow.asSharedFlow()

    fun handleIntent(intent: CreateContactIntent) {
        createContactIntentHandlers.handleIntent(
            intent,
            stateFlow,
            effectFlow,
            viewModelScope
        )
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