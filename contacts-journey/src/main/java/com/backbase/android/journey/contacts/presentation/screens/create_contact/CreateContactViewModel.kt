package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModelProvider
import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.ViewModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.Submit
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateEmail
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateName

/**
 * Journey devs have to follow the next steps to create a new screen:
 * 1. Define a `state` class representing the UI state.
 *    (Optional) Define a set of ViewEffects that can be launched from the UI.
 * 2. Define a set of `intents` that can modify the state.
 * 3. Create a set of `IntentHandlers` that can handle the intents defined in 2.
 *    The intent handlers update the UI or launch a side effect based on the operation that the intent represents.
 * 4. Create a ViewModel defining a initial state of the UI and a set of intent handlers.
 * 5. Create a composable screen.
 * 6. Define the navigation.
 */
class CreateContactViewModel<StateExtension>(
    initialState: CreateContactState<StateExtension> = CreateContactState(),
    updateNameIntentHandler: IntentHandler<UpdateName, CreateContactState<StateExtension>, CreateContactViewEffect>,
    updateEmailIntentHandler: IntentHandler<UpdateEmail, CreateContactState<StateExtension>, CreateContactViewEffect>,
    updateAccountNumberIntentHandler: IntentHandler<UpdateAccountNumber, CreateContactState<StateExtension>, CreateContactViewEffect>,
    saveContactIntentHandler: IntentHandler<Submit, CreateContactState<StateExtension>, CreateContactViewEffect>,
    vararg intentHandlers: IntentHandler<out CreateContactIntent, CreateContactState<StateExtension>, CreateContactViewEffect>
) : ViewModel<CreateContactIntent, CreateContactState<StateExtension>, CreateContactViewEffect>(
    initialState = initialState,
    intentHandlers = listOf(updateNameIntentHandler, updateEmailIntentHandler, updateAccountNumberIntentHandler, saveContactIntentHandler) + intentHandlers
)

class CreateContactViewModelFactory<Extension>(private val saveNewContactUseCase: SaveNewContactUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T = CreateContactViewModel<Extension>(
        updateNameIntentHandler = updateNameIntentHandler(),
        updateEmailIntentHandler = updateEmailIntentHandler(),
        updateAccountNumberIntentHandler = updateAccountNumberIntentHandler(),
        saveContactIntentHandler = saveContactIntentHandler(saveNewContactUseCase)
    ) as T
}
