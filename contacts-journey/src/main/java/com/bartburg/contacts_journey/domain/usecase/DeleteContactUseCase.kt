package com.bartburg.contacts_journey.domain.usecase

import com.bartburg.contacts_journey.domain.repository.ContactsRepository

interface DeleteContactUseCase {
    suspend operator fun invoke(contactId: String)
}

class DeleteContactUseCaseImpl(
    private val contactsRepository: ContactsRepository
) : DeleteContactUseCase {
    override suspend operator fun invoke(contactId: String) {
        contactsRepository.deleteContact(contactId)
    }
} 