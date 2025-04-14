package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultCreateContactIntentHandlers<StateExtension>(
    saveNewContactUseCase: SaveNewContactUseCase,
    private val stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
    private val effectFlow: MutableSharedFlow<CreateContactViewEffect>,
    private val scope: CoroutineScope
) {
    private val saveContactHandler: SaveContactIntentHandler<StateExtension> = SaveContactIntentHandlerImpl<StateExtension>(
        saveNewContactUseCase = saveNewContactUseCase,
        stateFlow = stateFlow,
        effectFlow = effectFlow
    )
    private val updateNumberHandler: UpdateAccountNumberHandler<StateExtension> = UpdateAccountNumberHandlerImpl<StateExtension>(
        stateFlow = stateFlow
    )
    private val updateNameHandler: UpdateNameHandler<StateExtension> = UpdateNameHandlerImpl<StateExtension>(
        stateFlow = stateFlow
    )
    private val updateEmailHandler: UpdateEmailHandler<StateExtension> = UpdateEmailHandlerImpl<StateExtension>(
        stateFlow = stateFlow
    )

    fun handleIntent(intent: CreateContactIntent){
        when(intent) {
            is CreateContactIntent.UpdateName -> updateNameHandler(intent)
            CreateContactIntent.Submit -> saveContactHandler(scope)
            is CreateContactIntent.UpdateAccountNumber -> updateNumberHandler(intent)
            is CreateContactIntent.UpdateEmail -> updateEmailHandler(intent)
        }
    }
}