package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.data.service.MockContactsService
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.repository.DefaultContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.GetContactsUseCaseImpl
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCase
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCaseImpl
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModel
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewModelFactory
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListViewModel
import com.backbase.android.journey.contacts.presentation.screens.list.ContactsListViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.getValue


class ContactsJourneyFragment : Fragment() {
    private val contactsRepository = DefaultContactsRepository(MockContactsService())

    private val createContactViewModel: CreateContactAccountViewModel2<Unit> by viewModels {
        CreateContactAccountViewModelFactory2(
            saveNewAccountUseCase = SaveNewAccountUseCaseImpl(contactsRepository),
            saveAccountIntentHandler = object : SaveAccountIntentHandler<Unit> {
                override fun invoke(
                    saveNewAccountUseCase: SaveNewAccountUseCase,
                    stateFlow: MutableStateFlow<CreateContactAccountState<Unit>>,
                    effectFlow: MutableSharedFlow<CreateContactAccountViewEffect>,
                    scope: CoroutineScope){
                }
            }
        )
    }
}

class CreateContactAccountViewModelFactory2<StateExtension>(
    private val saveNewAccountUseCase: SaveNewAccountUseCase,
    private val saveAccountIntentHandler: SaveAccountIntentHandler<StateExtension> = SaveAccountIntentHandlerImpl<StateExtension>(),
    private val updateNumberIntentHandler: UpdateNumberIntentHandler<StateExtension> = UpdateNumberIntentHandlerImpl<StateExtension>(),
    private val updateNameIntentHandler: UpdateNameIntentHandler<StateExtension> = UpdateNameIntentHandlerImpl<StateExtension>(),
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateContactAccountViewModel2::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateContactAccountViewModel2<StateExtension>(
                saveNewAccountUseCase = saveNewAccountUseCase,
                saveAccountIntentHandler = saveAccountIntentHandler,
                updateNumberIntentHandler = updateNumberIntentHandler,
                updateNameIntentHandler = updateNameIntentHandler
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class CreateContactAccountViewModel2<StateExtension>(
    private val saveNewAccountUseCase: SaveNewAccountUseCase,
    private val saveAccountIntentHandler: SaveAccountIntentHandler<StateExtension> = SaveAccountIntentHandlerImpl<StateExtension>(),
    private val updateNumberIntentHandler: UpdateNumberIntentHandler<StateExtension> = UpdateNumberIntentHandlerImpl<StateExtension>(),
    private val updateNameIntentHandler: UpdateNameIntentHandler<StateExtension> = UpdateNameIntentHandlerImpl<StateExtension>(),
    initialState: CreateContactAccountState<StateExtension> = CreateContactAccountState<StateExtension>()
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<CreateContactAccountState<StateExtension>> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateContactAccountViewEffect>()
    val effect: SharedFlow<CreateContactAccountViewEffect> = _effect.asSharedFlow()

    fun handleIntent(intent: CreateContactAccountIntent) {
        when(intent){
            CreateContactAccountIntent.Submit -> saveAccountIntentHandler(saveNewAccountUseCase, _state, _effect, viewModelScope)
            is CreateContactAccountIntent.UpdateAccountName -> updateNameIntentHandler(intent, _state)
            is CreateContactAccountIntent.UpdateAccountNumber -> updateNumberIntentHandler(intent, _state)
        }
    }
}

interface UpdateNumberIntentHandler<StateExtension>{
    operator fun invoke(
        intent: CreateContactAccountIntent.UpdateAccountNumber,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>
    )
}

class UpdateNumberIntentHandlerImpl<StateExtension>() : UpdateNumberIntentHandler<StateExtension>{
    override fun invoke(
        intent: CreateContactAccountIntent.UpdateAccountNumber,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>
    ){
        stateFlow.value = stateFlow.value.copy(accountNumber = stateFlow.value.accountNumber.copy(value = intent.accountNumber))
    }
}

interface UpdateNameIntentHandler<StateExtension>{
    operator fun invoke(
        intent: CreateContactAccountIntent.UpdateAccountName,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>
    )
}

class UpdateNameIntentHandlerImpl<StateExtension>() : UpdateNameIntentHandler<StateExtension>{
    override fun invoke(
        intent: CreateContactAccountIntent.UpdateAccountName,
        stateFlow: MutableStateFlow<CreateContactAccountState<StateExtension>>
    ){
        stateFlow.value = stateFlow.value.copy(accountName = stateFlow.value.accountName.copy(value = intent.accountName))
    }
}

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