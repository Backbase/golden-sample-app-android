package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.StateReducer
import com.backbase.android.journey.contacts.data.repository.MockContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.util.FieldValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UpdateAlias(val accountAlias: String): CreateContactIntent

data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)

class CustomCreateContactViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = CreateContactViewModel<CustomCreateContactStateExtension>(
        // If a client wants to use the default implementation from backbase
        updateNameIntentHandler(),
        updateEmailIntentHandler(),

        // If a client wants to keep the default implementation but override the async operation
        saveContactIntentHandler(saveNewContactUseCase = SaveNewContactUseCaseImpl(MockContactsRepository())),

        // If a client wants to override the default implementation
        IntentHandler<UpdateAccountNumber, CreateContactState<CustomCreateContactStateExtension>, CreateContactViewEffect> {
            coroutineScope.launch(Dispatchers.IO) { delay(timeMillis = 1000L) }
            updateUiState(showAccountNumberUpdated(accountNumber = intent.value))
        },

        // If a client wants to handle a custom intent
        IntentHandler<UpdateAlias, CreateContactState<CustomCreateContactStateExtension>, CreateContactViewEffect> {
            updateUiState(showAccountAliasUpdated(accountAlias = intent.accountAlias))
        },
    ) as T
}

fun showAccountAliasUpdated(accountAlias: String) = StateReducer<CreateContactState<CustomCreateContactStateExtension>> { currentState ->
    val updatedExtension = currentState.extension?.copy(accountAlias = FieldValue(accountAlias))

    currentState.copy(extension = updatedExtension)
}

// If the view model extends the default implementation adding more intent to handle, then
// a new screen has to be created. Components from package
// com.backbase.android.journey.contacts.presentation.components can be used to reuse as much
// components as possible.