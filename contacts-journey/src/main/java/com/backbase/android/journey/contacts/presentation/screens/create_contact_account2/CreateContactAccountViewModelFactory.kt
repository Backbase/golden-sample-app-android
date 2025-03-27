package com.backbase.android.journey.contacts.presentation.screens.create_contact_account2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CreateContactAccountViewModelFactory<StateExtension, IntentExtension>(
    private val intentExtensionHandler: CreateContactAccountIntentExtensionHandler<StateExtension, IntentExtension>? = null
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateContactAccountViewModel2::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateContactAccountViewModel2(intentExtensionHandler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

fun defaultViewModelFactory(): CreateContactAccountViewModelFactory<Unit, Unit> =
    CreateContactAccountViewModelFactory(intentExtensionHandler = null)