package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModelProvider
import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.ViewModel
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.Submit
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateEmail
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateName

class CreateContactViewModel<StateExtension>(
    initialState: CreateContactState<StateExtension> = CreateContactState(),
    updateNameIntentHandler: IntentHandler<UpdateName, CreateContactState<StateExtension>, CreateContactViewEffect>,
    updateEmailIntentHandler: IntentHandler<UpdateEmail, CreateContactState<StateExtension>, CreateContactViewEffect>,
    updateAccountNumberIntentHandler: IntentHandler<UpdateAccountNumber, CreateContactState<StateExtension>, CreateContactViewEffect>,
    saveContactIntentHandler: IntentHandler<Submit, CreateContactState<StateExtension>, CreateContactViewEffect>,
    intentHandlers: List<IntentHandler<out CreateContactIntent, CreateContactState<StateExtension>, CreateContactViewEffect>>
) : ViewModel<CreateContactIntent, CreateContactState<StateExtension>, CreateContactViewEffect>(
    initialState = initialState,
    intentHandlers = listOf(updateNameIntentHandler, updateEmailIntentHandler, updateAccountNumberIntentHandler, saveContactIntentHandler) + intentHandlers
)

class CreateContactViewModelFactory<E>(
    private val updateNameIntentHandler: IntentHandler<UpdateName, CreateContactState<E>, CreateContactViewEffect>,
    private val updateEmailIntentHandler: IntentHandler<UpdateEmail, CreateContactState<E>, CreateContactViewEffect>,
    private val updateAccountNumberIntentHandler: IntentHandler<UpdateAccountNumber, CreateContactState<E>, CreateContactViewEffect>,
    private val saveContactIntentHandler: IntentHandler<Submit, CreateContactState<E>, CreateContactViewEffect>,
    private val intentHandlers: List<IntentHandler<out CreateContactIntent, CreateContactState<E>, CreateContactViewEffect>> = emptyList(),
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T = CreateContactViewModel<E>(
        updateNameIntentHandler = updateNameIntentHandler,
        updateEmailIntentHandler = updateEmailIntentHandler,
        updateAccountNumberIntentHandler = updateAccountNumberIntentHandler,
        saveContactIntentHandler = saveContactIntentHandler,
        intentHandlers = intentHandlers
    ) as T
}
