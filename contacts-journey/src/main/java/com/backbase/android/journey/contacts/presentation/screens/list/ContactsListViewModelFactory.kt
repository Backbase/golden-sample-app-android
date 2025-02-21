package com.backbase.android.journey.contacts.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

class ContactsListViewModelFactory<ContactExtension, AccountExtension>(
    private val repository: ContactsRepository<ContactExtension, AccountExtension>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsListViewModel::class.java)) {
            return ContactsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
} 