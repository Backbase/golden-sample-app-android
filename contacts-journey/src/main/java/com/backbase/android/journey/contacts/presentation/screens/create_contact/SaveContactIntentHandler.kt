package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.IntentScope.Companion.uiStateSnapshot
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect.ToContactCreateResult
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.Submit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

fun <S> saveContactIntentHandler(saveNewContactUseCase: SaveNewContactUseCase) = IntentHandler<Submit, CreateContactState<S>, CreateContactViewEffect> {
    updateUiState { currentState -> currentState.copy(isLoading = true) }

    launch(Dispatchers.IO) {
        try {
            val account = AccountModel(
                accountNumber = uiStateSnapshot.accountNumber.value
            )
            val contact = ContactModel(
                id = UUID.randomUUID().toString(),
                name = uiStateSnapshot.name.value,
                accounts = listOf(account)
            )
            saveNewContactUseCase(contact)

            updateUiState { currentState -> currentState.copy(isLoading = false, isSaved = true) }
            launchEffect(effect = ToContactCreateResult)
        } catch (e: Exception) {
            updateUiState { currentState -> currentState.copy(isLoading = false, error = e.message) }
        }
    }
}
