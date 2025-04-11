package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.CreateContactAccountIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler.SaveAccountIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler.SaveAccountIntentHandlerImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler.UpdateNameIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler.UpdateNameIntentHandlerImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler.UpdateNumberIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler.UpdateNumberIntentHandlerImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateContactAccountViewModel<StateExtension>(
    private val saveNewAccountUseCase: SaveNewAccountUseCase,
    private val saveAccountIntentHandler: SaveAccountIntentHandler<StateExtension> = SaveAccountIntentHandlerImpl<StateExtension>(),
    private val updateNumberIntentHandler: UpdateNumberIntentHandler<StateExtension> = UpdateNumberIntentHandlerImpl<StateExtension>(),
    private val updateNameIntentHandler: UpdateNameIntentHandler<StateExtension> = UpdateNameIntentHandlerImpl<StateExtension>(),
    initialState: CreateContactAccountState<StateExtension> = CreateContactAccountState<StateExtension>()
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<CreateContactAccountState<StateExtension>> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateContactAccountViewEffect>()
    val effect: SharedFlow<CreateContactAccountViewEffect> = _effect.asSharedFlow()

    fun handleIntent(intent: CreateContactAccountIntent) {
        when(intent){
            CreateContactAccountIntent.Submit -> saveAccountIntentHandler(saveNewAccountUseCase, _state, _effect, viewModelScope)
            is CreateContactAccountIntent.UpdateAccountName -> updateNameIntentHandler(intent, _state)
            is CreateContactAccountIntent.UpdateAccountNumber -> updateNumberIntentHandler(intent, _state)
        }
    }
}