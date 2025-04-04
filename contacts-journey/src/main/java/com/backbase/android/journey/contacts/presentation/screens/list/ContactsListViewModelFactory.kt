package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository
import com.backbase.android.journey.contacts.domain.usecase.GetContactsUseCase

class ContactsListViewModelFactory(
    private val getContactsUseCase: GetContactsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsListViewModel::class.java)) {
            return ContactsListViewModel(getContactsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
} 