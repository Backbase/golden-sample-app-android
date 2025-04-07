package com.backbase.android.journey.contacts.presentation.screens.create_contact_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.journey.contacts.domain.usecase.SaveNewAccountUseCase

class CreateContactAccountViewModelFactory(
    val saveNewAccountUseCase: SaveNewAccountUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateContactAccountViewModelImpl::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateContactAccountViewModelImpl(
                saveNewAccountUseCase = saveNewAccountUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}