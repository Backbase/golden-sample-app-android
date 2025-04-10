package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CreateContactAccountIntentHandler<StateExtension>(
    private val saveNewAccountUseCase: SaveNewAccountUseCase,
    private val stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>,
    private val effectFlow: MutableSharedFlow<CreateContactAccountViewEffect>,
    private val scope: CoroutineScope
) {
    fun handleIntent(
        intent: CreateContactAccountIntent
    ){
        when(intent){
            is CreateContactAccountIntent.UpdateAccountName -> stateFlow.value = stateFlow.value.copy(accountName = stateFlow.value.accountName.copy(value = intent.accountName))
            is CreateContactAccountIntent.UpdateAccountNumber -> stateFlow.value = stateFlow.value.copy(accountNumber = stateFlow.value.accountNumber.copy(value = intent.accountNumber))
            CreateContactAccountIntent.Submit -> saveAccount(stateFlow, effectFlow, scope, saveNewAccountUseCase)
        }
    }

    fun saveAccount(
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactAccountViewEffect>,
        scope: CoroutineScope,
        saveNewAccountUseCase: SaveNewAccountUseCase
    ) {

        // Validate inputs
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