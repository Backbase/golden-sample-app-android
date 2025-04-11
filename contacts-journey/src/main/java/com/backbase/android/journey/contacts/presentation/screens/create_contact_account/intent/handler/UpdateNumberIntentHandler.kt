package com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler

import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountState
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.CreateContactAccountIntent
import kotlinx.coroutines.flow.MutableStateFlow

interface UpdateNumberIntentHandler<StateExtension>{
    operator fun invoke(
        intent: CreateContactAccountIntent.UpdateAccountNumber,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>
    )
}

class UpdateNumberIntentHandlerImpl<StateExtension>() : UpdateNumberIntentHandler<StateExtension>{
    override fun invoke(
        intent: CreateContactAccountIntent.UpdateAccountNumber,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>
    ){
        stateFlow.value = stateFlow.value.copy(accountNumber = stateFlow.value.accountNumber.copy(value = intent.accountNumber))
    }
}