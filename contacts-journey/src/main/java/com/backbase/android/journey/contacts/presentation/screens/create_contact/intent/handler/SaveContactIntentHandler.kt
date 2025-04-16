package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

/**
 * Interface for handling the intent to save a newly created contact.
 *
 * This handler is responsible for:
 * - Running a list of validation functions against the current contact state.
 * - Launching a coroutine to persist the contact if validations pass.
 *
 * @param StateExtension A generic parameter used to extend the state on project if needed.
 */
interface SaveContactIntentHandler<StateExtension> {

    /**
     * Scope is passed on invoke because in Android, the ViewModel's scope it not accessible before
     * ViewModel creation.
     *
     */
    operator fun invoke(
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactViewEffect>,
        scope: CoroutineScope
    )
}

/**
 * Implementation of [SaveContactIntentHandler] that validates and saves a new contact.
 *
 * Behavior:
 * - Executes the provided validation functions against the current [CreateContactState].
 * - Aborts the save operation if the name field is invalid.
 * - If validations pass, builds a [ContactModel] with a generated ID and saves it using [SaveNewContactUseCase].
 * - Emits a [CreateContactViewEffect.ToContactCreateResult] effect on successful save.
 * - Updates the state with loading indicators and error messages as needed.
 *
 * @param saveNewContactUseCase Use case responsible for persisting the new contact.
 */
class SaveContactIntentHandlerImpl<StateExtension>(
    private val saveNewContactUseCase: SaveNewContactUseCase
    ): SaveContactIntentHandler<StateExtension> {
    override operator fun invoke(
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactViewEffect>,
        scope: CoroutineScope,
    ) {
        scope.launch{
            if (stateFlow.value.name.fieldStatus is FieldStatus.Invalid) {
                return@launch
            }

            withContext(Dispatchers.IO) {
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
    }
}