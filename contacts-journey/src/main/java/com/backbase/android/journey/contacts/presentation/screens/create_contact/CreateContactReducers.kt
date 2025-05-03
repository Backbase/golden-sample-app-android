package com.backbase.android.journey.contacts.presentation.screens.create_contact

import com.backbase.android.foundation.mvi.StateReducer
import com.backbase.android.journey.contacts.presentation.util.FieldStatus

fun <S> showAccountNumberUpdated(accountNumber: String) = StateReducer<CreateContactState<S>> { currentState ->
    currentState.copy(name = currentState.accountNumber.copy(value = accountNumber))
}

fun <S> showContactCreated() = StateReducer<CreateContactState<S>> { currentState ->
    currentState.copy(isLoading = false, isSaved = true)
}

fun <S> showEmailUpdated(email: String) = StateReducer<CreateContactState<S>> { currentState ->
    currentState.copy(name = currentState.email.copy(value = email))
}

fun <S> showError(cause: Throwable) = StateReducer<CreateContactState<S>> { currentState ->
    currentState.copy(isLoading = false, error = cause.message)
}

fun <S> showFieldValidationUpdated(status: FieldStatus) = StateReducer<CreateContactState<S>> { currentState ->
    currentState.copy(accountNumber = currentState.accountNumber.copy(fieldStatus = status))
}

fun <S> showLoading() = StateReducer<CreateContactState<S>> { currentState ->
    currentState.copy(isLoading = true)
}

fun <S> showNameUpdated(name: String) = StateReducer<CreateContactState<S>> { currentState ->
    currentState.copy(name = currentState.name.copy(value = name))
}