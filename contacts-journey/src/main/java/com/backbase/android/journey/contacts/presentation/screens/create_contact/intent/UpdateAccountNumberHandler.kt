package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent

import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.BankAccountValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import kotlinx.coroutines.flow.MutableStateFlow


class UpdateAccountNumberHandler<StateExtension>(
    val stateFlow: MutableStateFlow<CreateContactState<StateExtension>>
) {
    operator fun invoke(
        intent: CreateContactIntent.UpdateAccountNumber
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