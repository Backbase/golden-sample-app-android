package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModelProvider
import com.backbase.android.foundation.mvi.IntentHandlerCollection
import com.backbase.android.foundation.mvi.ViewModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.saveContactIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.updateAccountNumberHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.updateEmailHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.updateNameHandler

/*
A journey dev has to follow the next step in order to create a new screen:
1. Define a `state` class representing the UI state.
2. Define a set of `intents` that can modify the state.
   Intents must by implemented a sealed classes. They implement the `Intent` interface.
3. Create a set of `IntentHandlers` that can handle the intents defined in 2.
4. Create a ViewModel defining a initial state of the UI and a set of intent handlers.
5. Create a composable screen.
 */
class CreateContactViewModel<StateExtension>(
    initialState: CreateContactState<StateExtension>,
    intentHandlers: IntentHandlerCollection<CreateContactIntent, CreateContactState<StateExtension>>,
) : ViewModel<CreateContactIntent, CreateContactState<StateExtension>>(initialState, intentHandlers)

class CreateContactViewModelFactory<Extension>(private val saveNewContactUseCase: SaveNewContactUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return CreateContactViewModel<Extension>(
            initialState = CreateContactState(),
            intentHandlers = IntentHandlerCollection(
                updateNameHandler(),
                updateEmailHandler(),
                updateAccountNumberHandler(),
                saveContactIntentHandler(saveNewContactUseCase),
            )
        ) as T
    }
}