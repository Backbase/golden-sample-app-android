package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.BankAccountValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Interface for handling the [CreateContactIntent.UpdateAccountNumber] intent.
 *
 * This handler is responsible for processing updates to the account number field during
 * the contact creation flow.
 *
 * @param StateExtension A generic parameter used to extend the state on project if needed.
 */
interface UpdateAccountNumberHandler<StateExtension>{
    operator fun invoke(
        intent: CreateContactIntent.UpdateAccountNumber,
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactViewEffect>
    )
}

/**
 * Implementation of [UpdateAccountNumberHandler] that updates the account number field in the
 * [CreateContactState] and performs validation if the field has been interacted with.
 *
 * - Updates the state with the new account number value from the intent.
 * - If the field status is not [FieldStatus.Init], validates the account number format.
 * - Sets [FieldStatus.Valid] or [FieldStatus.Invalid] based on the validation result.
 *
 * @param StateExtension A generic parameter used to extend the state on project if needed.
 */
class UpdateAccountNumberHandlerImpl<StateExtension>(): UpdateAccountNumberHandler<StateExtension> {
    override operator fun invoke(
        intent: CreateContactIntent.UpdateAccountNumber,
        stateFlow: MutableStateFlow<CreateContactState<StateExtension>>,
        effectFlow: MutableSharedFlow<CreateContactViewEffect>
    ) {
        stateFlow.value = stateFlow.value.copy(
            accountNumber = stateFlow.value.accountNumber.copy(value = intent.value)
        )

        if(stateFlow.value.accountNumber.fieldStatus is FieldStatus.Init) {
            return //Does not validate when on Init
        } else {
            val validationResult = BankAccountValidator.validateBankAccount(stateFlow.value.accountNumber.value)
            if(validationResult){
                stateFlow.value = stateFlow.value.copy(accountNumber = stateFlow.value.accountNumber.copy(fieldStatus = FieldStatus.Valid))
            } else {
                stateFlow.value = stateFlow.value.copy(accountNumber = stateFlow.value.accountNumber.copy(fieldStatus = FieldStatus.Invalid(R.string.contacts_account_create_field_wrong_format)))
            }
        }
    }
}