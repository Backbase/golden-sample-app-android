package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CreateContactIntentHandlers<StateExtension>(
    saveNewContactUseCase: SaveNewContactUseCase,

    private val saveContactHandler: SaveContactIntentHandler<StateExtension> = SaveContactIntentHandlerImpl<StateExtension>(
        saveNewContactUseCase = saveNewContactUseCase
    ),
    private val updateNumberHandler: UpdateAccountNumberHandler<StateExtension> = UpdateAccountNumberHandlerImpl<StateExtension>(),
    private val updateNameHandler: UpdateNameHandler<StateExtension> = UpdateNameHandlerImpl<StateExtension>(),
    private val updateEmailHandler: UpdateEmailHandler<StateExtension> = UpdateEmailHandlerImpl<StateExtension>()

) {
    fun handleIntent(
        intent: CreateContactIntent,
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactViewEffect>,
        scope: CoroutineScope
    ){
        when(intent) {
            is CreateContactIntent.UpdateName -> updateNameHandler(
                intent,
                stateFlow,
                effectFlow
            )
            CreateContactIntent.Submit -> saveContactHandler(
                stateFlow,
                effectFlow,
                scope
            )
            is CreateContactIntent.UpdateAccountNumber -> updateNumberHandler(
                intent,
                stateFlow,
                effectFlow
            )
            is CreateContactIntent.UpdateEmail -> updateEmailHandler(
                intent,
                stateFlow,
                effectFlow
            )
        }
    }
}