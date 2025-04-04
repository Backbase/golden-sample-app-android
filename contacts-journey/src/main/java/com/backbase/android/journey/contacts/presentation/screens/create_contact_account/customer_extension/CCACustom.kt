package com.backbase.android.journey.contacts.presentation.screens.create_contact_account.customer_extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.domain.repository.MockContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountIntentHandler
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountState
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountViewEffect
import com.backbase.android.journey.contacts.presentation.util.FieldValue
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow


// Below are examples for customer implementations
sealed class CustomCreateContactAccountIntent {
    class DefaultIntent(val value: CreateContactAccountIntent): CustomCreateContactAccountIntent()
    class UpdateAccountAlias(val accountAlias: String): CustomCreateContactAccountIntent()
}

data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)

class CustomCreateContactAccountViewModel : ViewModel() {

    private val _state = MutableStateFlow(CreateContactAccountState<CustomCreateContactStateExtension>())
    val state: StateFlow<CreateContactAccountState<CustomCreateContactStateExtension>> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateContactAccountViewEffect>()
    val effect: SharedFlow<CreateContactAccountViewEffect> = _effect.asSharedFlow()

    private val intentHandler = CreateContactAccountIntentHandler<CustomCreateContactStateExtension>(
        stateFlow = _state,
        effectFlow = _effect,
        scope = viewModelScope,
        saveNewAccountUseCase = SaveNewAccountUseCaseImpl(MockContactsRepository())
    )

    fun handleIntent(intent: CustomCreateContactAccountIntent) {
        when(intent){
            is CustomCreateContactAccountIntent.UpdateAccountAlias -> {
                _state.value = _state.value.copy(
                    extension = _state.value.extension?.copy(
                        accountAlias = FieldValue(intent.accountAlias)
                    )
                )
            }
            is CustomCreateContactAccountIntent.DefaultIntent -> intentHandler.handleIntent(intent.value)
            // Or alternatively if default override is needed for some intents:
//            is CustomCreateContactAccountIntent.DefaultIntent -> {
//                when(intent.value){
//                    is CreateContactAccountIntent.UpdateAccountName -> intentHandler.handleIntent(intent.value)
//                    is CreateContactAccountIntent.UpdateAccountNumber -> intentHandler.handleIntent(intent.value)
//                    CreateContactAccountIntent.Submit -> TODO()
//                }
//            }
        }
    }
}