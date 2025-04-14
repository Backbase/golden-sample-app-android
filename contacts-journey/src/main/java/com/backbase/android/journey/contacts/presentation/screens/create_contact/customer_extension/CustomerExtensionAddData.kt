package com.backbase.android.journey.contacts.presentation.screens.create_contact.customer_extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.android.journey.contacts.data.repository.MockContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCaseImpl
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler.DefaultCreateContactIntentHandlers
import com.backbase.android.journey.contacts.presentation.util.FieldValue
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow


// Below is an example where customer wants to add another editable text field. The state needs to
// be extended with an extension, the intent needs to encapsulate the default intent.

// Intent with additional type. The default CreateContactIntent is encapsulated in
// DefaultIntent
sealed class CustomCreateContactIntent {
    class DefaultIntent(val value: CreateContactIntent): CustomCreateContactIntent()
    class UpdateAlias(val accountAlias: String): CustomCreateContactIntent()
}

// Class with all extension fields
data class CustomCreateContactStateExtension (
    val accountAlias: FieldValue<String> = FieldValue("")
)


// Custom ViewModel creation
class CustomCreateContactViewModel : ViewModel() {

    private val _state = MutableStateFlow(CreateContactState<CustomCreateContactStateExtension>())
    val state: StateFlow<CreateContactState<CustomCreateContactStateExtension>> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateContactViewEffect>()
    val effect: SharedFlow<CreateContactViewEffect> = _effect.asSharedFlow()

    private val defaultIntentHandlers = DefaultCreateContactIntentHandlers<CustomCreateContactStateExtension>(
        saveNewContactUseCase = SaveNewContactUseCaseImpl(MockContactsRepository()),
        stateFlow = _state,
        effectFlow = _effect,
        scope = viewModelScope
    )


    fun handleIntent(intent: CustomCreateContactIntent) {
        when(intent){
            is CustomCreateContactIntent.UpdateAlias -> {
                _state.value = _state.value.copy(
                    extension = _state.value.extension?.copy(
                        accountAlias = FieldValue(intent.accountAlias)
                    )
                )
            }
            is CustomCreateContactIntent.DefaultIntent -> defaultIntentHandlers.handleIntent(intent.value)
            // Or alternatively if default override is needed for some intents:
//            is CustomCreateContactIntent.DefaultIntent -> {
//                when(intent.value){
//                    is CreateContactIntent.UpdateName -> defaultIntentHandlers.handleIntent(intent.value)
//                    is CreateContactIntent.UpdateAccountNumber -> defaultIntentHandlers.handleIntent(intent.value)
//                    is CreateContactIntent.UpdateEmail -> defaultIntentHandlers.handleIntent(intent.value)
//                    CreateContactIntent.Submit -> {
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