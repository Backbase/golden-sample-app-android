package com.bartburg.contacts_journey.domain.repository

import com.bartburg.contacts_journey.domain.model.ContactModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing contact operations.
 * This interface defines the contract for handling contact-related data operations, supporting both
 * remote and local data sources with caching capabilities.
 *
 * Implementing classes should handle:
 * - Contact data retrieval with pagination
 * - Individual contact details fetching
 * - Contact creation, updates, and deletion
 * - Cache invalidation and management
 *
 * This interface is designed to be implementation-agnostic, allowing customers to provide their own
 * repository implementation that suits their specific data source and caching requirements. An example
 would be caching with a Room database instead of our defailt strategy of caching with local data only.
 */
interface ContactsRepository {
    suspend fun getContacts(page: Int, pageSize: Int): Result<List<ContactModel>>
    suspend fun getContactDetails(contactId: String): Result<ContactModel>
    suspend fun saveContact(contact: ContactModel): Result<Unit>
    suspend fun updateContact(contact: ContactModel): Result<Unit>
    suspend fun deleteContact(contactId: String): Result<Unit>

    suspend fun getCachedContacts(page: Int): List<ContactModel>?
    fun cacheContacts(page: Int, contacts: List<ContactModel>)
    fun isCacheExpired(): Boolean
    fun clearCache()
}