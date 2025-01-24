package com.bartburg.contacts_journey.domain.repository

import com.bartburg.contacts_journey.domain.model.ContactModel
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    suspend fun getContacts(page: Int, pageSize: Int): Flow<List<ContactModel>>
    suspend fun getContactDetails(contactId: String): ContactModel
    suspend fun saveContact(contact: ContactModel)
    suspend fun updateContact(contact: ContactModel)
    suspend fun deleteContact(contactId: String)
} 