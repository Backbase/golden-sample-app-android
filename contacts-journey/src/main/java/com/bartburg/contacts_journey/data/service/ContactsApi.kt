package com.bartburg.contacts_journey.data.service

import com.backbase.android.client.gen2.contactmanagerclient2.model.ContactGetResponseBody
import com.backbase.android.client.gen2.contactmanagerclient2.model.ContactsGetResponseBodyItem

interface ContactsApi {
    suspend fun getContacts(page: Int, pageSize: Int): Result<List<ContactGetResponseBody>>
    
    suspend fun getContactDetails(contactId: String): Result<ContactGetResponseBody>
    
    suspend fun saveContact(contact: ContactGetResponseBody): Result<Unit>
    
    suspend fun updateContact(contact: ContactGetResponseBody): Result<Unit>
    
    suspend fun deleteContact(contactId: String): Result<Unit>
}