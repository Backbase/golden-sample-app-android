package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModelProvider
import com.backbase.android.foundation.mvi.IntentHandlerCollection
import com.backbase.android.foundation.mvi.ViewModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.createContactIntentHandlers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateContactViewModel<StateExtension>(
    handlers: IntentHandlerCollection<CreateContactIntent, CreateContactState<StateExtension>>
) : ViewModel<CreateContactIntent, CreateContactState<StateExtension>>(handlers) {

    private val _uiStateFlow = MutableStateFlow(CreateContactState<StateExtension>())
    override val uiState: StateFlow<CreateContactState<StateExtension>> = _uiStateFlow.asStateFlow()

    override fun updateState(newState: CreateContactState<StateExtension>) {
        _uiStateFlow.value = newState
    }
}

class CreateContactViewModelFactory<Extension>(private val saveNewContactUseCase: SaveNewContactUseCase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        val handlers = createContactIntentHandlers<Extension>(saveNewContactUseCase)

        return CreateContactViewModel<Extension>(handlers) as T
    }
}