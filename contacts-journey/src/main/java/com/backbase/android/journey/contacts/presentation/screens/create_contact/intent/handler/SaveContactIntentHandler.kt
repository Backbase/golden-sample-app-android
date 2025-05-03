package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import androidx.lifecycle.viewModelScope
import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent.Submit
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showContactCreated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showError
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showLoading
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.UUID

fun <S> saveContactIntentHandler(
    saveNewContactUseCase: SaveNewContactUseCase
) = IntentHandler<Submit, CreateContactState<S>> { intent, uiState ->
    flow {
        if (uiState.name.fieldStatus is FieldStatus.Invalid) return@flow
        emit(value = showLoading())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val account = AccountModel(
                    accountNumber = uiState.accountNumber.value
                )
                val contact = ContactModel(
                    id = UUID.randomUUID().toString(),
                    name = uiState.name.value,
                    accounts = listOf(account)
                )
                saveNewContactUseCase(contact)

                emit(value = showContactCreated())
                // TODO effectFlow.emit(CreateContactViewEffect.ToContactCreateResult)
            } catch (e: Exception) {
                emit(value = showError(cause = e))
            }
        }
    }
}
