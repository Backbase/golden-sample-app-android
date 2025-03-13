package com.backbase.android.journey.contacts.presentation.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateContactViewModel<ContactExtension, AccountExtension>(
    private val saveNewContactUseCase: SaveNewContactUseCase<ContactExtension, AccountExtension>,
    val validationFunctions: MutableList<(CreateContactState) -> CreateContactState> = mutableListOf()
) : ViewModel() {

    private val _state = MutableStateFlow(CreateContactState())
    val state: StateFlow<CreateContactState> = _state.asStateFlow()

    init {
        validationFunctions.add(::nameValidation)
    }

    fun handleIntent(intent: CreateContactIntent) {
        when (intent) {
            is CreateContactIntent.SaveContact -> saveContact(intent)
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

    fun nameValidation(currentState: CreateContactState): CreateContactState {
        return if(currentState.name.fieldStatus is FieldStatus.Init) {
            currentState //Does not validate when on Init
//        } else if (currentState.name.value.isEmpty()){
//            currentState.copy(name = currentState.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts.create.field.name.empty_error)))
//        } else if (currentState.name.value.contains(regex = Regex("[0-9]"))){
//            currentState.copy(name = currentState.name.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts.create.field.name.no_digits)))
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

    private fun saveContact(intent: CreateContactIntent.SaveContact) {
        // TODO
//        viewModelScope.launch {
//            _state.value = _state.value.copy(isLoading = true)
//            try {
//                val account = AccountModel<AccountExtension>(accountNumber = intent.accountNumber)
//                val contact = ContactModel<ContactExtension, AccountExtension>(
//                    id = UUID.randomUUID().toString(),
//                    name = intent.name,
//                    accounts = listOf(account)
//                )
//                saveNewContactUseCase(contact)
//                _state.value = _state.value.copy(isLoading = false, isSaved = true)
//            } catch (e: Exception) {
//                _state.value = _state.value.copy(
//                    isLoading = false,
//                    error = e.message
//                )
//            }
//        }
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