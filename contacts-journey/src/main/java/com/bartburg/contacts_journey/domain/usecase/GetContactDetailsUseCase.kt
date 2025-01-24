package com.bartburg.contacts_journey.domain.usecase

import com.bartburg.contacts_journey.domain.model.ContactModel
import com.bartburg.contacts_journey.domain.repository.ContactsRepository
import javax.inject.Inject

interface GetContactDetailsUseCase<T : ContactModel> {
    suspend operator fun invoke(contactId: String): T
}

class GetContactDetailsUseCaseImpl @Inject constructor(
    private val contactsRepository: ContactsRepository
) : GetContactDetailsUseCase <ContactModel> {
    override suspend operator fun invoke(contactId: String): ContactModel {
        return contactsRepository.getContactDetails(contactId)
    }
} 