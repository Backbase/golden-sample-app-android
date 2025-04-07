package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent

import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

interface  SaveContactIntentHandler<StateExtension>{
    operator fun invoke(
        saveNewContactUseCase: SaveNewContactUseCase,
        validationFunctions: MutableList<(CreateContactState<StateExtension>) -> CreateContactState<StateExtension>> = mutableListOf(),
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactViewEffect?>,
        scope: CoroutineScope)
}

class SaveContactIntentHandlerImpl<StateExtension>(): SaveContactIntentHandler<StateExtension> {
    override fun invoke(
        saveNewContactUseCase: SaveNewContactUseCase,
        validationFunctions: MutableList<(CreateContactState<StateExtension>) -> CreateContactState<StateExtension>>,
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactViewEffect?>,
        scope: CoroutineScope,
    ) {
        runValidations(
            stateFlow = stateFlow,
            validationFunctions = validationFunctions
        )

        if (stateFlow.value.name.fieldStatus is FieldStatus.Invalid) {
            return
        }

        scope.launch {
            stateFlow.value = stateFlow.value.copy(isLoading = true)
            try {
                val account = AccountModel(accountNumber = stateFlow.value.accountNumber.value)
                val contact = ContactModel(
                    id = UUID.randomUUID().toString(),
                    name = stateFlow.value.name.value,
                    accounts = listOf(account)
                )
                saveNewContactUseCase(contact)
                stateFlow.value = stateFlow.value.copy(isLoading = false, isSaved = true)
                effectFlow.emit(CreateContactViewEffect.ToContactCreateResult)
            } catch (e: Exception) {
                stateFlow.value = stateFlow.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    private fun runValidations(
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        validationFunctions: MutableList<(CreateContactState<StateExtension>) -> CreateContactState<StateExtension>>
    ) {
        for (validationFunction in validationFunctions) {
            stateFlow.value = validationFunction(stateFlow.value)
        }
    }

}