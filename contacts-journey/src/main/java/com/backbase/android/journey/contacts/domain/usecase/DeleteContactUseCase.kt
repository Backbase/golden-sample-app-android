package com.backbase.android.journey.contacts.domain.usecase

import android.provider.ContactsContract
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

interface DeleteContactUseCase {
    suspend operator fun invoke(contactId: String)
}

class DeleteContactUseCaseImpl<ContactExtension, AccountExtension>(
    private val contactsRepository: ContactsRepository<ContactExtension, AccountExtension>
) : DeleteContactUseCase {
    override suspend operator fun invoke(contactId: String) {
        contactsRepository.deleteContact(contactId)
    }
}

data class Contact(val name: String, val contactDTO: ContactDto)

data class ContactExtension(val nickname: String)

val contact: Contact<ContactExtension> = Contact("John Doe", ContactExtension("Johnny"))