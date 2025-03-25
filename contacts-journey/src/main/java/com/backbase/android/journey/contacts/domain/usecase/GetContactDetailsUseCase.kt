package com.backbase.android.journey.contacts.domain.usecase

import com.backbase.android.journey.contacts.domain.model.ContactModel
import com.backbase.android.journey.contacts.domain.repository.ContactsRepository

interface GetContactDetailsUseCase {
    suspend operator fun invoke(contactId: String): Result<ContactModel>
}

class GetContactDetailsUseCaseImpl (
    private val contactsRepository: ContactsRepository
) : GetContactDetailsUseCase  {
    override suspend operator fun invoke(contactId: String): Result<ContactModel> {
        return contactsRepository.getContactDetails(contactId)
    }
}