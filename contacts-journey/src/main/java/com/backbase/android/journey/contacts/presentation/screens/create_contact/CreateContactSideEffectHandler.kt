package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.foundation.mvi.SideEffectHandler
import com.backbase.android.journey.contacts.domain.model.AccountModel
import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.usecase.SaveNewContactUseCase
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.AccountNumberValidationResult
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.EmailValidationResult
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.NameValidationResultIntent
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.SaveContactError
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactIntent.Submitted
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactSideEffect.RunBankAccountValidation
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactSideEffect.RunEmailValidation
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactSideEffect.RunNameValidation
import com.backbase.android.journey.contacts.presentation.screens.create_contact.CreateContactSideEffect.SaveContact
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.AccountNameValidator
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.BankAccountValidator
import com.backbase.android.journey.contacts.presentation.screens.create_contact.validation.EmailValidator
import java.util.UUID

class CreateContactSideEffectHandler<S>(
    private val saveNewContactUseCase: SaveNewContactUseCase,
): SideEffectHandler<CreateContactIntent, CreateContactState<S>, CreateContactSideEffect> {

    override suspend fun handleEffect(
        currentState: CreateContactState<S>,
        effect: CreateContactSideEffect,
        throwIntent: (CreateContactIntent) -> Unit
    ) {
        when (effect) {
            is RunBankAccountValidation -> {
                val isValid = BankAccountValidator.validateBankAccount(effect.accountNumber)
                throwIntent(AccountNumberValidationResult(isValid))
            }
            is RunEmailValidation -> {
                val isValid = EmailValidator.validateEmail(effect.email)
                throwIntent(EmailValidationResult(isValid))
            }
            is RunNameValidation -> {
                val validateNameResult = AccountNameValidator.validate(effect.name)
                throwIntent(NameValidationResultIntent(validateNameResult))
            }
            is SaveContact -> {
                try {
                    val contact = ContactModel(
                        id = UUID.randomUUID().toString(),
                        name = currentState.name.value,
                        accounts = listOf(AccountModel(accountNumber = currentState.accountNumber.value))
                    )
                    saveNewContactUseCase(contact)
                    throwIntent(Submitted)
                } catch (e: Exception) {
                    throwIntent(SaveContactError(e))
                }
            }
        }
    }
}
