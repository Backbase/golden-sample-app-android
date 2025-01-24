package com.bartburg.contacts_journey.domain.usecase

import com.bartburg.contacts_journey.domain.model.ContactModel
import com.bartburg.contacts_journey.domain.repository.ContactsRepository

interface EditContactUseCase<T : ContactModel> {
    suspend operator fun invoke(contact: T)
}

class EditContactUseCaseImpl(
    private val contactsRepository: ContactsRepository
) : EditContactUseCase<ContactModel> {
    override suspend operator fun invoke(contact: ContactModel) {
        contactsRepository.updateContact(contact)
    }
} 