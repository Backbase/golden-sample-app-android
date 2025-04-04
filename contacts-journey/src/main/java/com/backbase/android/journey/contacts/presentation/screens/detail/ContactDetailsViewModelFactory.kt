package com.backbase.android.journey.contacts.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.journey.contacts.domain.usecase.GetContactDetailsUseCase

class ContactDetailsViewModelFactory(
    private val getContactDetailsUseCase: GetContactDetailsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactDetailsViewModel::class.java)) {
            return ContactDetailsViewModel(getContactDetailsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
} 