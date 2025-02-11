package com.bartburg.contacts_journey.data.repository

import com.backbase.android.client.gen2.contactmanagerclient2.model.ContactGetResponseBody
import com.bartburg.contacts_journey.data.service.ContactsApi
import com.bartburg.contacts_journey.domain.model.ContactModel
import com.bartburg.contacts_journey.domain.repository.ContactsRepository
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    // Add your data sources here, e.g.:
    private val contactsApi: ContactsApi,
    // private val contactsDao: ContactsDao
    private val cacheValidityDuration: Long = 5 * 60 * 1000 // 5 minutes in milliseconds
) : ContactsRepository {
    private var cachedContacts: MutableMap<Int, List<ContactModel>> = mutableMapOf()
    private var lastFetchTimestamp: Long = 0

    override suspend fun getContacts(page: Int, pageSize: Int): Result<List<ContactModel>> {
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
                clear() // Invalidate cache after modification
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateContact(contact: ContactModel): Result<Unit> {
        return try {
            contactsApi.updateContact(contact.toContactGetResponseBody()).also {
                clear() // Invalidate cache after modification
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteContact(contactId: String): Result<Unit> {
        return try {
            contactsApi.deleteContact(contactId).also {
                clear() // Invalidate cache after modification
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCachedContacts(page: Int): List<ContactModel>? {
        return cachedContacts[page]
    }

    override fun cacheContacts(
        page: Int,
        contacts: List<ContactModel>
    ) {
        TODO("Not yet implemented")
    }

    override fun isCacheExpired(): Boolean =
        System.currentTimeMillis() - lastFetchTimestamp > cacheValidityDuration


    override fun clearCache() {
        cachedContacts.clear()
    }

    private fun updateCache(contacts: List<ContactModel>) {
        cachedContacts = contacts
        lastFetchTimestamp = System.currentTimeMillis()
        forceUpdate = false
    }


    override fun clear() {
        cachedContacts = null
        lastFetchTimestamp = 0
        forceUpdate = false
    }

    // Extension functions for mapping between domain and data models
    private fun ContactGetResponseBody.toContactModel(): ContactModel {
        return ContactModel{
            id = this@toContactModel.id
            country = this@toContactModel.country ?: ""
            name = this@toContactModel.name
            alias = this@toContactModel.alias
            accounts = emptyList()
        }
    }

    private fun ContactModel.toContactGetResponseBody(): ContactGetResponseBody {
        return ContactGetResponseBody.Builder().apply {
            id = this@toContactGetResponseBody.id
            name = this@toContactGetResponseBody.name
            accounts = emptyList()
        }.build()
    }
} 