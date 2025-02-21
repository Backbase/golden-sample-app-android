package com.backbase.android.journey.contacts.domain.usecase

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