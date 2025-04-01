package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateContactAccountViewModel(
    saveNewAccountUseCase: SaveNewAccountUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CreateContactAccountState<Unit>())
    val state: StateFlow<CreateContactAccountState<Unit>> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateContactAccountViewEffect>()
    val effect: SharedFlow<CreateContactAccountViewEffect> = _effect.asSharedFlow()

    private val intentHandler = CreateContactAccountIntentHandler<Unit>(
        stateFlow = _state,
        effectFlow = _effect,
        scope = viewModelScope,
        saveNewAccountUseCase = saveNewAccountUseCase
    )

    fun handleIntent(intent: CreateContactAccountIntent) {
        intentHandler.handleIntent(
            intent = intent
        )
    }
}