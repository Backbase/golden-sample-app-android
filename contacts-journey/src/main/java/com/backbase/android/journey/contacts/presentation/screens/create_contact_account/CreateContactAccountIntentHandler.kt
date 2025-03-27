package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CreateContactAccountIntentHandler<StateExtension> {
    fun handleIntent(
        intent: CreateContactAccountIntent,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactAccountViewEffect>,
        scope: CoroutineScope
    ){
        when(intent){
            is CreateContactAccountIntent.UpdateAccountName -> stateFlow.value = stateFlow.value.copy(accountName = stateFlow.value.accountName.copy(value = intent.accountName))
            is CreateContactAccountIntent.UpdateAccountNumber -> stateFlow.value = stateFlow.value.copy(accountNumber = stateFlow.value.accountNumber.copy(value = intent.accountNumber))
            CreateContactAccountIntent.Submit -> scope.launch{ saveAccount(stateFlow, effectFlow) }
        }
    }

    suspend fun saveAccount(
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactAccountViewEffect>
    ) {
        // Set loading state
        stateFlow.value = stateFlow.value.copy(isLoading = true)

        // Simulate network delay and validation
        kotlinx.coroutines.delay(1000)

        // Validate inputs
        if (stateFlow.value.accountName.value.isBlank() || stateFlow.value.accountNumber.value.isBlank()) {
            stateFlow.value = stateFlow.value.copy(
                isLoading = false,
                error = "Account name and number cannot be empty" //Would be error from BE so no translation
            )
            effectFlow.emit(CreateContactAccountViewEffect.ToContactCreateResult)
            return
        }

        // Simulate success (90%) or failure (10%) randomly
        if ((0..100).random() < 90) {
            stateFlow.value = stateFlow.value.copy(
                isLoading = false,
                error = null,
                isSaved = true
            )
        } else {
            stateFlow.value = stateFlow.value.copy(
                isLoading = false,
                error = "Failed to save account. Please try again.",//Would be error from BE so no translation
                isSaved = false
            )
        }
        effectFlow.emit(CreateContactAccountViewEffect.ToContactCreateResult)
    }
}