package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.viewModelScope
import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.uiStateSnapshot
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect.ToContactCreateResult
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.Submit
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

fun <S> saveContactIntentHandler(
    saveNewContactUseCase: SaveNewContactUseCase
) = IntentHandler<Submit, CreateContactState<S>, CreateContactViewEffect> { intent, emitState, emitEffect ->
    if (uiStateSnapshot.name.fieldStatus is FieldStatus.Invalid) return@IntentHandler
    emitState(showLoading())

    viewModelScope.launch(Dispatchers.IO) {
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

            emitState(showContactCreated())
            emitEffect(ToContactCreateResult)
        } catch (e: Exception) {
            emitState(showError(cause = e))
        }
    }
}
