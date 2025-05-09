package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.BankAccountValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Valid

fun <S> updateAccountNumberIntentHandler() = IntentHandler<UpdateAccountNumber, CreateContactState<S>, CreateContactViewEffect> {
    updateUiState(showAccountNumberUpdated(accountNumber = intent.value))

    if (uiStateSnapshot.accountNumber.fieldStatus is FieldStatus.Init) return@IntentHandler

    val isValidBankAccount = BankAccountValidator.validateBankAccount(uiStateSnapshot.accountNumber.value)
    if (isValidBankAccount) updateUiState(showFieldValidationUpdated(status = Valid))
    else updateUiState(showFieldValidationUpdated(status = Invalid(R.string.contacts_account_create_field_wrong_format)))
}
