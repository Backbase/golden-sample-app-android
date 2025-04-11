package com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler

import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountState
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.CreateContactAccountIntent
import kotlinx.coroutines.flow.MutableStateFlow

interface UpdateNameIntentHandler<StateExtension>{
    operator fun invoke(
        intent: CreateContactAccountIntent.UpdateAccountName,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>
    )
}

class UpdateNameIntentHandlerImpl<StateExtension>() : UpdateNameIntentHandler<StateExtension>{
    override fun invoke(
        intent: CreateContactAccountIntent.UpdateAccountName,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>
    ){
        stateFlow.value = stateFlow.value.copy(accountName = stateFlow.value.accountName.copy(value = intent.accountName))
    }
}