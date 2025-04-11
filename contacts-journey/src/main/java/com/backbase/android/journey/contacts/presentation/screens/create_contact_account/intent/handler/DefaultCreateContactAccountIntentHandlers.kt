package com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler

import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountState
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.CreateContactAccountIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultCreateContactAccountIntentHandlers<StateExtension>(
    private val saveNewAccountUseCase: SaveNewAccountUseCase,
    private val stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>,
    private val effectFlow: MutableSharedFlow<CreateContactAccountViewEffect>,
    private val scope: CoroutineScope
) {
    private val saveAccountIntentHandler: SaveAccountIntentHandler<StateExtension> = SaveAccountIntentHandlerImpl<StateExtension>()
    private val updateNumberIntentHandler: UpdateNumberIntentHandler<StateExtension> = UpdateNumberIntentHandlerImpl<StateExtension>()
    private val updateNameIntentHandler: UpdateNameIntentHandler<StateExtension> = UpdateNameIntentHandlerImpl<StateExtension>()

    fun handleIntent(
        intent: CreateContactAccountIntent
    ){
        when(intent){
            is CreateContactAccountIntent.UpdateAccountName -> updateNameIntentHandler(intent, stateFlow)
            is CreateContactAccountIntent.UpdateAccountNumber -> updateNumberIntentHandler(intent, stateFlow)
            CreateContactAccountIntent.Submit -> saveAccountIntentHandler(
                saveNewAccountUseCase = saveNewAccountUseCase,
                stateFlow = stateFlow,
                effectFlow = effectFlow,
                scope = scope
            )
        }
    }

}