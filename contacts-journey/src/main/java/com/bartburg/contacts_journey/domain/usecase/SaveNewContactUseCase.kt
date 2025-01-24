package com.bartburg.contacts_journey.domain.usecase

import com.bartburg.contacts_journey.domain.model.ContactModel
import com.bartburg.contacts_journey.domain.repository.ContactsRepository
import javax.inject.Inject

interface SaveNewContactUseCase<T : ContactModel> {
    suspend operator fun invoke(contact: T)
}

class SaveNewContactUseCaseImpl @Inject constructor(
    private val contactsRepository: ContactsRepository
) : SaveNewContactUseCase<ContactModel> {
    override suspend fun invoke(
        contact: ContactModel
    ) {
        contactsRepository.saveContact(contact)
    }
} 