package com.backbase.android.journey.contacts.data.service

import com.backbase.android.client.gen2.contactmanagerclient2.model.ContactGetResponseBody
import com.backbase.android.journey.contacts.data.MockContactsCreatorImpl
import kotlinx.coroutines.delay
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
class MockContactsService(private val delayMilis: Long = 1000) : ContactsApi {
    private val mockContacts = mutableListOf<ContactGetResponseBody>()

    init {
        // Add some mock data
        mockContacts.addAll(MockContactsCreatorImpl.createMockContactGetResponseBody(40))
    }

    override suspend fun getContacts(
        page: Int,
        pageSize: Int
    ): Result<List<ContactGetResponseBody>> {
        val startIndex = (page -1) * pageSize
        val endIndex = minOf(startIndex + pageSize, mockContacts.size)

        delay(delayMilis)

        return if (startIndex <= mockContacts.size) {
            Result.success(mockContacts.subList(startIndex, endIndex))
        } else {
            Result.success(emptyList())
        }
    }

    override suspend fun getContactDetails(contactId: String): Result<ContactGetResponseBody> {
        delay(delayMilis)
        return mockContacts.find { it.id == contactId }?.let {
            Result.success(it)
        } ?: Result.failure(NoSuchElementException("Contact not found"))
    }

    override suspend fun saveContact(contact: ContactGetResponseBody): Result<Unit> {
        delay(delayMilis)
        mockContacts.add(contact)
        return Result.success(Unit)
    }

    override suspend fun updateContact(contact: ContactGetResponseBody): Result<Unit> {
        delay(delayMilis)
        val index = mockContacts.indexOfFirst { it.id == contact.id }
        return if (index != -1) {
            mockContacts[index] = contact
            Result.success(Unit)
        } else {
            Result.failure(NoSuchElementException("Contact not found"))
        }
    }

    override suspend fun deleteContact(contactId: String): Result<Unit> {
        delay(delayMilis)
        val removed = mockContacts.removeIf { it.id == contactId }
        return if (removed) {
            Result.success(Unit)
        } else {
            Result.failure(NoSuchElementException("Contact not found"))
        }
    }
}