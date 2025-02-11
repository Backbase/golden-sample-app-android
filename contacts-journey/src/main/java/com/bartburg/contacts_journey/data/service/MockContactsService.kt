package com.bartburg.contacts_journey.data.service

import com.backbase.android.client.gen2.contactmanagerclient2.model.ContactGetResponseBody
import java.util.*

/**
 * A mock implementation of the [ContactsApi] interface for testing and development purposes.
 *
 * This class provides a simulated service for managing contacts, allowing you to
 * test your application's logic without relying on a real backend or database.
 * It stores contact data in memory and provides methods to interact with this data.
 *
 * The mock service is initialized with a predefined set of contacts.
 *
 * Note: Data stored in this mock service is not persistent and will be lost when
 * the application is closed or the service is reinitialized.
 */
class MockContactsService : ContactsApi {
    private val mockContacts = mutableListOf<ContactGetResponseBody>()
    
    init {
        // Add some mock data
        mockContacts.addAll(createMockContacts())
    }

    override suspend fun getContacts(
        page: Int,
        pageSize: Int
    ): Result<List<ContactGetResponseBody>> {
        val startIndex = page * pageSize
        val endIndex = minOf(startIndex + pageSize, mockContacts.size)
        
        return if (startIndex <= mockContacts.size) {
            Result.success(mockContacts.subList(startIndex, endIndex))
        } else {
            Result.success(emptyList())
        }
    }

    override suspend fun getContactDetails(contactId: String): Result<ContactGetResponseBody> {
        return mockContacts.find { it.id == contactId }?.let {
            Result.success(it)
        } ?: Result.failure(NoSuchElementException("Contact not found"))
    }

    override suspend fun saveContact(contact: ContactGetResponseBody): Result<Unit> {
        mockContacts.add(contact)
        return Result.success(Unit)
    }

    override suspend fun updateContact(contact: ContactGetResponseBody): Result<Unit> {
        val index = mockContacts.indexOfFirst { it.id == contact.id }
        return if (index != -1) {
            mockContacts[index] = contact
            Result.success(Unit)
        } else {
            Result.failure(NoSuchElementException("Contact not found"))
        }
    }

    override suspend fun deleteContact(contactId: String): Result<Unit> {
        val removed = mockContacts.removeIf { it.id == contactId }
        return if (removed) {
            Result.success(Unit)
        } else {
            Result.failure(NoSuchElementException("Contact not found"))
        }
    }

    private fun createMockContacts(): List<ContactGetResponseBody> {
        return listOf(
            ContactGetResponseBody.Builder().apply {
                id = "1"
                name = "John Doe"
                emailId = "john.doe@example.com"
                phoneNumber = "+1234567890"
                accounts = emptyList()
            }.build(),
            ContactGetResponseBody.Builder().apply {
                id = "2"
                name = "Jane Smith"
                emailId = "jane.smith@example.com"
                phoneNumber = "+0987654321"
                accounts = emptyList()
            }.build()
        )
    }
}