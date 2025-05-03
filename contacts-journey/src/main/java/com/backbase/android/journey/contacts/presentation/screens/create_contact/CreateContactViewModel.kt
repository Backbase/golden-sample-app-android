package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModelProvider
import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.ViewModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.saveContactIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.updateAccountNumberIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.updateEmailIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.updateNameIntentHandler

/*
A journey dev has to follow the next step in order to create a new screen:
1. Define a `state` class representing the UI state.
2. Define a set of `intents` that can modify the state.
   Intents must by implemented a sealed classes. They implement the `Intent` interface.
3. Create a set of `IntentHandlers` that can handle the intents defined in 2.
   The intent handlers return a flow of `StateReducer`s, which update the UI state.
4. Create a ViewModel defining a initial state of the UI and a set of intent handlers.
5. Create a composable screen.
 */
class CreateContactViewModel<StateExtension>(
    intentHandlers: List<IntentHandler<out CreateContactIntent, CreateContactState<StateExtension>>>,
) : ViewModel<CreateContactIntent, CreateContactState<StateExtension>>(initialState = CreateContactState(), intentHandlers) {

    constructor(
        vararg handlers: IntentHandler<out CreateContactIntent, CreateContactState<StateExtension>>
    ) : this(intentHandlers = handlers.toList())
}

class CreateContactViewModelFactory<Extension>(private val saveNewContactUseCase: SaveNewContactUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T = CreateContactViewModel<Extension>(
        updateNameIntentHandler(),
        updateEmailIntentHandler(),
        updateAccountNumberIntentHandler(),
        saveContactIntentHandler(saveNewContactUseCase)
    ) as T
}
