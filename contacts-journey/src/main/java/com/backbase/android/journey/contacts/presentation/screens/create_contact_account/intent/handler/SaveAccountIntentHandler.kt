package com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler

import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountState
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountViewEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

interface SaveAccountIntentHandler<StateExtension>{
    operator fun invoke(
        saveNewAccountUseCase: SaveNewAccountUseCase,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactAccountViewEffect>,
        scope: CoroutineScope)
}

class SaveAccountIntentHandlerImpl<StateExtension> : SaveAccountIntentHandler<StateExtension>{
    override fun invoke(
        saveNewAccountUseCase: SaveNewAccountUseCase,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactAccountViewEffect>,
        scope: CoroutineScope){
        if (stateFlow.value.accountName.value.isBlank() || stateFlow.value.accountNumber.value.isBlank()) {
            stateFlow.value = stateFlow.value.copy(
                isLoading = false,
                error = "Account name and number cannot be empty" //Would be error from BE so no translation
            )
            return
        }

        stateFlow.value = stateFlow.value.copy(isLoading = true)

        scope.launch {
            val result = saveNewAccountUseCase(
                account = AccountModel(
                    accountName = stateFlow.value.accountName.value,
                    accountNumber = stateFlow.value.accountNumber.value
                )
            )
            if (result.isSuccess) {
                stateFlow.value = stateFlow.value.copy(
                    isSaved = true
                )
                effectFlow.emit(CreateContactAccountViewEffect.ToContactCreateResult)
            } else {
                stateFlow.value = stateFlow.value.copy(
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }
}