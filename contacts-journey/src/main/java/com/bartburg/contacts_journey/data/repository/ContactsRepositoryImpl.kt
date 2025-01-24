package com.bartburg.contacts_journey.data.repository

import com.bartburg.contacts_journey.domain.model.ContactModel
import com.bartburg.contacts_journey.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    // Add your data sources here, e.g.:
    // private val contactsApi: ContactsApi,
    // private val contactsDao: ContactsDao
) : ContactsRepository {
    override suspend fun getContacts(page: Int, pageSize: Int): Flow<List<ContactModel>> = flow {
        // Implement pagination logic here
        TODO("Implement getContacts")
    }

    override suspend fun getContactDetails(contactId: String): ContactModel {
        TODO("Implement getContactDetails")
    }

    override suspend fun saveContact(contact: ContactModel) {
        TODO("Implement saveContact")
    }

    override suspend fun updateContact(contact: ContactModel) {
        TODO("Implement updateContact")
    }

    override suspend fun deleteContact(contactId: String) {
        TODO("Implement deleteContact")
    }
} 