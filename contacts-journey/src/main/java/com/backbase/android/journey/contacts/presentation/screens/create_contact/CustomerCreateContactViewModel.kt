package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.data.repository.MockContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.util.FieldValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A custom journey dev has to follow the next step in order to create a new screen:
 * 1. (Optional) Define a custom extension with extra information that the screen needs.
 *    (Optional) Create a custom view effect (for example navigate to a certain screen).
 * 2. (Optional) Define a set of `intents` that can modify the state.
 * 3. Create a set of `IntentHandlers` that can handle a intent defined in point 2 or an out-of-the-box intent.
 * 4. Create a ViewModel defining an initial state of the UI (optional) and a set of intent handlers.
 * 5. (Optional) Create a composable screen. If a client wants to support custom intents it is most
 *    likely that a new screen has to be created. Components from package
 *    com.backbase.android.journey.contacts.presentation.components can be used to reuse as much
 *    components as possible.
 * 6. TODO - Define the navigation.
 */
data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)

class UpdateAlias(val accountAlias: String): CreateContactIntent

object CustomCreateContactViewEffect: CreateContactViewEffect

class CustomCreateContactViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = CreateContactViewModel<CustomCreateContactStateExtension>(
        // If a client wants to use the default implementation from backbase
        updateNameIntentHandler = updateNameIntentHandler(),
        updateEmailIntentHandler = updateEmailIntentHandler(),
        // If a client wants to override the default implementation
        updateAccountNumberIntentHandler = IntentHandler<UpdateAccountNumber, CreateContactState<CustomCreateContactStateExtension>, CreateContactViewEffect> {
            launch(Dispatchers.IO) {
                delay(timeMillis = 1000L)
                updateUiState { currentState ->
                    currentState.copy(name = currentState.accountNumber.copy(value = intent.value))
                }
            }
        },
        // If a client wants to keep the default implementation but override the async operation
        saveContactIntentHandler = saveContactIntentHandler(saveNewContactUseCase = SaveNewContactUseCaseImpl(contactsRepository = MockContactsRepository())),
        // If a client wants to handle a custom intent
        intentHandlers = arrayOf(
            IntentHandler<UpdateAlias, CreateContactState<CustomCreateContactStateExtension>, CreateContactViewEffect> {
                updateUiState { currentState ->
                    currentState.copy(extension = (currentState.extension?: CustomCreateContactStateExtension()).copy(accountAlias = FieldValue(intent.accountAlias)))
                }
                launch { launchEffect(effect = CustomCreateContactViewEffect) }
            }
        ),
    ) as T
}

@Composable
fun CustomCreateContactScreen(onNavigateAfterSuccess: () -> Unit) {
   val viewModel = CustomCreateContactViewModelFactory().create(CreateContactViewModel::class.java)

    CreateContactScreen(viewModel, onNavigateAfterSuccess)
}