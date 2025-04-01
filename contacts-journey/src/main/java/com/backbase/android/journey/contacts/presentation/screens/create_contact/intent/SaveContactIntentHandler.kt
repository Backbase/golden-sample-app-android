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

class SaveContactIntentHandler<StateExtension>(
    private val saveNewContactUseCase: SaveNewContactUseCase,
    private val validationFunctions: MutableList<(CreateContactState<StateExtension>) -> CreateContactState<StateExtension>> = mutableListOf(),
    private val stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
    private val effectFlow: MutableSharedFlow<CreateContactViewEffect?>,
    private val scope: CoroutineScope
    ) {

    operator fun invoke() {
        runValidations(stateFlow)

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

    private fun runValidations(stateFlow: MutableStateFlow<CreateContactState<StateExtension>>) {
        for (validationFunction in validationFunctions) {
            stateFlow.value = validationFunction(stateFlow.value)
        }
    }
}