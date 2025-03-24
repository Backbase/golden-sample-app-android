package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateContactViewModel<ContactExtension, AccountExtension>(
    val validationFunctions: MutableList<(CreateContactState) -> CreateContactState> = mutableListOf()
) : ViewModel() {

    private val _state = MutableStateFlow(CreateContactState())
    val state: StateFlow<CreateContactState> = _state.asStateFlow()

    init {
        validationFunctions.add(::accountNumberValidation)
    }

    fun handleIntent(intent: CreateContactAccountIntent) {
        when (intent) {
            is CreateContactAccountIntent.ChangeAccountName -> TODO()
            is CreateContactAccountIntent.ChangeAccountNumber -> TODO()
        }
    }

    private fun updateName(intent: CreateContactIntent.UpdateName) {
        _state.value = _state.value.copy(
            name = _state.value.name.copy(value = intent.value)
        )

        _state.value = accountNumberValidation(_state.value)
    }

    fun accountNumberValidation(currentState: CreateContactAccountState): CreateContactAccountState {
        return if(currentState.accountNumber.fieldStatus is FieldStatus.Init) {
            currentState //Does not validate when on Init
        } else if (currentState.accountNumber.value.isEmpty()){
            currentState.copy(accountNumber = currentState.accountNumber.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_create_field_name_empty_error)))
        } else if (currentState.accountNumber.value.contains(regex = Regex("^[A-Z]{2}\\d{2}[A-Z0-9]{11,30}\$"))){
            currentState.copy(accountNumber = currentState.accountNumber.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_account_create_field_wrong_format)))
        } else {
            currentState.copy(accountNumber = currentState.accountNumber.copy(fieldStatus = FieldStatus.Valid))
        }
    }

    private fun updateEmail(intent: CreateContactIntent.UpdateEmail) {
        // TODO
    }

    private fun updateAccountNumber(intent: CreateContactIntent.UpdateAccountNumber) {
        // TODO
    }

    private fun saveContact(intent: CreateContactIntent.SaveContact) {
        runValidations()

        // TODO
    }

    private fun runValidations() {
        for (validationFunction in validationFunctions) {
            _state.value = validationFunction(_state.value)
        }
    }
}

class CreateContactViewModelFactory<ContactExtension, AccountExtension>(
    private val saveNewContactUseCase: SaveNewContactUseCase<ContactExtension, AccountExtension>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateContactViewModel(saveNewContactUseCase) as T
    }
} 