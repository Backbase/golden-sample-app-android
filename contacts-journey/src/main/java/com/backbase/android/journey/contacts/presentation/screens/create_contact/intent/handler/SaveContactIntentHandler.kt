package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import androidx.lifecycle.viewModelScope
import com.backbase.android.foundation.mvi.intentHandler
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

fun <StateExtension> saveContactIntentHandler(
    saveNewContactUseCase: SaveNewContactUseCase
) = intentHandler<CreateContactIntent.UpdateEmail, CreateContactState<StateExtension>> { intent, onUiStateUpdated ->
    val state = this.uiState.value
    if (state.name.fieldStatus is FieldStatus.Invalid) return@intentHandler

    viewModelScope.launch(Dispatchers.IO) {
        onUiStateUpdated(state.copy(isLoading = true))

        try {
            val account = AccountModel(accountNumber = state.accountNumber.value)
            val contact = ContactModel(
                id = UUID.randomUUID().toString(),
                name = state.name.value,
                accounts = listOf(account)
            )
            saveNewContactUseCase(contact)

            onUiStateUpdated(state.copy(isLoading = false, isSaved = true))
            // TODO effectFlow.emit(CreateContactViewEffect.ToContactCreateResult)
        } catch (e: Exception) {
            onUiStateUpdated(state.copy(isLoading = false, error = e.message))
        }
    }
}
