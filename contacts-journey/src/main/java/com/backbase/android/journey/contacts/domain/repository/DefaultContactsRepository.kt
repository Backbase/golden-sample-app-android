package com.backbase.android.journey.contacts.domain.repository

import com.backbase.android.journey.contacts.data.service.ContactsApi
import com.backbase.android.journey.contacts.data.toContactGetResponseBody
import com.backbase.android.journey.contacts.data.toContactModel
import com.backbase.android.journey.contacts.domain.model.ContactModel

class DefaultContactsRepository (
    // Add your data sources here, e.g.:
    private val contactsApi: ContactsApi,
    // private val contactsDao: ContactsDao
    private val cacheValidityDuration: Long = 5 * 60 * 1000 // 5 minutes in milliseconds
) : ContactsRepository {
    private var cachedContacts: List<ContactModel>? = null
    private var lastFetchTimestamp: Long = 0

    override suspend fun getContacts(page: Int, pageSize: Int, query: String?): Result<List<ContactModel>> {
        return try {
            val result = contactsApi.getContacts(page, pageSize)
            result.fold(
                onSuccess = { contacts ->
                    val contactModels = contacts.map { it.toContactModel() }
                    updateCache(contactModels)
                    Result.success(contactModels)
                },
                onFailure = { Result.failure(it) }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getContactDetails(contactId: String): Result<ContactModel> {
        return contactsApi.getContactDetails(contactId).map { it.toContactModel() }
    }

    override suspend fun saveContact(contact: ContactModel): Result<Unit> {
        return try {
            contactsApi.saveContact(contact.toContactGetResponseBody()).also {
                clearCache() // Invalidate cache after modification
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateContact(contact: ContactModel): Result<Unit> {
        return try {
            contactsApi.updateContact(contact.toContactGetResponseBody()).also {
                clearCache() // Invalidate cache after modification
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteContact(contactId: String): Result<Unit> {
        return try {
            contactsApi.deleteContact(contactId).also {
                clearCache() // Invalidate cache after modification
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCachedContacts(): List<ContactModel>? {
        return cachedContacts
    }

    override fun cacheContacts(
        contacts: List<ContactModel>
    ) {
        cachedContacts = contacts
        lastFetchTimestamp = System.currentTimeMillis()
    }

    override fun isCacheExpired(): Boolean =
        System.currentTimeMillis() - lastFetchTimestamp > cacheValidityDuration


    override fun clearCache() {
        cachedContacts = null
        lastFetchTimestamp = 0
    }

    private fun updateCache(contacts: List<ContactModel>) {
        cachedContacts = contacts
        lastFetchTimestamp = System.currentTimeMillis()
    }
} 