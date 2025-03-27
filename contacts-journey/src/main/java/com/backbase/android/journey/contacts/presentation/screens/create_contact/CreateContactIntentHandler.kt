package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class CreateContactIntentHandler<StateExtension>(
    val validationFunctions: MutableList<(CreateContactState<StateExtension>) -> CreateContactState<StateExtension>> = mutableListOf()
) {
    fun handleIntent(
        intent: CreateContactIntent,
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactViewEffect>,
        scope: CoroutineScope
    ) {
        when(intent){
            CreateContactIntent.Submit -> TODO()
            is CreateContactIntent.UpdateAccountNumber -> TODO()
            is CreateContactIntent.UpdateEmail -> TODO()
            is CreateContactIntent.UpdateName -> TODO()
        }
    }

    private fun updateName(
        intent: CreateContactIntent.UpdateName,
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>
    ) {
        stateFlow.value = stateFlow.value.copy(
            name = stateFlow.value.name.copy(value = intent.value)
        )

        stateFlow.value = nameValidation(stateFlow.value)
    }

    /**
     * Name validation function.
     *
     */
    fun nameValidation(
        currentState: CreateContactState<StateExtension>
    ): CreateContactState<StateExtension> {
        return if(currentState.name.fieldStatus is FieldStatus.Init) {
            currentState //Does not validate when on Init
        } else if (currentState.name.value.isEmpty()){
            currentState.copy(name = currentState.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_empty_error)))
        } else if (currentState.name.value.contains(regex = Regex("[0-9]"))){
            currentState.copy(name = currentState.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_no_digits)))
        } else {
            currentState.copy(name = currentState.name.copy(fieldStatus = FieldStatus.Valid))
        }
    }

    private fun updateEmail(intent: CreateContactIntent.UpdateEmail) {
        // TODO
    }

    private fun updateAccountNumber(intent: CreateContactIntent.UpdateAccountNumber) {
        // TODO
    }

    private fun saveContact(
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        scope: CoroutineScope,
        saveNewContactUseCase: SaveNewContactUseCase
    ) {
        // TODO
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