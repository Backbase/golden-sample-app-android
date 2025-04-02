package com.backbase.android.journey.contacts.data.service

import com.backbase.android.client.gen2.contactmanagerclient2.model.ContactGetResponseBody

interface ContactsApi {
    suspend fun getContacts(page: Int, pageSize: Int, query: String? = null): Result<List<ContactGetResponseBody>>
    
    suspend fun getContactDetails(contactId: String): Result<ContactGetResponseBody>
    
    suspend fun saveContact(contact: ContactGetResponseBody): Result<Unit>
    
    suspend fun updateContact(contact: ContactGetResponseBody): Result<Unit>
    
    suspend fun deleteContact(contactId: String): Result<Unit>
}