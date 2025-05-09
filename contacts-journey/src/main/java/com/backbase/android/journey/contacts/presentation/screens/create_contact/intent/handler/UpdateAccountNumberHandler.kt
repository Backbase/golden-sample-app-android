package com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.handler

import com.backbase.android.foundation.mvi.IntentHandler
import com.backbase.android.foundation.mvi.uiStateSnapshot
import com.backbase.android.journey.contacts.R
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactState
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactViewEffect
import com.backbase.android.journey.contacts.presentation.screens.create_contact.intent.CreateContactIntent.UpdateAccountNumber
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showAccountNumberUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.showFieldValidationUpdated
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.BankAccountValidator
import com.backbase.android.journey.contacts.presentation.util.FieldStatus
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Invalid
import com.backbase.android.journey.contacts.presentation.util.FieldStatus.Valid
import kotlinx.coroutines.flow.flow

fun <S> updateAccountNumberIntentHandler() = IntentHandler<UpdateAccountNumber, CreateContactState<S>, CreateContactViewEffect> { intent, emitState, emitEffect ->
    emitState(showAccountNumberUpdated(accountNumber = intent.value))

    if (uiStateSnapshot.accountNumber.fieldStatus is FieldStatus.Init) return@IntentHandler

    val isValidBankAccount = BankAccountValidator.validateBankAccount(uiStateSnapshot.accountNumber.value)
    if (isValidBankAccount) emitState(showFieldValidationUpdated(status = Valid))
    else emitState(showFieldValidationUpdated(status = Invalid(R.string.contacts_account_create_field_wrong_format)))
}
