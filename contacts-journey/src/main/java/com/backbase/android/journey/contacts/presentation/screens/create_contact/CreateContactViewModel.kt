package com.backbase.android.journey.contacts.presentation.screens.create_contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class CreateContactViewModel(
    private val saveNewContactUseCase: SaveNewContactUseCase,
    val validationFunctions: MutableList<(DefaultCreateContactState) -> DefaultCreateContactState> = mutableListOf()
) : ViewModel() {

    private val _state = MutableStateFlow(DefaultCreateContactState())
    val state: StateFlow<DefaultCreateContactState> = _state.asStateFlow()

    init {
        validationFunctions.add(::nameValidation) //TODO use validations
    }

    fun handleIntent(intent: CreateContactIntent) {
        when (intent) {
            is CreateContactIntent.Submit -> saveContact(intent)
            is CreateContactIntent.UpdateAccountNumber -> updateAccountNumber(intent)
            is CreateContactIntent.UpdateEmail -> updateEmail(intent)
            is CreateContactIntent.UpdateName -> updateName(intent)
        }
    }

    private fun updateName(intent: CreateContactIntent.UpdateName) {
        _state.value = _state.value.copy(
            name = _state.value.name.copy(value = intent.value)
        )

        _state.value = nameValidation(_state.value)
    }

    fun nameValidation(currentState: DefaultCreateContactState): DefaultCreateContactState {
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

    private fun saveContact(intent: CreateContactIntent.Submit) {
        // TODO

        runValidations()

        if (_state.value.name.fieldStatus is FieldStatus.Invalid) {
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val account = AccountModel(accountNumber = state.value.accountNumber.value)
                val contact = ContactModel(
                    id = UUID.randomUUID().toString(),
                    name = state.value.name.value,
                    accounts = listOf(account)
                )
                saveNewContactUseCase(contact)
                _state.value = _state.value.copy(isLoading = false, isSaved = true)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    private fun runValidations() {
        for (validationFunction in validationFunctions) {
            _state.value = validationFunction(_state.value)
        }
    }
}

class CreateContactViewModelFactory(
    private val saveNewContactUseCase: SaveNewContactUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateContactViewModel(saveNewContactUseCase) as T
    }
} 