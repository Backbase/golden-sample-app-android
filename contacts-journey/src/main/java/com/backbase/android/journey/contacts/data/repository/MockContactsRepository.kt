package com.backbase.android.journey.contacts.data.repository

import com.backbase.android.journey.contacts.data.MockContactsCreatorImpl
import com.backbase.android.journey.contacts.domain.model.DefaultContactModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.delay

class MockContactsRepository: ContactsRepository {

    var cachedContacts: List<DefaultContactModel>? = null
    private var lastFetchTimestamp: Long = 0
    private val cacheValidityDuration: Long = 5 * 60 * 1000 // 5 minutes in milliseconds

    override suspend fun getContacts(
        page: Int,
        pageSize: Int,
    ): Result<List<DefaultContactModel>> {
        if(cachedContacts != null && !isCacheExpired()){
            return Result.success(cachedContacts!!)
        }
        val result = MockContactsCreatorImpl.createMockContactsList(pageSize)
        cacheContacts(result)
        return Result.success(MockContactsCreatorImpl.createMockContactsList(pageSize))
    }


    override suspend fun getContactDetails(contactId: String): Result<DefaultContactModel> {
        return Result.success(MockContactsCreatorImpl.createMockContactsList(1).last())
    }

    override suspend fun saveContact(contact: DefaultContactModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateContact(contact: DefaultContactModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteContact(contactId: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getCachedContacts(): List<DefaultContactModel>? {
        TODO("Not yet implemented")
    }

    override fun cacheContacts(contacts: List<DefaultContactModel>) {
        TODO("Not yet implemented")
    }

    override fun isCacheExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun clearCache() {
        TODO("Not yet implemented")
    }
}