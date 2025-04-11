package com.backbase.android.journey.contacts.presentation.screens.create_contact_account.customer_extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.data.repository.MockContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.CreateContactAccountIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountState
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.CreateContactAccountViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact_account.intent.handler.DefaultCreateContactAccountIntentHandlers
import com.backbase.android.journey.contacts.presentation.util.FieldValue
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow


// Below is an example where customer wants to add another editable text field. The state needs to
// be extended with an extension, the intent needs to encapsulate the default intent.

// Intent with additional type. The default CreateContactAccountIntent is encapsulated in
// DefaultIntent
sealed class CustomCreateContactAccountIntent {
    class DefaultIntent(val value: CreateContactAccountIntent): CustomCreateContactAccountIntent()
    class UpdateAccountAlias(val accountAlias: String): CustomCreateContactAccountIntent()
}

// Class with all extension fields
data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)


// Custom ViewModel creation
class CustomCreateContactAccountViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        CreateContactAccountState<CustomCreateContactStateExtension>()
    )
    val state: StateFlow<CreateContactAccountState<CustomCreateContactStateExtension>> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateContactAccountViewEffect>()
    val effect: SharedFlow<CreateContactAccountViewEffect> = _effect.asSharedFlow()

    private val defaultIntentHandlers = DefaultCreateContactAccountIntentHandlers<CustomCreateContactStateExtension>(
        saveNewAccountUseCase = SaveNewAccountUseCaseImpl(MockContactsRepository()),
        stateFlow = _state,
        effectFlow = _effect,
        scope = viewModelScope
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
            is CustomCreateContactAccountIntent.DefaultIntent -> defaultIntentHandlers.handleIntent(intent.value)
            // Or alternatively if default override is needed for some intents:
//            is CustomCreateContactAccountIntent.DefaultIntent -> {
//                when(intent.value){
//                    is CreateContactAccountIntent.UpdateAccountName -> defaultIntentHandlers.handleIntent(intent.value)
//                    is CreateContactAccountIntent.UpdateAccountNumber -> defaultIntentHandlers.handleIntent(intent.value)
//                    CreateContactAccountIntent.Submit -> {
//                        //Custom customer logic
//                    }
//                }
//            }
        }
    }
}

// Since the ViewModel is changed, a new screen has to be created. Components from package
// com.backbase.android.journey.contacts.presentation.components can be used to reuse as much
// components as possible.